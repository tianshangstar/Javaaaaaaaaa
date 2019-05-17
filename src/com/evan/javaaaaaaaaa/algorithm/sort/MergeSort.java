package com.evan.javaaaaaaaaa.algorithm.sort;

import java.util.Arrays;

/**
 * Created by evan01.zhang on 2018/11/4.
 * 复杂度 O(N*logN)
 */

public class MergeSort {

    /**
     * 思路： 将原数组递归拆分，直到拆分出的最小数组长度为1
     * merge：对比左右两个数组的首元素，取较大（较小）的一个按位置放入新数组的指定位置
     *
     * @param arr
     */
    private static void sort(int[] arr) {
        // 将原数组拆分为左右两半（长度%2不为0无所谓哪边大小结果不受影响）
        //左半数组
        int[] tempArr = new int[arr.length];
        sort(arr, 0, arr.length - 1, tempArr);
    }

    private static void sort(int[] arr, int left, int right, int[] tempArr) {
        if (left < right) {
            //拆分数组并递归
            int mid = (left + right) / 2;
            //分别排序左右数组
            sort(arr, left, mid, tempArr);
            sort(arr, mid + 1, right, tempArr);
            //将排好序的左右两个数组合并。
            merge1(arr, left, right, tempArr);
        }
    }


    /**
     * 递归合并
     * 但是要保证进入合并的左右两边都是排好序的
     * 所以，首先要将原数组拆分的最小颗粒度进行递归归并。
     *
     * @param arr     原数组
     * @param left    原数组归并起始左边界
     * @param right   原数组归并结束右边界，与left参数共同约定当前排序中需要拆分的数组范围
     * @param tempArr 保存归并排序的结果
     */
    private static void merge(int[] arr, int left, int right, int[] tempArr) {
        // 所以，这里归并的最优写法怎么玩。。。
        // 1、左数组指针pLeft
        int pLeft = left;
        // 2、左右数组边界mid
        int mid = (left + right) / 2;
        // 3、右数组指针pRight
        int pRight = mid + 1;
        // 4、循环
        int count = right - left + 1;
        // 5、遍历进度 & tempArr指针
        int tempIndex = left;
        while (tempIndex < count + left) {
            // 如果左数组遍历完成，只处理右
            if (pLeft > mid) {
                tempArr[tempIndex] = arr[pRight++];
            } else if (pRight > right) {// 如果右数组遍历完，只处理左
                tempArr[tempIndex] = arr[pLeft++];
            } else if (pLeft <= mid && pRight <= right) {// 左右数组指针同时在边界内，比较当前指针所指向的值，并移动取出的值对应的指针
                if (arr[pLeft] < arr[pRight]) {
                    // 赋值 指针移动
                    tempArr[tempIndex] = arr[pLeft++];
                } else {
                    // 赋值 指针移动
                    tempArr[tempIndex] = arr[pRight++];
                }
            }
            tempIndex++;
        }

        // tempArr回写到arr
        int rewriteIndex = left;
        while (rewriteIndex <= right) {
            arr[rewriteIndex] = tempArr[rewriteIndex++];
        }
        System.out.printf("merge end : left = %d; right = %d; arr is : %s \n", left, right, Arrays.toString(arr));
    }

    /**
     * 参照其他人解题思路后优化部分。
     * @param arr
     * @param left
     * @param right
     * @param tempArr
     */
    private static void merge1(int[] arr, int left, int right, int[] tempArr) {
        // 所以，这里归并的最优写法怎么玩。。。
        // 1、左数组指针pLeft
        int pLeft = left;
        // 2、左右数组边界mid
        int mid = (left + right) / 2;
        // 3、右数组指针pRight
        int pRight = mid + 1;
        // 4、循环
        int count = right - left + 1;
        // 5、遍历进度 & tempArr指针
        int tempIndex = left;
        while (pLeft <= mid && pRight <= right) {
            if (pLeft <= mid && pRight <= right) {// 左右数组指针同时在边界内，比较当前指针所指向的值，并移动取出的值对应的指针
                if (arr[pLeft] < arr[pRight]) {
                    // 赋值 指针移动
                    tempArr[tempIndex++] = arr[pLeft++];
                } else {
                    // 赋值 指针移动
                    tempArr[tempIndex++] = arr[pRight++];
                }
            }
        }

        while (pLeft <= mid) {
            tempArr[tempIndex++] = arr[pLeft++];
        }

        while (pRight <= right) {
            tempArr[tempIndex++] = arr[pRight++];
        }
        // tempArr回写到arr
        int rewriteIndex = left;
        while (rewriteIndex <= right) {
            arr[rewriteIndex] = tempArr[rewriteIndex++];
        }
        System.out.printf("merge end : left = %d; right = %d; arr is : %s \n", left, right, Arrays.toString(arr));
    }

    public static void main(String[] args) {
        //待排序数组
        int[] arr = {15, 24, 15, 9, 17, 44, 48, 5, 9, 3, 48, 10};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
