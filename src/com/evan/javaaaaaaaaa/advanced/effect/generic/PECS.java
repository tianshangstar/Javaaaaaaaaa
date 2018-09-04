package com.evan.javaaaaaaaaa.advanced.effect.generic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by evan01.zhang on 2018/9/3.
 * <p>
 * PECS:Producter-extends, consumer-super
 * <p>
 * 源自《Effective Java》 ----- 利用有限制通配符表示来提升API的灵活性
 */
public class PECS {

    /**
     * 一个关于PECS应用的例子。
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T extends Comparable<? super T>> T max(List<? extends T> list) {
        Iterator<? extends T> i = list.iterator();
        T result = i.next();
        while (i.hasNext()) {
            T t = i.next();
            if (t.compareTo(result) > 0) {
                result = t;
            }
        }

        return result;
    }

    public static class Stack<T> {

        /**
         * Product - extends
         * 如： 声明Stack<Number>，这里就可以插入如Integer，Long等
         * 否则，如果方法声明如下面注释掉的部分，则只能插入Number的list
         * <p>
         * 以堆栈为例，push相当新建对象，适应于PE原则
         *
         * @param list
         */
        public void pushALl(List<? extends T> list) {
            for (T t : list) {
                push(t);
            }
        }
//        public void pushALl(List<T> list) {
//        }

        /**
         * 模拟入栈
         *
         * @param t
         */
        public void push(T t) {
        }

        /**
         * pop & popall方法，在堆栈模型中属于消费类方法（从堆栈中弹出对象消费掉）
         * 是应用CS原则
         *
         * @param dst
         */
        public void popAll(List<? super T> dst) {
            // 这里应该有个循环，懒得实现。
            dst.add(pop());
        }

        /**
         * 模拟出栈
         *
         * @return
         */
        public T pop() {
            return null;
        }
    }
}
