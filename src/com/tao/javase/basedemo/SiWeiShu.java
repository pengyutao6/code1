package com.tao.javase.basedemo;

public class SiWeiShu {
    public static void main(String[] args) {
        //1.定义一个变量，赋值为一个四位数整数，例如1234
        int num = 1234;

        //2.通过运算操作求出个位，十位，百位，千位
        int ge = num%1000%100%10;
        int shi = num%1000%100/10;
        int bai = num%1000/100;
        int qian = num/1000;

        System.out.println(num + "这个四位数个位上的数字是：" + ge);
        System.out.println(num + "这个四位数十位上的数字是：" + shi);
        System.out.println(num + "这个四位数百位上的数字是：" + bai);
        System.out.println(num + "这个四位数千位上的数字是：" + qian);
    }
}
