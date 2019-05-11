package com.evan.javaaaaaaaaa.jvm.jvm_exception;

/**
 * java运行时常连池OOM（由于运行时常连池是方法去的一部分，同样可以用来测试方法区）
 * <p>
 * VM Options : -XX:PermSize=10m -XX:MaxPermSize=10m
 * <p>
 * 问题已经不能出现了，且PermSize对于VM已经不生效了
 * Java HotSpot(TM) 64-Bit Server VM warning: ignoring option PermSize=10m; support was removed in 8.0
 * Java HotSpot(TM) 64-Bit Server VM warning: ignoring option MaxPermSize=10m; support was removed in 8.0
 */
public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {
        //------------------------------------------------
        //1.7以后，这里无法出现OOM，原因见下面方法说明
        //前引用避免gc回收
//        List<String> list = new ArrayList<>();
//        int i = 0;
//        while (true) {
//            list.add(String.valueOf(i++).intern());
//            System.out.println(i);
//        }
        //------------------------------------------------
        testIntern();
    }

    /**
     * 在jdk1.6中，intern()方法会把首次遇到的字符串实例复制到永久代中，返回的是永久代中这个实例的引用
     * 在jdk1.7，intern()不会再复制，只记录在常连池中首次出现的实例引用。因为java在toString()之前已经出现过，所以，str2 返回false
     */
    public static void testIntern() {
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);

        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);

        String str3 = new StringBuilder("jaaaaaa").append("vaxxxxxxxxxx").toString();
        System.out.println(str3.intern() == str3);
    }
}
