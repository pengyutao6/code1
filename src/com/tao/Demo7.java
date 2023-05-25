package com.tao;

import jxl.demo.Demo;

public class Demo7 extends Demo6{
    public static void main(String[] args) {
        Demo7 demo7 = new Demo7();

        System.out.println(demo7 instanceof Demo6);
        //只有当instanceof为true 才能进行类型强制转换
        if(demo7 instanceof Demo6){
            Demo6 demo6 = (Demo6) demo7;
            System.out.println(demo6);
        }
        Demo6 demo6 = new Demo6();
        System.out.println(demo6);
        work(new Police());
        work(new FireMen());
    }

    public static void work(Police police){
       Police.governanceSecurity();
    }

    public static void work(FireMen fireMen){
        FireMen.outFire();
    }
}
