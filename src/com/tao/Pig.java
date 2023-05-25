package com.tao;


public class Pig implements Fly,Run{

    @Override
    public void fly() {
        System.out.println("飞");
    }

    @Override
    public void run() {
        System.out.println("跑");
    }

    public static void main(String[] args) {
        Pig pig = new Pig();
        pig.fly();
        pig.run();
    }
}

