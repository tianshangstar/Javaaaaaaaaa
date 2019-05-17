package com.evan.javaaaaaaaaa.advanced.effect.clone.object;

import java.util.Arrays;

/**
 * 深拷贝实现
 */
public class DeepClone implements Cloneable {
    int[] ints = {1, 2, 3};
    int[][] ints_2 = {{10, 11}, {20, 21}, {30, 31}};

    MyInt[] ints_myint = {new MyInt(1), new MyInt(2), new MyInt(3)};

    MyInt[][] ints_myint_2 = {{new MyInt(10), new MyInt(11)}, {new MyInt(20), new MyInt(21)}, {new MyInt(30), new MyInt(31)}};

    public static class MyInt implements Cloneable {
        private int i = 0;


        public MyInt(int i) {
            this.i = i;
        }

        @Override
        public String toString() {
            return "MyInt{" +
                    "i=" + i +
                    '}';
        }

        @Override
        protected MyInt clone() throws CloneNotSupportedException {
            return (MyInt) super.clone();
        }

    }


    @Override
    protected DeepClone clone() throws CloneNotSupportedException {
//        return super.clone(); 如果只调用super.clone，那么下面的数组元素修改，会同步给clone对象
        DeepClone ca = (DeepClone) super.clone(); // 调用super.clone创建对象

        ca.ints = this.ints.clone();// 修正需要修正的属性，对于原始数据类型的一维数组，可以直接调用clone来处理

        ca.ints_2 = this.ints_2.clone(); // 原始数据类型的二维数组，不可以这样clone，数组没多出一个维度，在这里可以理解为多出一层对象继承关系
//        System.arraycopy(this.ints_2, 0, ca.ints_2, 0, this.ints_2.length); // system.arraycopy也是浅拷贝，对于基本数据类型的一维数组可用。

//        ca.ints = this.ints.clone();// 非原始数据的数组，直接调用clone会发现数组对象的引用变了，但是数组的内容引用没变。
        // 递归创建新对象来实现引用的深拷贝，二维数组同。
        ca.ints_myint = new MyInt[this.ints_myint.length];
        for (int i = 0; i < this.ints_myint.length; i++) {
            ca.ints_myint[i] = this.ints_myint[i].clone();
        }
//        System.arraycopy(this.ints, 0, ca.ints, 0, this.ints.length);
        ca.ints_myint_2 = this.ints_myint_2.clone();

        return ca;
    }

    @Override
    public String toString() {
        return "DeepClone{" +
                "\nints=" + Arrays.toString(ints) + "," +
                "\nints_2=" + Arrays.deepToString(ints_2) + "," +
                "\nints_myint=" + Arrays.toString(ints_myint) + "," +
                "\nints_myint_2=" + Arrays.deepToString(ints_myint_2) +
                "\n}\n\n";
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        DeepClone ca = new DeepClone();
        DeepClone ca_clone = ca.clone();

        ca.ints[1] = 99;

        ca.ints_2[1][0] = 99;
        ca.ints_2[1][1] = 99;

        ca.ints_myint[1].i = 99;

        ca.ints_myint_2[1][0].i = 99;
        ca.ints_myint_2[1][1].i = 99;

        System.out.println(ca);
        System.out.println(ca_clone);

    }
}
