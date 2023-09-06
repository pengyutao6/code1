package com.tao.guavademo;

import com.google.common.util.concurrent.RateLimiter;

public class GuavaRateLimiterDemo {
    //一秒生成10个令牌,相当于100ms一个令牌
    private RateLimiter rateLimiter = RateLimiter.create(10);

    /*
        模拟执行业务方法
     */
    public void exeBiz(){
        if(rateLimiter.tryAcquire(1)){
            try {
                Thread.sleep(500);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("线程"+Thread.currentThread().getName()+" 执行业务逻辑成功!");
        }else{
            System.out.println("线程"+Thread.currentThread().getName()+" 被限流了");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        GuavaRateLimiterDemo guavaRateLimiterDemo = new GuavaRateLimiterDemo();
        Thread.sleep(500);
        for (int i = 0; i < 100; i++) {
            new Thread(guavaRateLimiterDemo::exeBiz).start();
        }
    }
}
