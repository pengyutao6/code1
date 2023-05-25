package com.tao;

/**
 * 接口中所有的方法，成员变量，编译后都会加上public的修饰
 * 成员变量，编译后则是加上public static final变成了常量
 */
public interface Electronic {
    //常量
    String LED = "LED";
    //抽象方法
    int abstractMethod();
    //静态方法
    static boolean isGood(String goods){
        return LED.equals(goods);
    }

    //默认方法
    default void printDescription(){
        System.out.println("日光灯");
    }
}
