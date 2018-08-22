package com.evan.javaaaaaaaaa.advanced.effect.clone.object;

import java.io.*;
import java.util.Arrays;

/**
 * 借助Serizalizable来实现深拷贝
 * 缺点：需要对象实现Serializable，且关键属性不能是transient,
 * 并且要注意可以干扰序列化和反序列化过程的readReslove、readObject、writeObject方法
 */
public class CloneBySerializable implements Serializable {
    int[] ints = {1, 2, 3};
    int[][] ints_2 = {{10, 11}, {20, 21}, {30, 31}};

    MyInt[] ints_myint = {new MyInt(1), new MyInt(2), new MyInt(3)};

    MyInt[][] ints_myint_2 = {{new MyInt(10), new MyInt(11)}, {new MyInt(20), new MyInt(21)}, {new MyInt(30), new MyInt(31)}};

    public static class MyInt implements Serializable {
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

    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        CloneBySerializable ca = new CloneBySerializable();
        CloneBySerializable ca_clone = deepCopy(ca);

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

    /**
     * 要求：java的泛型可以多extends
     * 1、只能有一个是类 多extends
     * 2、类必须放在第一个
     *
     * @param src
     * @param <T>
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static <T extends Serializable> T deepCopy(T src) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(src);

        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        @SuppressWarnings("unchecked")
        T dest = (T) in.readObject();
        return dest;
    }
}
