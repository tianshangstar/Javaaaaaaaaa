package com.evan.javaaaaaaaaa.advanced.effect.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by evan01.zhang on 2018/9/7.
 * <p>
 * 简单示例:获取方法的注解声明，其他雷同。
 */
public class AnalysisAnno {
    public static void main(String[] args) throws NoSuchMethodException {
        Method[] methods = FunctionClass.class.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(AnnotationTest.class)) {
                AnnotationTest annotation = method.getAnnotation(AnnotationTest.class);
                System.out.println(annotation.id());
                System.out.println(annotation.description());
                System.out.println(annotation.str().name());
                System.out.println(annotation.str().exception());
                System.out.println(annotation.str().exceptions());
            }
        }

        System.out.println("-----------------------------------");

        Field[] fields = FunctionClass.class.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(AnnotationTestStr.class)) {
                AnnotationTestStr annotationTestStr = field.getAnnotation(AnnotationTestStr.class);
                System.out.println(annotationTestStr.name());
                System.out.println(annotationTestStr.exception());
                System.out.println(annotationTestStr.exceptions());
            }
        }
    }
}
