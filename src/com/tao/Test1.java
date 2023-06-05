package com.tao;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * 题目1：模式的使用
 * 完成编写testMethod方法代码,实现如下效果：
 * 当paramStr为A时，调用A类的aPrintln方法
 * 当paramStr为B时，调用B类的aPrintln方法
 * 当paramStr为C时，调用C类的aPrintln方法
 *
 * 不允许出现if，switch语句，出现即为不合格.
 * 开卷考试，允许使用互联网查询相关帮助。
 * 本题考点：理解模式的使用。使用合理的模式能极大提高代码复用性和可维护性，同时能让程序员更好的梳理业务逻辑
 *
 * 使用策略模式来实现调用不同类的方法，根据paramStr选择合适的策略执行。
 *
 * 注意：为了方便演示，我给每个类的aPrintln方法添加了一个参数，在实际使用中可以根据实际需求进行修改。
 *
 * 另外，根据Java命名规范，类名应该以大写字母开头，因此我将接口`printlnString`改为`PrintlnString`。
 *
 *
 */
public class Test1 {

    // 定义一个策略接口的Map，用于存储不同策略的实现类
    private static Map<String, Consumer<String>> strategyMap = new HashMap<>();

    // 静态代码块，在类加载时初始化策略Map
    static {
        strategyMap.put("A", new A()::aPrintln);
        strategyMap.put("B", new B()::aPrintln);
        strategyMap.put("C", new C()::aPrintln);
    }

    private static void testMethod(String paramStr) {
        // 根据paramStr选择对应的策略，执行相应的方法
        strategyMap.getOrDefault(paramStr, (s) -> {
            throw new IllegalArgumentException("Invalid paramStr: " + paramStr);
        }).accept(paramStr);
    }

    public static void main(String[] args) {
        String paramStr = "A";
        testMethod(paramStr);
    }
}

class A {
    public void aPrintln(String a) {
        System.out.println("A: " + a);
    }
}

class B {
    public void aPrintln(String a) {
        System.out.println("B: " + a);
    }
}

class C {
    public void aPrintln(String a) {
        System.out.println("C: " + a);
    }
}
