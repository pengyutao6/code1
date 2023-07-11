package com.tao;

public class 二分查找法 {
    /**
     *
     * @param nums  数组
     * @param target 目标值
     * @return -1表示未找到
     */
    public int binarySearch2(int[] nums,int target){
        //左指针
        int left = 0;
        //右指针
        int right = nums.length-1;
        while (left<=right){
            int mid = left + (left+right)/2;
            //找到目标值，返回下标值
            if(nums[mid]==target){
                return mid;
                //如果目标值大于中间值，那么说明目标值可能在右侧区域
                //所以让左指针往右折
            }else if(nums[mid]<target){
                left = mid + 1;
            }else{
                //如果目标值小于中间值，那么说明目标值可能在左侧区域
                //所以让右指标往左折
                right = mid -1;
            }
        }
        return -1;
    }
}
