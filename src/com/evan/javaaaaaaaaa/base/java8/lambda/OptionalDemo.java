package com.evan.javaaaaaaaaa.base.java8.lambda;

import java.util.Optional;

/**
 * Created by evan01.zhang on 2018/5/3.
 * 利用optional中的封装函数，可以少些if else的判空语句，但是习惯比较难改
 */
public class OptionalDemo {

    public static void main(String[] args) {
        FunctionInterface.Person person = new FunctionInterface.Person("san", "zhang", 19);

        Optional<FunctionInterface.Person> personOptional = Optional.of(person);

        if (person != null) {
            System.out.println(person.firstName);
        }
        personOptional.ifPresent((p) -> System.out.println(p.firstName));
        FunctionInterface.Person notNullPersion = personOptional.orElse(new FunctionInterface.Person("si", "li", 19));
        personOptional.orElseGet(() -> {
                    return personOptional.orElse(new FunctionInterface.Person("si", "li", 19));
                }
        );

        //
        personOptional.map((p) -> p.firstName)
                .map(firstName -> firstName.toUpperCase())
                .orElse(null);
    }
}
