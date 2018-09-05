package com.evan.javaaaaaaaaa.advanced.effect.enumm;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by evan01.zhang on 2018/9/5.
 * <p>
 * 特定于常量的方法实现
 */
public enum Operation {
    PLUS("+") {
        @Override
        double apply(double x, double y) {
            return x + y;
        }
    },

    MINUS("-") {
        @Override
        double apply(double x, double y) {
            return x - y;
        }
    },

    TIMES("*") {
        @Override
        double apply(double x, double y) {
            return x * y;
        }
    },

    DIVIDE("/") {
        @Override
        double apply(double x, double y) {
            return x / y;
        }
    };

    /**
     * 由于重写了toString方法，那么最好考虑实现一个由string来得到正确的enum的方法
     */
    private static final Map<String, Operation> stringToEnum = new HashMap<>();

//    private static String xxx = "static";
//    private static final String xxxxxx = "static final";

    /**
     * 为何采用静态代码块来将每一个字符串对应关系放入map：
     * 首先，枚举通过“公有的静态final域为每个枚举常量导出实例”来实现的。
     * 所以枚举常量从创建开始就是真正意义上的不可变的。
     * 故无法访问static变量，但是可以访问final和static final常量。
     *
     * The constructor is called before the static fields have all been initialized,
     * because the static fields (including those representing the enum values) are initialized in textual order,
     * and the enum values always come before the other fields.
     * Note that in your class example you haven't shown where ABBREV_MAP is initialized
     * - if it's after SUNDAY, you'll get an exception when the class is initialized.
     * Yes, it's a bit of a pain and could probably have been designed better.
     * However, the usual answer in my experience is to have a static {} block at the end of all the static initializers,
     * and do all static initialization there, using EnumSet.allOf to get at all the values.
     *
     * 这段的意思是：：：：：
     * 1、构造函数--enum的，在静态变量初始化之前被调用
     * 2、静态变量是按照文本顺序初始化。
     * 3、enum常量必须在枚举的最前面。
     * 所以，这才是为什么不能在枚举构造中，调用静态变量。常量可以！！！！
     */
    static {
        for (Operation op : values()) {
            // 由于重写了tostring方法，这里相当+->Operation.PLUS
            stringToEnum.put(op.toString(), op);
        }
    }

    public static Operation fromString(String symbol) {
        return stringToEnum.get(symbol);
    }

    private final String symbol;

    Operation(String symbol) {
//        System.out.printf(xxx);
//        System.out.printf(xxxxxx);
        this.symbol = symbol;
    }

    /**
     * 这里如果不重写tostring方法，那么每个打印的位置，默认调用
     * operation的tostring方法，返回的将会是对应的枚举的字符串表达
     * 如：枚举常量PLUS.toString = “PLUS”
     * <p>
     * 故，在enum中，如果需要log输出，可以合理重写toString方法
     *
     * @return
     */
    @Override
    public String toString() {
        return symbol;
    }

    // enum可以定义一个抽象方法，并在每个常量主体重覆盖实现这个抽象方法，来实现各自独有的功能
    abstract double apply(double x, double y);

    public static void main(String[] args) {
        double x = 10, y = 20;
        for (Operation op : Operation.values()) {
            System.out.printf("%f %s %f = %f %n", x, op, y, op.apply(x, y));
        }
    }
}
