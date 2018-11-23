package com.evan.javaaaaaaaaa.algorithm.sort;

import java.util.Arrays;

/**
 * Created by evan01.zhang on 2018/11/23.
 * 复杂度 O(n²) 最优 O(n)
 */
public class InsertSort {
    public static void sort(int[] arr) {
        // 以arr[0]为锚，遍历每一个
        for (int i = 1; i < arr.length; i++) {
            // 选择排序过的下一个元素，反向遍历
            for (int j = i; j > 0 && arr[j] > arr[j - 1]; j--) {
                arr[j] = arr[j] ^ arr[j - 1];
                arr[j - 1] = arr[j] ^ arr[j - 1];
                arr[j] = arr[j] ^ arr[j - 1];
            }
        }
    }

    public static void main(String[] args) {
        //待排序数组
        int[] arr = {15, 24, 15, 9, 17, 44, 48, 5, 9, 3, 48, 10};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
