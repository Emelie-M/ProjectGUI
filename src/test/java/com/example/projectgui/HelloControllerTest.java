package com.example.projectgui;

import javafx.event.ActionEvent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelloControllerTest {

    @Test
    void initialize() {
        HelloController controller = new HelloController();
        controller.initialize();
    }

    @Test
    void addMember() {
        HelloController controller = new HelloController();
        initialize();
        controller.students.clear();
        controller.id_field.setText("S12");
        controller.name_field.setText("Test");
        controller.age_field.setText("22");
        controller.school_field.setText("Vanier");
        controller.grade_field.setText("3");
        controller.addMember(new ActionEvent());
        assertEquals(1, controller.students.size(), "Student should be added");

    }

    @Test
    void showMember() {
    }

    @Test
    void addMovie() {
    }

    @Test
    void showMovie() {
    }

    @Test
    void addMovieRent() {
    }

    @Test
    void calculate() {
    }

    @Test
    void showMovieR() {
    }
}