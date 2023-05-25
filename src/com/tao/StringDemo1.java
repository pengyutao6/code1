package com.tao;

public final class StringDemo1{

    public static void main(String[] args) {
        String str1 = "涛";
        String str2 = "涛";
        System.out.println(str1.hashCode());
        System.out.println(str2.hashCode());
        String str3 = new String("涛");
        String str4 = new String("涛");
        System.out.println(str3.hashCode());
        System.out.println(str4.hashCode());
        String str5 = "它";
        String str6 = str5.intern();
        System.out.println(str5==str6);
        //对象放堆里面,因为是否字符串,所以会判断是否有无在字符串常量池,无则创建,有则不做处理
        String str7 = new String("塔");
        //intern(),这个方法判断str7是否存在字符串常量池,因为有了,直接引用,所以地址是常量池,上一个地址是堆,不同地方,所以为false
        //上面字符串"它",因为不是new对象,属于直接声明一个常量,所以地址都是常量池,则两者都相等
        String str8 = str7.intern();
        System.out.println("intern()比较对象:"+str7==str8);
        System.out.println("-----------------------------");
        String str9 = new String("小弟")+new String("大哥");
        String str10 = new StringBuilder().append("小弟").append("大哥").toString();
        //str9创建方式等同于str10
        //最后创建了小弟大哥的字符串在常量池
        System.out.println(str9==str9.intern());
        System.out.println(new String("A").intern()=="A");
        //被final修饰的变量后,如果再次赋值,变编译错误;并且要注意要初始化赋值,不然也会编译错误;
        final int finalInt = 6;
        //为什么String设计中,加上final修饰
        //1、线程安全(多线程使用中,不需要同步处理,比如trim(),subString(),toLowerCase(),都会返回一个新的值,不影响旧值)
        //2、Hashcode不可变(缓存Hashcode,多次调用只返回一个值,提高效率)
        //3、实现字符串常量池(减少JVM内存消耗,提高效率)
        //被final修饰的类,为不可变类,不能被继承
    }
}
