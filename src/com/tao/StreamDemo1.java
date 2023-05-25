package com.tao;

import java.util.Arrays;
import java.util.stream.Stream;

public class StreamDemo1 {
    public static void main(String[] args) {
        String [] cmwers = {"涛","锋","码农"};
        Arrays.asList(cmwers).stream().forEach(s -> System.out.println(s));
        System.out.println("------------------------");
        Stream.of(cmwers).forEach(System.out::println);
        System.out.println("------------------------");
        Arrays.stream(cmwers).forEach(System.out::println);
        System.out.println("------------------------");
        System.out.println(Arrays.asList(cmwers).toString());
        System.out.println("------------------------");
        //打印二维数组
        String[][] deepArray = new String[][] {{"沉默", "涛"}, {"一枚无趣的程序员"}};
        System.out.println(Arrays.deepToString(deepArray));
    }
}
