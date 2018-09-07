package com.evan.javaaaaaaaaa.advanced.effect.annotation;

/**
 * Created by evan01.zhang on 2018/9/6.
 */
public class FunctionClass {
    @AnnotationTestStr(name = "userAnnotation_2", exception = StringIndexOutOfBoundsException.class, exceptions = ArrayIndexOutOfBoundsException.class)
    private int field; // 试验属性注解

    private String name;

    @AnnotationTest(id = 11)
    public void userAnnotation_1() {

    }

    public void notUseAnnotation() {
    }
}
