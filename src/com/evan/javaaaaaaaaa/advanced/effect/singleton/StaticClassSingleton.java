package com.evan.javaaaaaaaaa.advanced.effect.singleton;

/**
 * Created by evan01.zhang on 2018/9/4.
 * 静态内部类实现单例模式
 *
 * 缺点---没有办法设置参数，适合无参的单例模式。
 */
public class StaticClassSingleton {

    private static class Holder {
        private static StaticClassSingleton INSTANCE = new StaticClassSingleton();
    }


    /**
     * 当getInstance方法第一次被调用，由于jvm加载机制------读取一个静态字段时会对于类
     * 进行初始化（Holder），由于Holder的静态对象初始化而构建INSTANCE，切getInstance每次都是
     * 读取的holder.INSTANCE,故实现单例。
     *
     * @return
     */
    public static StaticClassSingleton getInstance() {
        return Holder.INSTANCE;
    }
}
