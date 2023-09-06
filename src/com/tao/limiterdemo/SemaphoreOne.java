package com.tao.limiterdemo;



import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;

public class SemaphoreOne {
    private static Semaphore semaphore = new Semaphore(10);

    public static void bizMethod() throws InterruptedException{
        if(!semaphore.tryAcquire()){
            System.out.println(Thread.currentThread().getName() + "被拒绝");
            return;
        }
        System.out.println(Thread.currentThread().getName() + "执行业务逻辑");
        Thread.sleep(500);
        semaphore.release();
    }

    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                semaphore.release(10);
                System.out.println("释放所有锁");
            }
        },1000,1000);

        for (int i = 0; i < 10000; i++) {
            try {
                Thread.sleep(10);//模拟每隔10ms就有1个请求进来
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new Thread(() -> {
                try {
                    SemaphoreOne.bizMethod();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
