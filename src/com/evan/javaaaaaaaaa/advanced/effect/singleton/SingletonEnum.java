package com.evan.javaaaaaaaaa.advanced.effect.singleton;

/**
 * 用一个元素的枚举来实现单例模式
 *
 * 好处：可以不实现readResolve来避免反序列化问题
 * 可以不用在私有构造器特殊处理（抛异常）来处理反射创建对象的问题
 */
public enum SingletonEnum {
    INSTANCE;

    public void doSth() {

    }
}
