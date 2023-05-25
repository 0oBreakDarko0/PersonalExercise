package org.myself.huawei.machine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * 第一行：员工数量
 * 第二行：员工打卡数量 用空格隔开
 * 第三行：每天打卡的员工ID集合 用空格隔开
 *
 * 排序规则：1.打卡次数, 2. 打卡时间, 3.打卡人ID
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            //员工数量
            int employeeNums = in.nextInt();

            //员工打卡数量
            int[] employeeSign = new int[30];

            for (int i = 0; i < 30; i++) {
                employeeSign[i] = in.nextInt();
            }

            Map<Integer, Person> result = new HashMap<>();
            for (int i = 0; i < 30; i++) {
                int signCounts = employeeSign[i];
                for (int j = 0; j < signCounts; j++) {
                    //打卡人的ID
                    int signId = in.nextInt();
                    Person person = result.get(signId);
                    if (person == null) {
                        person = new Person();
                        person.setID(signId);
                        person.setCount(1);
                        person.setMinSignTime(i);
                    } else {
                        Integer count = person.getCount();
                        count++;
                        person.setCount(count);
                    }

                    result.put(signId, person);
                }
            }

            ArrayList<Person> employeeResult = new ArrayList<>(result.values());
            List<Integer> collect = employeeResult.stream().sorted(new Comparator<Person>() {
                @Override
                public int compare(Person o1, Person o2) {
                    Integer count1 = o1.getCount();
                    Integer count2 = o2.getCount();

                    if (!count1.equals(count2)) {
                        return count1 - count2;
                    } else {
                        Integer minSignTime1 = o1.getMinSignTime();
                        Integer minSignTime2 = o2.getMinSignTime();

                        if (!minSignTime1.equals(minSignTime2)) {
                            if (minSignTime1 < minSignTime2) {
                                return 1;
                            } else {
                                return -1;
                            }
                        } else {
                            Integer id1 = o1.getID();
                            Integer id2 = o2.getID();

                            return id2 - id1;
                        }
                    }
                }
            }).map(Person::getID).collect(Collectors.toList());

            Collections.reverse(collect);
            List<String> resultIds = collect.stream().limit(5).map(String::valueOf).collect(Collectors.toList());
            System.out.println(String.join(" ", resultIds));
        }
    }

    static class Person{
        private Integer ID;
        private Integer count;
        private Integer minSignTime;

        public Integer getID() {
            return ID;
        }

        public void setID(Integer ID) {
            this.ID = ID;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public Integer getMinSignTime() {
            return minSignTime;
        }

        public void setMinSignTime(Integer minSignTime) {
            this.minSignTime = minSignTime;
        }


    }
}
