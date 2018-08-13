package com.evan.javaaaaaaaaa.algorithm.other;

/**
 * Created by evan01.zhang on 17/4/10.
 */
public class SheepAndWater {

    public static void main(String[] args) {

        int poison = (int) (Math.random() * 100); // 随机一瓶水中有毒
        //             0  2  4  8  16 32 64
        int[] sheep = {0, 0, 0, 0, 0, 0, 0}; // 代表七只羊

        int[] bottles = {0, 0, 1, 0, 0, 1, 1}; // 用二进制表示瓶子,

        int[][] sheep_bottle = new int[100][7];

        for (int i = 1; i <= 100; i++) { //水瓶的循环
            // 返回的数组表示那几只羊来喝这瓶水,比如{ 1, 1, 0, 0, 0, 0, 1}就表示第1、2、7只羊需要喝这瓶水
            int[] targetSheep = parseIntToArr(i);
            sheep_bottle[i - 1] = targetSheep;
        }

        int count = 0;

        for (int[] maybe : sheep_bottle) {
            count++;
            System.out.print("{");
            for (int a : maybe) {
                System.out.print(" " + a);
            }
            System.out.println("}" + " 这是第" + count + "瓶水被哪些羊喝过的数组展示");
        }

        /**
         * 根据哪些羊死了,就可以知道第几瓶水有毒了。。。。
         */
    }

    public static int[] parseIntToArr(int targetInt) {

        int[] array = new int[7];
        int count = 0;
        for (int i = 1; i >= 0 && count < array.length; i <<= 1) {
            if ((targetInt & i) != 0) {
                array[count++] = 1;
            } else {
                array[count++] = 0;
            }
        }
//        System.out.print("{");
//        for (int a : array) {
//            System.out.print(a + ",");
//        }
//        System.out.println("}" + " 这是第" + targetInt + "种情况");

        return array;
    }

}
