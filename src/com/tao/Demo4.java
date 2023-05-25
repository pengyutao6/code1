package com.tao;

import org.apache.commons.collections.ArrayStack;

import java.io.PrintStream;

public class Demo4 {
    public static void main(String[] args) {
//        int intValue = 5;
//        double doubleValue = 10.5;
//        System.out.println(intValue*doubleValue);
//        byte byteValue = 50;
//        byteValue = byteValue*0.6;
//        byte byteValue = 50;
//        byteValue = (byte) (byteValue*0.6);
//        System.out.println(byteValue);
//        System.out.println((int)10.66);
//        System.err.println("错误的显示");
//        System.out.println("".toString());
//        System.out.println("a".compareTo("b"));
        boolean isEquals = equals("apple","apple");
        System.out.println(isEquals);
    }

    public static boolean equals(String a,String b){
        int aLength = a.length();
        if(aLength==b.length()){
            char[] c1 = a.toCharArray();
            char[] c2 = b.toCharArray();
            int i = 0;
            while (aLength-- != 0){
                if(c1[i]!=c2[i]){
                    return false;
                }
                i++;
            }
            return true;
        }
        return false;
    }
}
