package com.evan.javaaaaaaaaa.algorithm.leetcode.bilibili;

import java.util.LinkedHashMap;
import java.util.Map;

public class Demo4HashMap {

    static class Person {
        String name = null;

        Person() {
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object obj) {
            Person person = (Person) obj;
            return name.equals(person.name);
        }

        @Override
        public int hashCode() {
            return name.length();
        }
    }

    public static void main(String[] args) {
        LinkedHashMap<Person, String> linkedHashMap = new LinkedHashMap<>();

        Person person = new Person();
        person.setName("Evan");
        linkedHashMap.put(person, "Teacher");

        person.setName("Tom");
        linkedHashMap.put(person, "Lawyer");

        person.setName("Lily");
        linkedHashMap.put(person, "Student");

        for (Map.Entry<Person, String> entry : linkedHashMap.entrySet()) {
            System.err.println(
                    String.format("%s is %s", entry.getKey().name, entry.getValue()));
        }
    }
}
