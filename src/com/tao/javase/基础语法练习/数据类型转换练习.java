package com.tao.javase.基础语法练习;



public class 数据类型转换练习 {

    public static void main(String[] args) {
        short s1 = 120;
        short s2 = 8;
        //语法错误
//        short s3 = s1 + s2;

        short b1 = 120;
        short b2 = 8;
        byte b3 = (byte)(b1 + b2);
        //-128
        System.out.println(b3);

        char c1 = '0';
        char c2 = '1';
        //语法错误
//        char c3 = c1 + c2;
        //97,它们各自转换成ASCII码
        System.out.println(c1 + c2);
        int i = 4;
        long j = 120;
        //double 后面的d可以省略不写
        double d = 34;
        //float 后面必须带f
        float f = 1.2f;

        System.out.println(i + j + d + f);

        int a = 1;
        int b = 2;
        double result = a/b;
        //0.0
        System.out.println(result);
    }
}
