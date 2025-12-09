package com.example.projectgui;

public class Movie {
    public String title;
    public String genre;
    public String id;
    public boolean availability;
    public String studentId;

    public Movie(String id,String title,String genre,boolean availability){
        this.id = id;
        this.title=title;
        this.genre=genre;
        this.availability=availability;
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

    public String toString() {
        return "id="+id+
                ", title= " + title +
                ", genre= " + genre +
                ", availability= " + availability;
    }
}
