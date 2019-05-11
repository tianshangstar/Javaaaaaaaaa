package com.evan.javaaaaaaaaa.advanced.effect.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by evan01.zhang on 2018/9/5.
 * <p>
 * 验证俩问题：
 * 1、什么是super type token 答：用一个继承了目标类的匿名内部类，来获取目标类的泛型参数
 * 2、如何获取泛型参数类型 答：同上。
 */
public class SuperTypeToken<T> {

    public static void main(String[] args) {

        // ----------------- 一定要注意啊，这里tmd是个匿名内部类啊，并不是对象啊
        // ----------------- 我还在想，运行时泛型擦除还怎么获得泛型参数类型？？？？？
        // ----------------- 你这是作弊啊
        Type singleType = new SuperTypeToken<String>() {
        }.getClass().getGenericSuperclass();
        Type type = ((ParameterizedType) singleType).getActualTypeArguments()[0];
        System.out.println("获得的泛型参数:" + type);

        // 这里如果不用匿名内部类的写法，获取到的是E,当然JDK写T就是T
        // 但是，如果用内部类，返回就是正确的java.util.List<java.lang.String>
        // 核心就是注释掉的大括号xxxxxxxxxxxxxxxxxxxxxxxxxxxx
        Type listType = new ArrayList<List<String>>()
                //{}
                .getClass().getGenericSuperclass();
        Type genericType = ((ParameterizedType) listType).getActualTypeArguments()[0];
        System.out.println("获得的泛型参数:" + genericType);

    }

}
