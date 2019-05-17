package com.evan.javaaaaaaaaa.advanced.effect.enumm;

/**
 * Created by evan01.zhang on 2018/9/5.
 * <p>
 * 示例并实现枚举的基本用法
 */
public enum Student {

    // 枚举常量声明，如果有带参数的构造函数，可以调用带参构造实现
    STUDENT_ZHANG,
    STUDENT_LI(190, 100, "小李"),
    STUDENT_WANG(170, 70, "小王");

    //由于枚举天生就是不可变的，所以枚举的所有域都应该是final的
    private final int weight;
    private final float height;
    private final String name;
    private final float BMI;

    /**
     * 枚举的构造，和正常类一样，可以做一些其他的初始化操作
     */
    Student() {
        this.weight = 90;
        this.height = 180;
        this.name = "unknown";
        this.BMI = this.weight / (this.height * this.height / 10000);
    }

    Student(float height, int weight, String name) {
        this.height = height;
        this.weight = weight;
        this.name = name;
        this.BMI = this.weight / (this.height * this.height / 10000);
    }

    public static void main(String[] args) {
        //打印每个同学的姓名， BMI
        for (Student student : Student.values()) {
            System.out.printf("姓名 ： %s   BMI : %f \n", student.name, student.BMI);
            // 枚举的默认tostring，返回这个枚举对象名称的字符串表达
            System.out.println(student.toString());
        }
    }
}
