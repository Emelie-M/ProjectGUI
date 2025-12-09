package com.example.projectgui;

public class Movie implements Payement{
    public String title;
    public String genre;
    public String id;
    public boolean availability;
    public String studentId;
    public String memberId;
    public String date;

    public Movie(String id,String title,String genre,boolean availability){
        this.id = id;
        this.title=title;
        this.genre=genre;
        this.availability=availability;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean isAvailability() {
        return availability;
    }
    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public String getId() {
        return id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String toString() {
        return "id="+id+
                ", title= " + title +
                ", genre= " + genre +
                ", availability= " + availability;
    }

    @Override
    public int calculate(int days) {
            int fee = 0;
            if(!studentId.isEmpty()){
                if(days>7){
                    int feedays=days-7;
                    fee=5+ feedays;
                    return fee;
                }
                else{
                    fee=5;
                    return fee;
                }
            }
            if (!memberId.isEmpty()){
                if(days>7){
                    int feedays=days-7;
                    fee=10+feedays*2;
                    return fee;
                }
                else{
                    fee=10;
                    return fee;
                }
            }
        return fee;
    }
}
