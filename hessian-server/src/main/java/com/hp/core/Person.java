package com.hp.core;

import java.io.Serializable;

/**
 * Created by Paul on 2018/12/11
 */
public class Person implements Serializable {

//    private static final long serialVersionUID = -5120654915475703723L;
    private static final long serialVersionUID = -5120654915475703721L;

    private String name;

    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
