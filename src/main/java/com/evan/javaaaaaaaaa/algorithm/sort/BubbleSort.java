package com.evan.javaaaaaaaaa.algorithm.sort;

import java.util.Arrays;

/**
 * Created by evan01.zhang on 2018/11/23.
 * 冒泡排序 复杂度O(n²)
 */
public class BubbleSort {
    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    //交换j和j+1
                    arr[j] = arr[j] ^ arr[j + 1];
                    arr[j + 1] = arr[j] ^ arr[j + 1];
                    arr[j] = arr[j] ^ arr[j + 1];
                    System.out.println(Arrays.toString(arr));
                }
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
