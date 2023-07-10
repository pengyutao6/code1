package com.tao;

public class 二分查找法 {
    /**
     *
     * @param nums  数组
     * @param target 目标值
     * @return -1表示未找到
     */
    public int binarySearch(int[] nums,int target){
        int left = 0;
        int right = nums.length - 1;
        while (left<=right){
            //中间值
            int mid = left + (left+right)/2;
            if (nums[mid] == target){
                return mid;
            }else if(nums[mid] < target){
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
