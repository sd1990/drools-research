package org.songdan.drools.research.model;

/**
 * @author: Songdan
 * @create: 2019-11-20 15:59
 **/
public class Person {

    private String name;

    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
