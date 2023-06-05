package com.tao;

import java.io.*;
import java.util.Arrays;

/**
 * 题目3：序列化与反序列化
 * 本题要求：将一个POJO的列表pojoList序列化到一个本地的文件，取名为：data.dat：
 * 编写一个方法，以您认为性能最好的逻辑获取attr1=“张三”的pojo对象，打印这个对象的hash码
 * 本题考点：序列化与反序列化是JAVA存储和传输数据的重要手段。
 */
public class Test3 {

    private static Pojo[] pojoList = {new Pojo("李四", "13800138000", "男"), new Pojo("张三", "13800138000", "男"),
            new Pojo("赵六", "13800138000", "男")};

    public static void main(String[] args) {
        // 在这里编写代码
        serializePojoList();
        Pojo pojo = findPojoByAttr1("张三");
        System.out.println("Pojo哈希码：" + pojo.hashCode());
    }

    // 序列化pojoList到文件
    private static void serializePojoList() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data.dat"))) {
            oos.writeObject(pojoList);
            System.out.println("pojoList序列化成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 从文件中反序列化Pojo数组
    private static Pojo[] deserializePojoList() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data.dat"))) {
            return (Pojo[]) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 根据attr1找到对应的Pojo对象
    private static Pojo findPojoByAttr1(String attr1) {
        Pojo[] deserializedPojoList = deserializePojoList();
        if (deserializedPojoList != null) {
            return Arrays.stream(deserializedPojoList)
                    .filter(pojo -> pojo.getAttr1().equals(attr1))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }
}

class Pojo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String attr1;
    private String attr2;
    private String attr3;

    public Pojo(String attr1, String attr2, String attr3) {
        this.attr1 = attr1;
        this.attr2 = attr2;
        this.attr3 = attr3;
    }

    public String getAttr1() {
        return attr1;
    }

    public String getAttr2() {
        return attr2;
    }

    public String getAttr3() {
        return attr3;
    }
}
