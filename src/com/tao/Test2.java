package com.tao;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 题目2：复合字段的排序
 * 本题考点：通过各类排序工具，对复合的排序字段进行排序：
 * 对StringList数组里面的内容进行排序，使其能按照下列顺序输出
 *  200-10_09.12.A00001
 *  200-10_10.12.A00011
 *  200-11_09.12.A00001
 *
 * 本题考点：JAVA对字符内数字的排序可以使用工具类或者实现其compare函数进行比较排序。
 */
public class Test2 {

    private static String[] StringList = {"200-10_09.12.A00001", "200-11_09.12.A00011", "200-10_10.12.A00001"};

    public static void main(String[] args) {
        String paramStr = "A";
        // 在这里编写代码
        Arrays.sort(StringList, new CustomComparator());
        for (String str : StringList) {
            System.out.println(str);
        }
    }

    // 自定义比较器，按照指定顺序排序
    static class CustomComparator implements Comparator<String> {

        @Override
        public int compare(String str1, String str2) {
            // 自定义排序规则
            String[] parts1 = str1.split("\\.");
            String[] parts2 = str2.split("\\.");

            int result = parts1[0].compareTo(parts2[0]); // 按照第一个字段排序
            if (result == 0) {
                result = parts1[1].compareTo(parts2[1]); // 按照第二个字段排序
                if (result == 0) {
                    result = parts1[2].compareTo(parts2[2]); // 按照第三个字段排序
                    if (result == 0) {
                        result = parts1[3].compareTo(parts2[3]); // 按照第四个字段排序
                    }
                }
            }

            return result;
        }
    }
}

