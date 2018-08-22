package com.evan.javaaaaaaaaa.advanced.effect.clone;

/**
 * 演示基本数据类型下clone的用法
 */
public class CloneBaseDemo implements Cloneable {

    char field_c = 'c';
    int field_i = 1;
    double field_d = 5D;
    String field_s = "abc";

    /**
     * 从java引入泛型开始，这里可以直接返回对应的真实类型，而不用返回Object了
     *
     * @return
     */
    @Override
    protected CloneBaseDemo clone() {
        try {
            return (CloneBaseDemo) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            throw new AssertionError(); // never happen
        }
    }

    @Override
    public String toString() {
        return "CloneBaseDemo{" +
                "field_c=" + field_c +
                ", field_i=" + field_i +
                ", field_d=" + field_d +
                ", field_s='" + field_s + '\'' +
                '}';
    }

    public static void main(String[] args) {
        CloneBaseDemo cbd = new CloneBaseDemo();
        CloneBaseDemo cbd_copy = cbd.clone();

        // 由此可证基本数据类型，clone无需特殊处理。
        cbd.field_d = 11D;
        // String是放在常量池当中，这个修改相当于把指向字符串"abc"的指针，指向了"cba"
        cbd.field_s = new StringBuilder("cba").toString();
        cbd.field_i = 11;
        cbd.field_c = 'a';
        System.out.println(cbd);
        System.out.println(cbd_copy);
    }
}
