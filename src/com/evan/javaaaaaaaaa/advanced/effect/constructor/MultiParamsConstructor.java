package com.evan.javaaaaaaaaa.advanced.effect.constructor;

/**
 * 多参构造器，考虑用构建器实现
 * <p>
 * 避免多参重载过多的构造器
 * 避免在外部调用过多的setter方法导致线程不同步
 */
public class MultiParamsConstructor {

    // 两个必选参数
    int param_nes1 = -1;
    int param_nes2 = -1;
    // 6个可选参数
    int param_un1 = 0;
    int param_un2 = 0;
    int param_un3 = 0;
    int param_un4 = 0;
    int param_un5 = 0;
    int param_un6 = 0;

    /**
     * 泛型---接口---返回类型
     *
     * @param <T>
     */
    public interface IBuilder<T> {
        T build();
    }

    public static class Builder implements IBuilder<MultiParamsConstructor> {
        // 两个必选参数
        int param_nes1 = -1;
        int param_nes2 = -1;
        // 6个可选参数
        int param_un1 = 0;
        int param_un2 = 0;
        int param_un3 = 0;
        int param_un4 = 0;
        int param_un5 = 0;
        int param_un6 = 0;

        public Builder(int param_nes1, int param_nes2) {
            this.param_nes1 = param_nes1;
            this.param_nes2 = param_nes2;
        }

        public Builder param_un1(int param_un1) {
            this.param_un1 = param_un1;
            return this;
        }

        public Builder param_un2(int param_un2) {
            this.param_un2 = param_un2;
            return this;
        }

        public Builder param_un3(int param_un3) {
            this.param_un3 = param_un3;
            return this;
        }

        public Builder param_un4(int param_un4) {
            this.param_un4 = param_un4;
            return this;
        }

        public Builder param_un5(int param_un5) {
            this.param_un5 = param_un5;
            return this;
        }

        public Builder param_un6(int param_un6) {
            this.param_un6 = param_un6;
            return this;
        }

        /**
         * 最终调用Builder.build方法来生成实例对象
         *
         * @return
         */
        public MultiParamsConstructor build() {
            return new MultiParamsConstructor(this);
        }
    }

    private MultiParamsConstructor(Builder builder) {
        this.param_nes1 = builder.param_nes1;
        this.param_nes2 = builder.param_nes2;
        this.param_un1 = builder.param_un1;
        this.param_un2 = builder.param_un2;
        this.param_un3 = builder.param_un3;
        this.param_un4 = builder.param_un4;
        this.param_un5 = builder.param_un5;
        this.param_un6 = builder.param_un6;
    }

}
