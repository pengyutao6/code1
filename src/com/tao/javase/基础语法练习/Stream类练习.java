package com.tao.javase.基础语法练习;

import org.docx4j.wml.U;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Stream类练习 {


    /*
        推荐Stream教程文章:https://juejin.cn/post/7118991438448164878#comment
     */


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
