package com.evan.javaaaaaaaaa.advanced.effect.annotation;

import java.lang.annotation.*;

/**
 * Created by evan01.zhang on 2018/9/6.
 * <p>
 * 注解
 */
// ***四个元注解***   注解可以再哪里使用
@Target(ElementType.METHOD)
// ***四个元注解***   注解在哪个级别可用---SOURCE、CLASS、RUNTIME
@Retention(RetentionPolicy.RUNTIME)
// ***四个元注解***   将此注解包含在java doc中
@Documented
// ***四个元注解***   声明允许子类继承父类中的注解
@Inherited
public @interface AnnotationTest {
    // 没有元素的注解，被称为标记注解

    /**
     * 关于注解的可用类型：
     * 1、全部基本类型
     * 2、String
     * 3、Class
     * 4、enum
     * 5、Annotation
     * 6、以上全部类型的数组
     */
    public int id(); // 注解中的元素

    public String description() default "this is no description"; // 注解元素的default值

    //
    public AnnotationTestStr str() default @AnnotationTestStr(
            name = "not defalut name",
            exception = NullPointerException.class,
            exceptions = {IllegalAccessException.class, ArrayIndexOutOfBoundsException.class});
}
