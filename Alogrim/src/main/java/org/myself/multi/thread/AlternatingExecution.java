package org.myself.multi.thread;

/**
 * 三个线程交替打印A、B、C
 * @author mapengfei
 */
public class AlternatingExecution {
    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(){
            @Override
            public void run() {
                System.out.println("线程A");;
            }
        };

        Thread threadB = new Thread(){
            @Override
            public void run() {
                System.out.println("线程B");;
            }
        };

        Thread threadC = new Thread(){
            @Override
            public void run() {
                System.out.println("线程C");;
            }
        };

        threadA.start();
        threadA.join();

        threadB.start();
        threadB.join();

        threadC.start();
        threadC.join();
    }
}
