package com.evan.javaaaaaaaaa.advanced.effect.clone;

/**
 * Created by evan01.zhang on 2018/8/22.
 */
public class MyInt implements Cloneable {
    public int i;

    public MyInt(int i) {
        this.i = i;
    }

    /**
     * 在jdk1.5之前，这里只能返回Object
     * 由于jdk1.5之后引入的泛型，所以这里可以返回最终类型
     *
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected MyInt clone() throws CloneNotSupportedException {
        return (MyInt) super.clone();
    }

    @Override
    public String toString() {
        return "MyInt{" +
                "i=" + i +
                '}';
    }
}
