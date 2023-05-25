package com.tao;

import java.util.ArrayList;

public class Demo2 {
    public static void main(String[] args) {
        System.out.println((double)(3*0.1));
        System.out.println((float)(3*0.1));
        ArrayList<String> list = new ArrayList<String>();
        list.add("a");
        list.add("ab");
        list.add("abc");
        list.forEach(i-> System.out.println(i));
    }
}
