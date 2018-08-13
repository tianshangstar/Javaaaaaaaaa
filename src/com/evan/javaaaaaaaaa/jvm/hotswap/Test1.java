package com.evan.javaaaaaaaaa.jvm.hotswap;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Test1 {
    public static void main(String[] args) {
        String localClassFilePath = "/Users/evan01.zhang/develop/java workspace/Jvm/out/production/Jvm/hotswap/Test.class";
        try {
            InputStream is = new FileInputStream(localClassFilePath);
            byte[] b = new byte[is.available()];
            is.read(b);
            is.close();
            System.out.println(JavaClassExecuter.excute(b));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
