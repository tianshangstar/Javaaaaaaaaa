package com.evan.javaaaaaaaaa.jvm.hotswap;

import java.lang.reflect.Method;

public class JavaClassExecuter {

    public static String excute(byte[] classByte) {
        HackSystem.clearBuffer();

        ClassModifier cm = new ClassModifier(classByte);
        byte[] modiBYtes = cm.modifyUTF8Constant("java/lang/System", "hotswap/HackSystem");

        HotSwapClassLoader loader = new HotSwapClassLoader();
        Class clazz = loader.loadByte(modiBYtes);
        try {
            Method method = clazz.getMethod("main", new Class[]{String[].class});
            method.invoke(null, new String[]{null});
        } catch (Throwable e) {
            e.printStackTrace(HackSystem.err);
        }
        return HackSystem.getBufferString();
    }
}
