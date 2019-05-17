package com.evan.javaaaaaaaaa.advanced.effect.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by evan01.zhang on 2018/9/6.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnotationTestStr {
    public String name() default "default name";

    // 声明为继承Exception
    public Class<? extends Exception> exception();

    // 当然，复杂点的泛型令牌数组也可以
    public Class<? extends Exception>[] exceptions();
}
