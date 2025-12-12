package com.example.projectgui;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelloControllerTest {

    HelloController controller;

    @BeforeEach
    void setup() {
        controller = new HelloController();
        controller.id_field = new TextField();
        controller.name_field = new TextField();
        controller.age_field = new TextField();
        controller.firstField = new TextField();
        controller.secondField = new TextField();
        controller.choiceMember = new ComboBox<>();
        controller.choiceMember.getItems().addAll("Student", "Member");

        controller.movieId = new TextField();
        controller.title_field = new TextField();
        controller.genre_field = new TextField();

        controller.movId = new TextField();
        controller.idM = new TextField();

        controller.returnMvId = new TextField();
        controller.ReturnMId = new TextField();
        controller.DateReturn = new DatePicker();
        controller.date_field = new DatePicker();
        controller.Payement_field = new TextField();

        controller.studentArea = new TextArea();
        controller.memberArea = new TextArea();
        controller.showMovieArea = new TextArea();
        controller.sMv = new TextArea();

        controller.initialize();
    }

    @Test
    void testAddStudent() {
            controller.choiceMember.setValue("Student");
            controller.id_field.setText("S1");
            controller.name_field.setText("John");
            controller.age_field.setText("20");
            controller.firstField.setText("Vanier");
            controller.secondField.setText("2");

            controller.addMember();

            assertEquals(1, controller.students.size());
            assertEquals("John", controller.students.get(0).getName());
    }

    @Test
    void testAddMovie() {
            controller.movieId.setText("M1");
            controller.title_field.setText("Avatar");
            controller.genre_field.setText("Action");

            controller.addMovie();

            assertEquals(1, controller.movies.size());
            assertEquals("Avatar", controller.movies.get(0).getTitle());
    }

    @Test
    void testRentMovie() {
            controller.choiceMember.setValue("Student");
            controller.id_field.setText("S1");
            controller.name_field.setText("John");
            controller.age_field.setText("20");
            controller.firstField.setText("Vanier");
            controller.secondField.setText("2");
            controller.addMember();

            controller.movieId.setText("M1");
            controller.title_field.setText("Avatar");
            controller.genre_field.setText("Action");
            controller.addMovie();

            controller.movId.setText("M1");
            controller.idM.setText("S1");
            controller.date_field.setValue(LocalDate.of(2025, 12, 11));
            controller.addMovieRent();

            Movie m = controller.movies.get(0);
            assertEquals("S1", m.getStudentId());
            assertEquals("2024-01-01", m.getDate());
    }

    @Test
    void testReturnMovie() {
            controller.movieId.setText("M1");
            controller.title_field.setText("Avatar");
            controller.genre_field.setText("Action");
            controller.addMovie();

            Movie m = controller.movies.get(0);
            m.setAvailability(false);
            m.setDate("2024-01-01");
            m.setStudentId("S1");

            controller.returnMvId.setText("M1");
            controller.ReturnMId.setText("S1");
            controller.DateReturn.setValue(LocalDate.of(2025, 12, 21));

            controller.calculate();

            assertEquals("8", controller.Payement_field.getText());
    }
}