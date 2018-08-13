package com.evan.javaaaaaaaaa.base.java8.lambda;

/**
 * Created by evan01.zhang on 2018/5/2.
 * <p>
 * 下面这个写的不错
 * <p>
 * Lambda 表达式有何用处？如何使用？ - 涛吴的回答 - 知乎
 * https://www.zhihu.com/question/20125256/answer/14058285
 */
public class LambdaExpression {

    interface PrintSomething {
        void printFunction(String s);
    }

    public void doPrint(PrintSomething pst) {
        pst.printFunction("顺便打印点啥");
    }

    public void callDoPrint() {
        // 原来写法
        doPrint(new PrintSomething() {
            @Override
            public void printFunction(String s) {
                System.out.println("old way : " + s);
            }
        });

        // lambda
        doPrint((s) -> System.out.println("lambda way : " + s));
    }
}
