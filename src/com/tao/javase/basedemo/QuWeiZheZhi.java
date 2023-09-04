package com.tao.javase.basedemo;

public class QuWeiZheZhi {
    public static void main(String[] args) {
        //世界最高山峰是珠穆朗玛峰，它的高度是8848.86米，假如我有一张足够大的纸，它的厚度是0.1毫米。请问，我折叠多少次，可以折成珠穆朗玛峰的高度?
        int count = 0;
        int height = (int)8848.86*1000;

        for (double i = 0.1; i < height; i*=2) {
            count +=1;
        }
        //27次
        System.out.println("折叠次数："+count);
    }

}
