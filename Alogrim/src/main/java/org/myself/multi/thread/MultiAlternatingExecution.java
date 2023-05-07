package org.myself.multi.thread;

import java.util.concurrent.Semaphore;

/**
 * 三个线程交替打印A、B、C
 *
 * @author mapengfei
 */
public class MultiAlternatingExecution {

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphoreA = new Semaphore(1);
        Semaphore semaphoreB = new Semaphore(1);
        Semaphore semaphoreC = new Semaphore(1);

        Thread threadA = new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        semaphoreA.acquire();
                        System.out.println("线程A");
                        semaphoreB.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread threadB = new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        semaphoreB.acquire();
                        System.out.println("线程B");
                        semaphoreC.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread threadC = new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        semaphoreC.acquire();
                        System.out.println("线程C");
                        semaphoreA.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        semaphoreB.acquire();
        semaphoreC.acquire();
        threadA.start();
        threadB.start();
        threadC.start();
    }

}
