package com.evan.javaaaaaaaaa.advanced.effect.enumm;

/**
 * Created by evan01.zhang on 2018/9/6.
 * <p>
 * 用接口模拟可扩展的枚举
 */
public class EnumExtension {

    public interface Operation {
        double apply(double x, double y);
    }

    /**
     * 算法枚举的默认实现，包含了+ - * / 四种基本算法
     */
    public enum OperationBase implements Operation {
        PLUS("+") {
            @Override
            public double apply(double x, double y) {
                return x + y;
            }
        },

        MINUS("-") {
            @Override
            public double apply(double x, double y) {
                return x - y;
            }
        },

        TIMES("*") {
            @Override
            public double apply(double x, double y) {
                return x * y;
            }
        },

        DIVIDE("/") {
            @Override
            public double apply(double x, double y) {
                return x / y;
            }
        };
        private final String symbol;

        OperationBase(String symbol) {
            this.symbol = symbol;
        }

        @Override
        public String toString() {
            return symbol;
        }
    }

    public enum OperationExtension implements Operation {

        EXPONENT("pow") {
            @Override
            public double apply(double x, double y) {
                return Math.pow(x, y);
            }
        }, REMAINDER("%") {
            @Override
            public double apply(double x, double y) {
                return x % y;
            }
        };

        private final String symbol;

        OperationExtension(String symbol) {
            this.symbol = symbol;
        }

        @Override
        public String toString() {
            return symbol;
        }
    }

    public static void main(String[] args) {
        double x = 2, y = 4;
        test(OperationBase.class, x, y);
        test(OperationExtension.class, x, y);
    }

    /**
     * 这里传递枚举用有限类型令牌
     *
     * @param operations
     * @param x
     * @param y
     * @param <T>
     */
    public static <T extends Enum<T> & Operation> void test(Class<T> operations, double x, double y) {
        for (Operation op : operations.getEnumConstants()) {
            System.out.printf("%f %s %f = %f %n", x, op, y, op.apply(x, y));
        }
    }

}
