package hotswap;

public class Test {
    public static void main(String[] args) {
        System.out.println("ceshi");

        throw new IllegalArgumentException("我就随便打印一个错误信息");
    }
}
