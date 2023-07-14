package com.tao.javase.基础语法练习;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Stream类练习 {


    /*
        推荐Stream教程文章:https://juejin.cn/post/7118991438448164878#comment
        注意：Stream流一旦调用了终止操作的方法，下一次再次调用它的方法，会抛出异常：stream has already been operated upon or closed
     */

    @Test
    public void testGetTargetUsers(){
        List<String> ids = Arrays.asList("205","10","308","49","627","193","111", "193");
        List<User> collect = ids.stream().filter(s -> s.length() > 2)
                .distinct()
                .map(Integer::valueOf)
                .limit(3)
                .map(id -> {
                    User user = new User();
                    user.setId(id);
                    return user;
                }).collect(Collectors.toList());
        System.out.println(collect);
        /*
            1、使用filter过滤掉不符合条件的数据
            2、通过distinct对存量元素进行去重操作
            3、通过map操作将字符串转成整数类型
            4、借助sorted指定按照数字大小正序排列
            5、使用limit截取排在前3位的元素
            6、又一次使用map将id转为Dept对象类型
            7、使用collect终止操作将最终处理后的数据收集到list中
       */
    }

    @Test
    public void testPeekAndForeach() {
        List<String> sentences = Arrays.asList("hello world","Jia Gou Wu Dao");
        // 演示点1： 仅peek操作，最终不会执行
        System.out.println("----before peek----");
        sentences.stream().peek(sentence -> System.out.println(sentence));
        System.out.println("----after peek----");
        System.out.println();
        // 演示点2： 仅foreach操作，最终会执行
        System.out.println("----before foreach----");
        sentences.stream().forEach(sentence -> System.out.println(sentence));
        System.out.println("----after foreach----");
        System.out.println();
        // 演示点3： peek操作后面增加终止操作，peek会执行
        System.out.println("----before peek and count----");
        sentences.stream().peek(sentence -> System.out.println(sentence)).count();
        System.out.println("----after peek and count----");
    }

    @Test
    //flatMap 将已有元素转换为另一个对象类型，一对多逻辑，即原来一个元素对象可能会转换为1个或者多个新类型的元素，返回新的stream流
    public void stringToFlatmap() {
        List<String> sentences = Arrays.asList("hello tao","A B C D");
        // 使用流操作
        List<String> results = sentences.stream()
                .flatMap(sentence -> Arrays.stream(sentence.split(" ")))
                .collect(Collectors.toList());
        System.out.println(results);
    }

    @Test
    //map 将已有元素转换为另一个对象类型，一对一逻辑，返回新的stream流
    public void strToMap(){
        List<String> ids = Arrays.asList("201", "106", "208", "479", "127", "192", "121");
        // 使用流操作
        List<User> results = ids.stream().map(id -> {
            User user = new User();
            user.setId(Integer.parseInt(id));
            return user;
        }).collect(Collectors.toList());

        System.out.println(results);
    }

    class User{
        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    '}';
        }
    }
}
