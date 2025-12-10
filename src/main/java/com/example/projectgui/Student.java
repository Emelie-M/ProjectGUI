package com.example.projectgui;

public class Student extends Person  {
    public String schoolName;
    public int grade;

    public Student(String id, String name, int age, String schoolName, int grade) {
        super(id, name, age);
        this.schoolName = schoolName;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return super.toString() + ",   schoolName: " + schoolName + ",   grade: " + grade;
    }
}
