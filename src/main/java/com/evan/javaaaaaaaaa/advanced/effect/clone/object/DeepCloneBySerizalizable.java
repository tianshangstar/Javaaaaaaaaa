package com.evan.javaaaaaaaaa.advanced.effect.clone.object;

import java.io.*;
import java.util.Arrays;

import static com.evan.javaaaaaaaaa.advanced.effect.clone.object.CloneBySerializable.deepCopy;

/**
 * Created by evan01.zhang on 2018/8/23.
 */
public class DeepCloneBySerizalizable implements Serializable {
    int[][] intss = {{10, 11}, {20, 21}, {30, 31}};
    // 由于不特殊处理，transient不会序列化，所以会在clone对象中打印null
    transient int[][] intss1 = {{10, 11}, {20, 21}, {30, 31}};

    @Override
    public String toString() {
        return "DeepCloneBySerizalizable{" +
                "\nintss=" + Arrays.deepToString(intss) + ", " +
                "\nintss1=" + Arrays.deepToString(intss1) + ", " +
                "\n}";
    }

    /**
     * 在原对象上调用，将原对象属性写入流
     *
     * @param oos
     * @throws IOException
     */
    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        oos.writeInt(intss1.length);
        for (int i = 0; i < intss1.length; i++) {
            oos.writeObject(intss1[i]);
        }
    }

    /**
     * 在新对象上调用，准确的说，是在新对象执行完<cinit>后触发
     * 注：<cinit> 类构造器，自己去看jvm
     * 通过debug可知，这这里，新对象的所有属性都是默认值，即非静态属性不初始化。
     *
     * @param ois
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void readObject(ObjectInputStream ois) throws IOException,
            ClassNotFoundException {
        ois.defaultReadObject();
        int length = ois.readInt();
        this.intss1 = new int[3][2];
        for (int i = 0; i < length; i++) {
            this.intss1[i] = (int[]) ois.readObject();
        }
    }

    /**
     * 如果重写了readResolve方法，那么反序列化会通过反射的形式调用
     * 即最终反序列化生成的对象由readResolve的行为决定
     *
     * @return
     */
    private Object readResolve() {
        DeepCloneBySerizalizable deepCloneBySerizalizable = new DeepCloneBySerizalizable();
        deepCloneBySerizalizable.intss1[1][1] = -123;
        return deepCloneBySerizalizable;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        DeepCloneBySerizalizable dcbs = new DeepCloneBySerizalizable();
        DeepCloneBySerizalizable dcbs_clone = deepCopy(dcbs);

        dcbs.intss[1][0] = 99;
        dcbs.intss[1][1] = 99;

        dcbs.intss1[1][0] = 999;
        dcbs.intss1[1][1] = 999;

        System.out.println(dcbs);
        System.out.println("-------------------------------------------");
        System.out.println(dcbs_clone);
    }
}
