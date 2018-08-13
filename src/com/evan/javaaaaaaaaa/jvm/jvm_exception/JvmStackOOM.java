package com.evan.javaaaaaaaaa.jvm.jvm_exception;

/**
 * java栈异常
 *
 * OutOfMemoryError
 * StackOverflowError
 *
 * jvm options:-Xss128k   for StackOverflowError
 *             -Xss2m     for OutOfMemoryError
 */
public class JvmStackOOM {

    private int i = 1;

    public void stackLeak() {
        i++;
        stackLeak();
    }

    public static void main(String[] args) {
//        JvmStackOOM jvmStackOOM = new JvmStackOOM();
//        jvmStackOOM.stackLeak();
        //--------------------------------------------------------------
        //这段代码别跑，卡死windows专用，设置xxs也没用。。 ~~~~~
        //--------------------------------------------------------------
//        while (true) {
//            new Thread() {
//                @Override
//                public void run() {
//                    keepRun();
//                }
//            }.start();
//        }
    }

    private static void keepRun(){
        while(true) {

        }
    }

}
