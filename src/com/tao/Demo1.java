package com.tao;



import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.HashMap;


public class Demo1 {
    @JsonField(value = "666")
    public String name;

    public static void main(String[] args) {
        HashMap<String, Integer> title = new HashMap<>();
        title.put("date", 0);
        System.out.println(title.get("date"));
        System.out.println(title.get("date"));
        char i = 'A';

        System.out.println("abc" instanceof String);
        int age = 18;
        modify(18);
        System.out.println(age);
        "".intern();
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("123");
        arrayList.set(0,"0");
        System.out.println(arrayList.get(0));
        arrayList.set(0,"1");
        System.out.println(arrayList.get(0));
        Demo1 demo1 = new Demo1();
        System.out.println(demo1.name);
    }

    public static void modify(int age1){
        age1=30;

    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface JsonField{
        public String value() default "132";
    }
}
