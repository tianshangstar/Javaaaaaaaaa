package com.evan.javaaaaaaaaa.base.java8.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Created by evan01.zhang on 2018/5/2.
 * <p>
 * Function 提供任意一种类型的参数，返回另外一个任意类型返回值   -> stream.map
 * Consumer 提供任意一种类型的参数，返回空值                    -> stream.forEach
 * Supplier 参数为空，得到任意一种类型的返回值                  -> stream.generate
 * Predicate 提供任意一种类型的参数，返回boolean返回值          -> stream.filter
 */
public class FunctionInterface {
    static class Person {
        public String firstName;
        public String lastName;
        public int age;

        public Person(String firstName, String lastName, int age) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
        }
    }

    public static void main(String[] args) {
        List<Person> personList = Arrays.asList(
                new Person("yixing", "zhang", 15),
                new Person("min", "zhao", 18),
                new Person("entong", "li", 19)
        );

        // 需求:打印出所有"Z"开头的人的FirstName

        // 传统foreach遍历方式
        printFun(personList);

        // lambda方式1。。。。 自定义接口
        checkAndPrint(personList,
                (p) -> p.lastName.startsWith("z"),
                (p) -> System.out.println(p.firstName));

        // lambda with FunctionInterface
        checkAndPrintFI(personList,
                (p) -> p.lastName.startsWith("z"),
                (p) -> System.out.println(p.firstName));

        // 用foreach代替循环
        checkAndPrintFIWithLambdaForeach(
                personList,
                (p) -> p.lastName.startsWith("z"),
                (p) -> System.out.println(p.firstName));

        // 用stream代替匿名函数
        personList
                .stream()
                .filter((p) -> p.lastName.startsWith("z"))
                .forEach((p) -> System.out.println(p.firstName));

        System.out.println("-------------");
        // 用其他class的method代替lambda expression
        personList
                .stream()
                .filter((p) -> p.lastName.startsWith("z"))
                .forEach(FunctionInterface::printFirstName);

        System.out.println("-------------");
        personList.stream()
                .filter((p) -> p.lastName.startsWith("z"))
                .map((p) -> p.firstName)
                .forEach(System.out::println);
    }

    public static void printFun(List<Person> personList) {
        for (Person person : personList) {
            if (person.lastName.startsWith("z")) {
                System.out.println(person.firstName);
            }
        }
    }

    /**
     * 自定义接口实现lambda
     *
     * @param personList
     * @param c
     * @param p
     */
    public static void checkAndPrint(List<Person> personList, NameChecker c, NamePrinter p) {
        for (Person person : personList) {
            if (c.checkName(person)) {
                p.printName(person);
            }
        }
    }

    /**
     * 借助Predicate和Consumer来实现lambda
     *
     * @param personList
     * @param c
     * @param p
     */
    public static void checkAndPrintFI(List<Person> personList, Predicate<Person> c, Consumer<Person> p) {
        for (Person person : personList) {
            if (c.test(person)) {
                p.accept(person);
            }
        }
    }

    /**
     * 用Iterable.forEach代替循环
     *
     * @param personList
     * @param c
     * @param p
     */
    public static void checkAndPrintFIWithLambdaForeach(List<Person> personList, Predicate<Person> c, Consumer<Person> p) {
        personList.forEach((person) -> {
            if (c.test(person)) {
                p.accept(person);
            }
        });
    }

    public static void printFirstName(Person person) {
        System.out.println(person.firstName);
    }

    interface NameChecker {
        boolean checkName(Person p);
    }

    interface NamePrinter {
        void printName(Person p);
    }
}
