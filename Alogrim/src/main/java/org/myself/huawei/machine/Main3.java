package org.myself.huawei.machine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * 输入：每一行有4个数字，1: ID, 2: 优先级, 3: 执行时间, 4.到达时间
 *
 * @author mapengfei
 */
public class Main3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        LinkedList<ThreadInformation> threadInformations = new LinkedList<>();
        while (in.hasNextLine()) {
            String tempLine = in.nextLine();

            if ("".equals(tempLine) || tempLine == null) {
                break;
            }
            String[] s = tempLine.split(" ");
            int id = Integer.parseInt(s[0]);
            int priority = Integer.parseInt(s[1]);
            int executeTime = Integer.parseInt(s[2]);
            int arriveTime = Integer.parseInt(s[3]);

            ThreadInformation threadInformation = new ThreadInformation(id, priority, executeTime, arriveTime);
            threadInformations.add(threadInformation);
        }

        List<ThreadInformation> result = new ArrayList<>();
        solution(threadInformations.removeFirst(), threadInformations, result, 0);

        Collections.sort(result, new Comparator<ThreadInformation>() {
            @Override
            public int compare(ThreadInformation o1, ThreadInformation o2) {
                return o1.getEndTime() - o2.getEndTime();
            }
        });

        result.forEach(r -> {
            System.out.println(r.getId() + " "+ r.getEndTime());
        });
    }

    private static void solution(ThreadInformation cur, LinkedList<ThreadInformation> threadInformations, List<ThreadInformation> result, int lastEndTime) {

        //获取当前的到达时间和执行时间
        Integer arriveTime = cur.getArriveTime();
        Integer executeTime = cur.getExecuteTime();

        int endTime = arriveTime >= lastEndTime ? arriveTime + executeTime : lastEndTime + executeTime;
        //获取优先级
        Integer priority = cur.getPriority();

        //遍历剩下的节点，找到优先级比当前线程高，且到达时间大于当前结束时间的
        ThreadInformation highPriorityThread = getHighPriorityThread(priority, endTime, threadInformations);
        if (highPriorityThread != null) {
            //更新当前节点的执行时间
            Integer highArriveTime = highPriorityThread.getArriveTime();
            lastEndTime = highArriveTime > lastEndTime ? highArriveTime : lastEndTime;
            cur.setExecuteTime(executeTime - (highArriveTime - arriveTime));
            ThreadInformation threadInformation = new ThreadInformation(cur.getId(), cur.getPriority(), cur.getExecuteTime(), cur.getArriveTime());
            threadInformation.setEndTime(cur.getEndTime());
            //再把当前节点加入到队列中去
            threadInformations.add(threadInformation);
            solution(highPriorityThread, threadInformations, result, lastEndTime);

        } else {
            cur.setEndTime(endTime);
            ThreadInformation threadInformation = new ThreadInformation(cur.getId(), cur.getPriority(), cur.getExecuteTime(), cur.getArriveTime());
            threadInformation.setEndTime(cur.getEndTime());
            result.add(threadInformation);

            if (threadInformations.isEmpty()) {
                return;
            }
            cur = threadInformations.removeFirst();
            lastEndTime = endTime;
            solution(cur, threadInformations, result, lastEndTime);
        }


    }

    private static ThreadInformation getHighPriorityThread(int priority, int endTime, LinkedList<ThreadInformation> threadInformations) {
        for (int i = 0; i < threadInformations.size(); i++) {
            ThreadInformation threadInformation = threadInformations.get(i);
            Integer arriveTime = threadInformation.getArriveTime();
            Integer curPriority = threadInformation.getPriority();

            if (curPriority > priority && arriveTime <= endTime) {
                return threadInformations.remove(i);
            }
        }

        return null;
    }

    static class ThreadInformation {
        private Integer id;
        private Integer priority;
        private Integer executeTime;
        private Integer arriveTime;
        private Integer endTime;

        public ThreadInformation(Integer id, Integer priority, Integer executeTime, Integer arriveTime) {
            this.id = id;
            this.priority = priority;
            this.executeTime = executeTime;
            this.arriveTime = arriveTime;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getPriority() {
            return priority;
        }

        public void setPriority(Integer priority) {
            this.priority = priority;
        }

        public Integer getExecuteTime() {
            return executeTime;
        }

        public void setExecuteTime(Integer executeTime) {
            this.executeTime = executeTime;
        }

        public Integer getArriveTime() {
            return arriveTime;
        }

        public void setArriveTime(Integer arriveTime) {
            this.arriveTime = arriveTime;
        }

        public Integer getEndTime() {
            return endTime;
        }

        public void setEndTime(Integer endTime) {
            this.endTime = endTime;
        }
    }
}
