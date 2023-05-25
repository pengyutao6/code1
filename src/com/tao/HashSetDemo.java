package com.tao;

import java.util.HashSet;

public class HashSetDemo {
    public static void main(String[] args) {
        HashSet<String> set = new HashSet<>();
        set.add("tao");
        set.add("彭宇");
        set.add("无责清");
        //output:true
        System.out.println(set.contains("tao"));
        System.out.println(set.remove("tao"));
        System.out.println(set);
    }
}
