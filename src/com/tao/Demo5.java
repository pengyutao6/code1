package com.tao;

public class Demo5 {
    public static void main(String[] args) {
        String result = "";
        Long startTime = System.currentTimeMillis();
//        for (int i = 0; i < 100000; i++) {
//            result += "六六六";
//        }
//        System.out.println(System.currentTimeMillis()-startTime);
        startTime = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            sb.append("六六六");
        }
        System.out.println(System.currentTimeMillis()-startTime);
    }
}
