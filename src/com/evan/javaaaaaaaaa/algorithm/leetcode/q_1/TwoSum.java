package com.evan.javaaaaaaaaa.algorithm.leetcode.q_1;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by evan01.zhang on 2018/2/8.
 * <p>
 * Questions:
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * <p>
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * <p>
 * Example:
 * Given nums = [2, 7, 11, 15], target = 9,
 * <p>
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 * <p>
 * 原题目地址:https://leetcode.com/problems/two-sum/description/
 *
 * 结论：嵌套循环查找为什么要用数组？？？？
 */
public class TwoSum {


    public static void main(String[] args) {
        int arrayLength = 1000;
        int[] nums = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            nums[i] = i;
        }
//        if (index == null) {
//            System.out.println("no reuslt");
//        } else {
//            System.out.println("index[0]=" + index[0] + "\nindex[1]=" + index[1]);
//        }
        /** 我自己的方案,嵌套循环，
         * 耗时：请看括号当中的，空号内为全遍历查找结果。因为测试代码不好找到足够大的数组(对于a+b=c在数组中有唯一解)。。。。
         *      数组长度100000,执行1次（177288），耗时大概4600(4000-9000)
         *      数组长度10000,执行10次（10000次等太久了）（17728），耗时大概200-250(440)
         *      数组长度1000，执行10000次（1772），耗时大概2200(4400)
         *      数组长度100，执行1000000次（177），耗时大概2200(12000+)
         *      数组长度10，执行10000000次（17），耗时大概500(1350)
         */
//        for (int k = 0; k <= 4; k++) {
//            long startTimeMilis = System.currentTimeMillis();
//            for (int i = 0; i < 10000; i++) {
//                twoSum(nums, 17);
//            }
//            System.out.println(System.currentTimeMillis() - startTimeMilis + "   第" + k + "次");
//        }
//
//        System.out.println("my solotion");
        /** hashMap优化1 空间换时间？，
         * 耗时：
         *      数组长度100000,执行1000次（177288），耗时大概2800(2800)
         *      数组长度10000,执行10000次（17728），耗时大概2000(2300)
         *      数组长度1000，执行10000次（1772），耗时大概200-300(200-260)
         *      数组长度100，执行1000000次（177），耗时大概2000-2500(1800)
         *      数组长度10，执行10000000次（17），耗时大概1000(1100)
         */
        for (int k = 0; k <= 4; k++) {
            long startTimeMilis = System.currentTimeMillis();
            for (int i = 0; i < 10000; i++) {
                twoSum1(nums, 17);
            }
            System.out.println(System.currentTimeMillis() - startTimeMilis);
        }

        System.out.println("solotion1 ----------------- ");
        /** solution3 :
         * 耗时：
         *      数组长度100000,执行1000次（177288），耗时大概2200(2800)
         *      数组长度10000,执行10000次（17728），耗时大概1600(1800)
         *      数组长度1000，执行10000次（1772），耗时大概160-240(170)
         *      数组长度100，执行1000000次（177），耗时大概1150(1500)
         *      数组长度10，执行10000000次（17），耗时大概950(1000)
         */
        for (int k = 0; k <= 4; k++) {
            long startTimeMilis = System.currentTimeMillis();
            for (int i = 0; i < 10000; i++) {
                twoSum2(nums, 17);
            }
            System.out.println(System.currentTimeMillis() - startTimeMilis);
        }

        System.out.println("solotion2 ----------------- ");

    }

    /**
     * my solution
     * <p>
     * 时间复杂度O(n²)、
     * 空间复杂度O(1)
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {

//        int []result = {-1, -1};
        long count = 0;
        int[] result = null;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j < nums.length; j++) {
                count++;
                if (target == nums[i] + nums[j]) {
//                    System.out.println("count ------- " + count);
//                    return new int[]{i, j};
                    result = new int[]{i, j};
                }
            }
        }
        return result;
    }

    /**
     * 方案二
     * 借助hashmap，降低time complexity
     * 时间复杂度：O(n) 由于jdk1.8引入了红黑树，某些时候hashmap查找的复杂度为O(logn)，这里可以不考虑
     * 空间复杂度：O(n) --- 多了一个hashMap，大小受nums的大小影响
     */

    public static int[] twoSum1(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            //借助map中，确定位置采用的hash算法，降低循环次数（TreeNode不考虑------）
            if (map.containsKey(complement) && map.get(complement) != i) {
                //return new int[]{i, map.get(complement)};
            }
        }
        return null;
        //throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * 方案3
     *
     * 对比方案1：查找次数一半。。。。。。。
     * 快的原因：回查，map不是一次性创建完成的。
     */
    public static int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i]; //
            if (map.containsKey(complement)) { //
                //return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i); //Key = 数组中的值，value = 数组中的下标
        }
        return null;
        //throw new IllegalArgumentException("No two sum solution");
    }

}
