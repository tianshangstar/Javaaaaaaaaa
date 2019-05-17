package com.evan.javaaaaaaaaa.jvm.jvm_exception;

/**
 * Created by evan01.zhang on 2018/3/23.
 */
public class FinalizeExcapeGC {

    public static FinalizeExcapeGC finalizeExcapeGC = null;

    public void isAlive(){
        System.out.println("alive");
    }

    /**
     * 注意 ：： 每个对象的finalize方法，仅仅会执行一次
     * @throws Throwable
     */
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize excute");
        finalizeExcapeGC = this;
    }

    public static void main(String[] args) throws InterruptedException {
        finalizeExcapeGC = new FinalizeExcapeGC();
        finalizeExcapeGC = null;
        System.gc();
        Thread.sleep(500);
        if (finalizeExcapeGC != null) {
            finalizeExcapeGC.isAlive();
        } else {
            System.out.println("step 1 : killed");
        }

        finalizeExcapeGC = null;
        System.gc();
        Thread.sleep(500);
        if (finalizeExcapeGC != null) {
            finalizeExcapeGC.isAlive();
        } else {
            System.out.println("step 2 : killed");
        }
    }
}
