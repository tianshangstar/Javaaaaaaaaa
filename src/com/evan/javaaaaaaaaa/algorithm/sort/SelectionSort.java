package com.evan.javaaaaaaaaa.algorithm.sort;

import java.util.Arrays;

/**
 * Created by evan01.zhang on 2018/11/23.
 * 选择排序 复杂度O(n²)
 */
public class SelectionSort {
    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            // mark index
            int m = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] < arr[j]) {
                    m = j;
                }
            }
            if (m != i) {
                arr[i] = arr[i] ^ arr[m];
                arr[m] = arr[i] ^ arr[m];
                arr[i] = arr[i] ^ arr[m];
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
