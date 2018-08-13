package com.evan.javaaaaaaaaa.jvm.hotswap;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class HackSystem {

    public static final InputStream in = System.in;

    private static ByteArrayOutputStream buffer = new ByteArrayOutputStream();

    private static final PrintStream out = new PrintStream(buffer);

    public static final PrintStream err = out;

    public static String getBufferString() {
        return buffer.toString();
    }

    public static void clearBuffer() {
        buffer.reset();
    }

    public static void setSecurityManager(final SecurityManager s) {
        System.setSecurityManager(s);
    }

    public static SecurityManager getSecurityManager() {
        return System.getSecurityManager();
    }

    public static void arraycopy(Object src, int srcPos,
                                 Object dest, int destPos,
                                 int length) {
        System.arraycopy(src, srcPos, dest, destPos, length);
    }

    public static int identityHashCode(Object x) {
        return System.identityHashCode(x);
    }

    // 后续需要继续补充，统一用system的方法实现就好
}
