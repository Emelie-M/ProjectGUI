package com.example.projectgui;

abstract public class Person {
    public String name;
    public int age;
    public String id;

    public Person(String id,String name, int age){
        this.id=id;
        this.name=name;
        this.age=age;
    }

    abstract String getName();

    public int getAge() {
        return age;
    }

    public String getId() {
        return id;
    }

    public String toString() {
        return "Id: " + id+",    Name: " + name +
                ",    age: " + age;
    }
}
