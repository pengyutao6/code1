package com.tao;

import java.util.Iterator;
import java.util.LinkedList;

public class QueueDemo {
    /**
     * 底层实现方式不同：LinkedList 是基于链表实现的，而 ArrayDeque 是基于数组实现的。随机访问的效率不同：
     * 由于底层实现方式的不同，LinkedList 对于随机访问的效率较低，时间复杂度为 O(n)，而 ArrayDeque 可以通过下标随机访问元素，时间复杂度为 O(1)。
     * 迭代器的效率不同：LinkedList 对于迭代器的效率比较低，因为需要通过链表进行遍历，时间复杂度为 O(n)，而 ArrayDeque 的迭代器效率比较高，因为可以直接访问数组中的元素，时间复杂度为 O(1)。
     * 内存占用不同：由于 LinkedList 是基于链表实现的，它在存储元素时需要额外的空间来存储链表节点，因此内存占用相对较高，而 ArrayDeque 是基于数组实现的，内存占用相对较低
     * @param args
     */
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
