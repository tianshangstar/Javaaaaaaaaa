package com.evan.javaaaaaaaaa.advanced.effect.annotation;

import java.lang.reflect.Field;

/**
 * Created by evan01.zhang on 2018/9/12.
 */
public class TestFruit {
    public static void main(String[] args) throws NoSuchFieldException {
        Field field = Fruit.class.getDeclaredField("name");
        FruitName annotation = field.getAnnotation(FruitName.class);
        System.out.println(annotation.value());
    }
}
