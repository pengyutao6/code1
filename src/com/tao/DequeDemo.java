package com.tao;

import java.util.ArrayDeque;

public class DequeDemo {
    public static void main(String[] args) {
        ArrayDeque<String> deque = new ArrayDeque<>();
        deque.add("tao");
        deque.add("彭宇");
        deque.add("无责清");
        //删除元素
        deque.remove("tao");
        //修改元素
        deque.remove("彭宇");
        deque.add("吴彦");
        //查找元素
        boolean hasWuYan = deque.contains("吴彦");
        System.out.println("是否包含吴彦:"+hasWuYan);
    }
}
