package com.example.projectgui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class HelloController {
    @FXML
    private TextField id_field, id_fieldm, name_field, name_fieldm, age_field, age_fieldm, school_field, grade_field, job_field, organization_field, movieId, title_field,genre_field;

    @FXML
    private Button addbtn, showbtn, addbtnM, showbtnM;

    @FXML
    private TextArea show_field,showMovieArea;

    @FXML
    ArrayList<Student> students = new ArrayList<>();
    ArrayList<External_Member> members = new ArrayList<>();
    ArrayList<Movie> movies = new ArrayList<>();
    Gson gsonStudent = new GsonBuilder().setPrettyPrinting().create();
    String filePath1 = "students.json";
    Gson gsonMember = new GsonBuilder().setPrettyPrinting().create();
    String filePath2 = "members.json";
    Gson gsonMovie = new GsonBuilder().setPrettyPrinting().create();
    String filePath3 = "movies.json";
    File file = new File(filePath1);

    @FXML
    protected void initialize() {
        if (file.exists() && file.length() != 0) {
            try (FileReader reader = new FileReader(file)) {
                Type listType = new TypeToken<ArrayList<Student>>() {}.getType();
                students = gsonStudent.fromJson(reader, listType);
            } catch (Exception e) {
                System.out.println("Error reading existing JSON: " + e.getMessage());
            }
        }
        File file2 = new File(filePath2);
        if (file2.exists() && file2.length() != 0) {
            try (FileReader reader = new FileReader(file2)) {
                Type listType = new TypeToken<ArrayList<External_Member>>() {}.getType();
                members = gsonMember.fromJson(reader, listType);
            } catch (Exception e) {
                System.out.println("Error reading existing JSON: " + e.getMessage());
            }
        }
        File file3 = new File(filePath3);
        if (file3.exists() && file3.length() != 0) {
            try (FileReader reader = new FileReader(file3)) {
                Type listType = new TypeToken<ArrayList<Movie>>() {}.getType();
                movies = gsonMovie.fromJson(reader, listType);
            } catch (Exception e) {
                System.out.println("Error reading existing JSON: " + e.getMessage());
            }
        }
    }

    @FXML
    protected void addMember(ActionEvent event) {
        addbtn = (Button) event.getSource();
        for(int i=0; i<students.size()&&i< members.size(); i++){
            try{
                if(id_field.getText().equalsIgnoreCase(students.get(i).getId())||id_fieldm.getText().equalsIgnoreCase(members.get(i).getId())){
                    throw new CheckIdException("Id already exists");
                }
            }catch (CheckIdException c){
                System.out.println(c.getMessage());
            }
        }

        if(!id_field.getText().isEmpty()&&!name_field.getText().isEmpty()&&!age_field.getText().isEmpty()){
            int age = Integer.parseInt(age_field.getText());
            int grade = Integer.parseInt(grade_field.getText());
            students.add(new Student(id_field.getText(),name_field.getText(),age,school_field.getText(),grade));
            try (FileWriter writer = new FileWriter(filePath1)) {
                gsonStudent.toJson(students, writer);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if(!id_fieldm.getText().isEmpty()&&!name_fieldm.getText().isEmpty()&&!age_fieldm.getText().isEmpty()) {
            int agem = Integer.parseInt(age_fieldm.getText());
            members.add(new External_Member(id_fieldm.getText(), name_fieldm.getText(), agem, job_field.getText(), organization_field.getText()));
            try (FileWriter writer = new FileWriter(filePath2)) {
                gsonMember.toJson(members, writer);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @FXML
    protected void showMember(ActionEvent event) {
        showbtn = (Button) event.getSource();
        show_field.clear();
        for (External_Member member : members) {
            show_field.appendText(member.toString());
            show_field.appendText("\n");
        }
        for (Student student : students) {
            show_field.appendText(student.toString());
            show_field.appendText("\n");
        }
    }

    @FXML
    protected void addMovie(ActionEvent event) {
        addbtnM = (Button) event.getSource();
        for(int i=0; i< movies.size(); i++){
            try{
                if(movieId.getText().equalsIgnoreCase(movies.get(i).getId())){
                    throw new CheckIdException("Id already exists");
                }
            }catch (CheckIdException c){
                System.out.println(c.getMessage());
            }
        }
        if(!movieId.getText().isEmpty()&&!title_field.getText().isEmpty()&&!genre_field.getText().isEmpty()){
            movies.add(new Movie(movieId.getText(),title_field.getText(),genre_field.getText(),true));
            try (FileWriter writer = new FileWriter(filePath3)) {
                gsonMovie.toJson(movies, writer);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    @FXML
    protected void showMovie(ActionEvent event) {
        showbtnM = (Button) event.getSource();
        showMovieArea.clear();
        for (Movie movie : movies) {
            showMovieArea.appendText(movie.toString());
            showMovieArea.appendText("\n");
        }
    }
}
