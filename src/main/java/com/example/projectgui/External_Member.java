package com.example.projectgui;

public class External_Member extends Person{
    public String job;
    public String organization;

    public External_Member(String id,String name, int age, String job, String organization){
        super(id,name,age);
        this.job=job;
        this.organization=organization;
    }
    public String getName(){
        return name;
    }

    public String toString() {
        return super.toString()+ ", Job: " + job + ", Organization: " + organization;
    }
}
