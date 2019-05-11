package com.evan.javaaaaaaaaa.advanced.effect.clone.object;

import java.io.IOException;
import java.util.Arrays;

/**
 * 深拷贝：构造器实现
 */
public class CopyConstructor {

    int[] ints = {1, 2, 3};
    int[][] ints_2 = {{10, 11}, {20, 21}, {30, 31}};

    MyInt[] ints_myint = {new MyInt(1), new MyInt(2), new MyInt(3)};

    MyInt[][] ints_myint_2 = {{new MyInt(10), new MyInt(11)}, {new MyInt(20), new MyInt(21)}, {new MyInt(30), new MyInt(31)}};

    /**
     * 用一个拷贝构造器来实现深拷贝
     *
     * @param src
     */
    public CopyConstructor(CopyConstructor src) throws CloneNotSupportedException {
        this.ints = src.ints.clone();

        for (int i = 0; i < src.ints_myint.length; i++) {
            this.ints_myint[i] = src.ints_myint[i].clone();
        }

        // 这个需要根据数组的多维展开，所以这样其实并不好,如果是三维数组，就要嵌套循环
        for (int i = 0; i < src.ints_2.length; i++) {
            this.ints_2[i] = src.ints_2[i].clone();
        }

        for (int i = 0; i < src.ints_myint_2.length; i++) {
            for (int j = 0; j < src.ints_myint_2[i].length; j++) {
                this.ints_myint_2[i][j] = src.ints_myint_2[i][j].clone();
            }
        }
    }

    public CopyConstructor() {
    }

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


    public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {
        CopyConstructor ca = new CopyConstructor();
        CopyConstructor ca_clone = new CopyConstructor(ca);

        ca.ints[1] = 99;

        ca.ints_2[1][0] = 99;
        ca.ints_2[1][1] = 99;

        ca.ints_myint[1].i = 99;

        ca.ints_myint_2[1][0].i = 99;
        ca.ints_myint_2[1][1].i = 99;

        System.out.println(ca);
        System.out.println(ca_clone);
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
}
