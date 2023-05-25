package com.tao;

import cn.hutool.crypto.SecureUtil;

public class HashCodeDemo {
    public static void main(String[] args) {
        System.out.println(hashCode("666"));
        System.out.println("666".hashCode());
        System.out.println(SecureUtil.md5("a"));
    }
   private static int hash;
    //仿写hashCode的方法
   public static int hashCode(String value){
        int h = hash;
        if(h == 0 && value.length() > 0){
            char val[] = value.toCharArray();

            for (int i = 0; i <value.toCharArray().length; i++) {
                h = 31 * h + val[i];
            }
            hash = h;
        }
        return h;
   }
}
