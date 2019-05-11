package com.evan.javaaaaaaaaa.advanced.effect.clone;

import java.util.Arrays;

/**
 * Created by evan01.zhang on 2018/8/23.
 */
public class MultiArraysClone implements Cloneable {

    int[][] intss = {{10, 11}, {20, 21}, {30, 31}};

    @Override
    public String toString() {
        return "MultiArraysClone{" +
                "intss=" + Arrays.deepToString(intss) +
                '}';
    }

    @Override
    protected MultiArraysClone clone() throws CloneNotSupportedException {
        MultiArraysClone clone = (MultiArraysClone) super.clone();
        clone.intss = this.intss.clone();
        return clone;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        MultiArraysClone multiArraysClone = new MultiArraysClone();
        MultiArraysClone multiArraysClone_clone = multiArraysClone.clone();
        multiArraysClone.intss[1][0] = 99;
        multiArraysClone.intss[1][1] = 99;
        System.out.println(multiArraysClone);
        System.out.println(multiArraysClone_clone);
    }
}
