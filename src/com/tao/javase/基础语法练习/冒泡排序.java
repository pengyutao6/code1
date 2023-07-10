package com.tao;

public class 冒泡排序 {

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
}
