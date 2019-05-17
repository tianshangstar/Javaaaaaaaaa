package com.evan.javaaaaaaaaa.jvm.hotswap;

/**
 * 为了多次载入执行类而加入的类加载器
 * 把cdefineClass方法开放出来，只有外部显示调用的时候才会使用到loadBYte方法
 * 由虚拟机调用时，仍然按照原有的双亲委派规则使用loadClass方法进行加载
 * <p>
 * 来自深入《理解java虚拟机》 章节9.3
 */
public class HotSwapClassLoader extends ClassLoader {
    public HotSwapClassLoader() {
        super(HotSwapClassLoader.class.getClassLoader());
    }

    public Class loadByte(byte[] classByte){
        return defineClass(null, classByte, 0, classByte.length);
    }
}
