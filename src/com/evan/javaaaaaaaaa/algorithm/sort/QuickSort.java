package com.evan.javaaaaaaaaa.algorithm.sort;

import java.util.Random;

/**
 * Created by evan01.zhang on 2018/11/4.
 */
public class QuickSort {

    private static void sort(int[] arr) {
        // 从数组下标0开始，进入快排。
//        int anchor = 0;
//        int anchor = qsort(arr, 0, arr.length);
        sort(arr, 0, arr.length);
//        sort(arr, anchor + 1, arr.length);
    }

    private static void sort(int[] arr, int start, int end) {
//        System.out.printf("anchor = %d, end = %d \n", start, end);
        if (start < end) {
            // 根据单词快排返回的分割点，重新拆分数组，递归快排。
            int split = qsort(arr, start, end);
//            System.out.printf("newAnchor = %d, anchor = %d, end = %d \n", split, start, end);
//            if (split > start) {
            sort(arr, start, split);
//            }
//            if (end > split) {
            sort(arr, split + 1, end);
//            }
        }
    }

    /**
     * 进行一轮快排
     *
     * @param arr   原数组
     * @param start 锚点： 即用来分割数组的index
     */
    private static int qsort(int[] arr, int start, int end) {
        // 快排左右分割的锚点
        int key = arr[start];
        // 快排数组遍历指针，从锚点向后开始，锚点前面已经快排过了
        int p = start + 1;
        // 将锚点的下一位初始化为缓存（用来交换的。）
        int cache = p;
        while (p < end) {
            // 比key小的，交换
//            System.out.printf("arr[p] = %d", arr[p]);
            if (arr[p] < key) {
//                System.out.printf("before : arr[p] = %d, arr[cache] = %d\n", arr[p], arr[cache]);
                int temp = arr[p];
                arr[p] = arr[cache];
                arr[cache] = temp;
                cache++;
//                System.out.printf("after : arr[p] = %d, arr[cache] = %d\n", arr[p], arr[cache]);
            } else {
                // 更新缓存点
//                cache = p;
            }
//            System.out.printf(" cache is : %d   arr is : %s\n", cache, Arrays.toString(arr));
            p++;
        }
        // 最终，交换key和cache
        arr[start] = arr[--cache];
        arr[cache] = key;
        // 返回最终排好序的数组的中间点
//        System.out.printf("cache = %d, anchor = %d qsrot : %s\n", cache, start, Arrays.toString(arr));
//        System.out.println("-------------------------------------");
        // 将后续分组依据返回
        return cache;
    }


    public static void main(String[] args) {
        //待排序数组
//        int[] arr = {15, 24, 15, 9, 17, 44, 48, 5, 9, 3, 48, 10};
        int[] arr = new int[1000000];
        int[] arr1 = new int[1000000];
        Random rand = new Random(47);
        for (int i = 0; i < 1000000; i++) {
            int random = rand.nextInt(88);
            arr[i] = arr1[i] = random;
        }
        long q1start, q1end, q2end;
        q1start = System.nanoTime();
        quick(arr1);
        q1end = System.nanoTime();
        sort(arr);
        q2end = System.nanoTime();
        System.out.printf("q1cost = %d, q2cost = %d", q1end - q1start, q2end - q1end);
        // out q1cost = 1852705469, q2cost = 1373557165
//        System.out.println(Arrays.toString(arr));
    }

    /**
     * 查找出中轴（默认是最低位low）的在numbers数组排序后所在位置
     *
     * @param numbers 带查找数组
     * @param low     开始位置
     * @param high    结束位置
     * @return 中轴所在位置
     */
    public static int getMiddle(int[] numbers, int low, int high) {
        int temp = numbers[low]; //数组的第一个作为中轴
        while (low < high) {
            while (low < high && numbers[high] >= temp) {
                high--;
            }
            numbers[low] = numbers[high];//比中轴小的记录移到低端
            while (low < high && numbers[low] < temp) {
                low++;
            }
            numbers[high] = numbers[low]; //比中轴大的记录移到高端
        }
        numbers[low] = temp; //中轴记录到尾
        return low; // 返回中轴的位置
    }

    /**
     * @param numbers 带排序数组
     * @param low     开始位置
     * @param high    结束位置
     */
    public static void quickSort(int[] numbers, int low, int high) {
        if (low < high) {
            int middle = getMiddle(numbers, low, high); //将numbers数组进行一分为二
            quickSort(numbers, low, middle - 1);   //对低字段表进行递归排序
            quickSort(numbers, middle + 1, high); //对高字段表进行递归排序
        }

    }

    /**
     * 快速排序
     *
     * @param numbers 带排序数组
     */
    public static void quick(int[] numbers) {
        if (numbers.length > 0)   //查看数组是否为空
        {
            quickSort(numbers, 0, numbers.length - 1);
        }
    }
}
