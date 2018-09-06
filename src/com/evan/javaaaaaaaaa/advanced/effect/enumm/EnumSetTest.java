package com.evan.javaaaaaaaaa.advanced.effect.enumm;

import java.util.EnumSet;
import java.util.Set;

/**
 * Created by evan01.zhang on 2018/9/6.
 * <p>
 * enumSet & 位域
 */
public class EnumSetTest {

    public enum Style {
        BOLD, ITALIC, UNDERLINE, STRIKETHROUGH;
    }

    public static void applStyles(Set<Style> styles) {

    }

    public static int MON = 1 << 0,
            TUE = 1 << 1,
            WEN = 1 << 2,
            THU = 1 << 3,
            FRI = 1 << 4,
            SAT = 1 << 5,
            SUN = 1 << 6;

    public static void main(String[] args) {
        EnumSetTest.applStyles(EnumSet.of(Style.BOLD, Style.ITALIC));
        EnumSet<Style> enumSet = EnumSet.allOf(Style.class); // 取所有元素生成EnumSet
        System.out.printf(enumSet.toString() + "\n");
        EnumSet<Style> enumSet_none = EnumSet.noneOf(Style.class);// 生成空的EnumSet

        // 11 1011
        // 15 1111
        System.out.println(11 & 15);

        int week = MON | TUE | WEN | THU | FRI | SAT | SUN;                           // 1111111 = 102
        System.out.println("week :         " + week);
        //合并位域结果 用|
        int week_1_4_5 = MON | THU | FRI;                                             // 0011001 = 25
        System.out.println("week_1_4_5 :" + week_1_4_5);
        int week_3_4_5 = WEN | THU | FRI;                                             // 0011100 = 28
        System.out.println("week_3_4_5 :" + week_3_4_5);
        // 去除交集，用^
        System.out.println("week ^ week_1_4_5 :" + (week ^ week_1_4_5));              // 1100110 = 102
        // 求交集，用&
        System.out.println("week & week_1_4_5 :" + (week & week_1_4_5));              // 0011001
        System.out.println("week_3_4_5 & week_1_4_5 :" + (week_3_4_5 & week_1_4_5));  // 0011000 = 24
        System.out.println("week_3_4_5 ^ week_1_4_5 :" + (week_3_4_5 ^ week_1_4_5));  // 0000101 = 5
        System.out.println();
        System.out.println(TUE | WEN | SAT | SUN);


        System.out.println(week & (~week_1_4_5));
        System.out.println(TUE | WEN | SAT | SUN);
    }
}
