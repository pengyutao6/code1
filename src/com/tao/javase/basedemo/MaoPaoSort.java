package com.tao.javase.basedemo;

public class MaoPaoSort {

    public static void main(String[] args) {
        int arr[] = {1,6,1,5,2,6,7,3,7,82,-1};
        int newArr[] = bubbleSort(arr);
        for (int i = 0; i < newArr.length; i++) {
            System.out.println(newArr[i]);
        }
    }

    public static int[] bubbleSort(int[] arr){
        int length = arr.length;
        for (int i = 0; i < length-1; i++) {
            for (int j = 0; j < length-i-1; j++) {
                if(arr[j]>arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        return arr;
    }
    /*
        两个for循环，一个是每一个元素循环从左右到右遍历比较每个元素
                一个是遍历每个元素进行上面的比较
            然后在最里面的循环，进行元素比较，如果左边大于右边，则进行位置替换
     */
    public static int[] bubbleSort2(int[] arr){
        int length = arr.length;
        for (int i = 0; i < length-1; i++) {
            for (int j = 0; j < length-i-1; j++) {
                if(arr[j]>arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        return arr;
    }

}
