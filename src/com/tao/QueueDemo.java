package com.tao;

import java.util.Iterator;
import java.util.LinkedList;

public class QueueDemo {
    public static void main(String[] args) {
        //创建一个LinkedList对象
        LinkedList<String> queue = new LinkedList<>();
        //添加元素
        queue.offer("tao");
        queue.offer("彭宇");
        queue.offer("无责清");
        //输出[tao,彭宇,无责清]
        System.out.println(queue);
        //删除元素
        queue.poll();
        //输出[彭宇,无责清]
        System.out.println(queue);
        //修改元素
        String first = queue.poll();
        queue.offer("tao2");
        System.out.println("删除的元素:"+first);
        System.out.println(queue);
        //查找元素
        System.out.println(queue.get(0));
        System.out.println(queue.contains("无责清"));
        //使用迭代器查询
        Iterator<String> iterator = queue.iterator();
        while (iterator.hasNext()){
            String element = iterator.next();
            if (element.equals("tao2")){
                System.out.println("查询到:"+element);
                break;
            }
        }
        System.out.println("______________________________");

    }
}
