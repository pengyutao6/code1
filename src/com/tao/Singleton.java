package com.tao;

public class Singleton {
    //正常类单例
    private volatile static Singleton singleton;
    //将默认构造器私有化
    private Singleton(){

    }
    public static Singleton getSingleton(){
        if(singleton == null){
            synchronized (Singleton.class){
                if(singleton == null){
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

    //枚举类单例
    public enum EasySingleton{
        INSTANCE;
    }
}
