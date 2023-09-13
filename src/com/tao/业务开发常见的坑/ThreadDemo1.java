package com.tao.业务开发常见的坑;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadDemo1 {
    //通过使用ThreadLocal保证SimpleDateFormat对象的安全性
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static ExecutorService executorService = Executors.newFixedThreadPool(10);
    private static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected SimpleDateFormat initialValue(){
            return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        }
    };
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(date(finalI));
                }
            });
        }
    }

    public static String date(int seconds){
        Date date = new Date(1000*seconds);
        return threadLocal.get().format(date);
    }
}
