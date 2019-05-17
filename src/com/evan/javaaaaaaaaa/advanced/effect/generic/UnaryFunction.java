package com.evan.javaaaaaaaaa.advanced.effect.generic;

/**
 * Created by evan01.zhang on 2018/9/3.
 * <p>
 * 泛型单例工厂
 * 依赖：
 * 1、泛型运行时类型擦除
 * 2、受检安全的类型转换
 */
public abstract class UnaryFunction<T> {

    public abstract T apply(T arg);

    /**
     * 懒汉式单例模式实现
     */
    private static UnaryFunction<Object> IDENTITY_FUNCTION = new UnaryFunction() {
        @Override
        public Object apply(Object arg) {
            return arg;
        }
    };

//    private static UnaryFunction<Map> IDENTITY_FUNCTION = new UnaryFunction<Map>() {
//        @Override
//        public Map apply(Map arg) {
//            return arg;
//        }
//    };


    /**
     * 由于运行时泛型类型擦除，所以下面的类型转换是安全的，
     * 故通过递归泛型类型限制，so，apply方法可以在一个对象内部支持多类型。
     * 当然，你直接生命apply为Object也不是不可以，但是由于泛型会在编译期进行类型检查，相对于
     * Object方式实现，只有再进行无校验的类型转换，比较安全！
     * <p>
     * （前提是：上面单例构建中，指定为Object，如果写成注释掉那样，那就没戏了）
     *
     * @param <E>
     * @return
     */
    public static <E> UnaryFunction<E> identityFunction() {
        return (UnaryFunction<E>) IDENTITY_FUNCTION;
    }

    public static void main(String[] args) {
        String[] strs = {"111", "222", "333"};
        //1、java运行时泛型类型擦除
        //2、递归类型限制
        UnaryFunction<String> stringUnaryFunction = identityFunction();
        for (String s : strs) {
            System.out.println(stringUnaryFunction.apply(s));
        }

        Number[] numbers = {1, 2.0F, 3.0012D};
        UnaryFunction<Number> numberUnaryFunction = identityFunction();
        for (Number n : numbers) {
            System.out.println(numberUnaryFunction.apply(n));
        }

        String[] strs1 = {"aaa", "bbb", "ccc"};
        UnaryFunction<String> stringUnaryFunction1 = identityFunction();
        for (String s : strs1) {
            System.out.println(stringUnaryFunction1.apply(s));
        }
    }
}
