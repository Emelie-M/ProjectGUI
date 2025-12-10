package com.example.projectgui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class HelloController {
    @FXML
    protected TextField id_field, id_fieldm, name_field, name_fieldm, age_field, age_fieldm, school_field, grade_field, job_field, organization_field, movieId, title_field,genre_field, movId, idM, ReturnMId, returnMvId, Payement_field;

    @FXML
    private Label RentedText;

    @FXML
    private DatePicker date_field, DateReturn;

    @FXML
    private TextArea show_field,showMovieArea,sMv;

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
    Alert alert = new Alert(Alert.AlertType.INFORMATION);

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
    protected void addMember() {
        boolean flag = true;
        for(int i=0; i<students.size()&&i< members.size(); i++) {
            while (flag) {
                if (id_field.getText().equalsIgnoreCase(students.get(i).getId()) || id_fieldm.getText().equalsIgnoreCase(members.get(i).getId())) {
                    flag = false;
                    Alert warningAlert = new Alert(Alert.AlertType.WARNING);
                    warningAlert.setTitle("Warning");
                    warningAlert.setHeaderText("Duplicate Id");
                    warningAlert.setContentText("Can't add member because id already exists");
                    warningAlert.showAndWait();
                }
                else {
                    if (!id_field.getText().isEmpty() && !name_field.getText().isEmpty() && !age_field.getText().isEmpty()) {
                        int age = Integer.parseInt(age_field.getText());
                        int grade = Integer.parseInt(grade_field.getText());
                        students.add(new Student(id_field.getText(), name_field.getText(), age, school_field.getText(), grade));
                        try (FileWriter writer = new FileWriter(filePath1)) {
                            gsonStudent.toJson(students, writer);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        flag = false;
                    }
                    if (!id_fieldm.getText().isEmpty() && !name_fieldm.getText().isEmpty() && !age_fieldm.getText().isEmpty()) {
                        int agem = Integer.parseInt(age_fieldm.getText());
                        members.add(new External_Member(id_fieldm.getText(), name_fieldm.getText(), agem, job_field.getText(), organization_field.getText()));
                        try (FileWriter writer = new FileWriter(filePath2)) {
                            gsonMember.toJson(members, writer);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        flag = false;
                    }
                }
            }
        }
    }

    @FXML
    protected void showMember() {
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
    protected void addMovie() {
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
    protected void showMovie() {
        showMovieArea.clear();
        for (Movie movie : movies) {
            showMovieArea.appendText(movie.toString());
            showMovieArea.appendText("\n");
        }
    }

    @FXML
    protected void addMovieRent(){
        boolean flag = false;
        for(int i=0;i<movies.size();i++){
            if(!(movies.get(i).getId().equalsIgnoreCase(movId.getText())) || movies.get(i).isAvailability()==false){
                flag=true;
            }
            else if(movies.get(i).getId().equalsIgnoreCase(movId.getText()) && movies.get(i).isAvailability()==true) {
                RentedText.setText("Movie Rented");
                alert.setTitle("Movie rented successfully");
                String date = String.valueOf(date_field);
                movies.get(i).setDate(date);
                movies.get(i).setAvailability(false);
                for (Student student : students) {
                    if (idM.getText().equalsIgnoreCase(student.getId())) {
                        movies.get(i).setStudentId(idM.getText());
                    }
                }
                for (External_Member member : members) {
                    if (idM.getText().equalsIgnoreCase(member.getId())) {
                        movies.get(i).setMemberId(idM.getText());
                    }
                }
                flag=false;
                try {
                    if(flag){
                        throw new CheckMovieException("Movie doesn't exist or not available");
                    }
                }catch (CheckMovieException e){
                    System.out.println(e.getMessage());
                }
            }
        }
        try (FileWriter writer = new FileWriter(filePath3)) {
            gsonMovie.toJson(movies, writer);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    protected void calculate(){
        Payement_field.clear();
        int days;
        try{
            for(Movie movie : movies){
                if(movie.getStudentId().equalsIgnoreCase(ReturnMId.getText())||movie.getMemberId().equalsIgnoreCase(ReturnMId.getText())){
                    if(movie.getId().equalsIgnoreCase(returnMvId.getText())){
                        movie.setAvailability(true);
                        days = DateReturn.getValue().compareTo(date_field.getValue());
                        String pay= String.valueOf(movie.calculate(days));
                        Payement_field.appendText(pay);
                        RentedText.setText("Movie Returned");
                    }
                }
                else{
                    throw new Exception();
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        try (FileWriter writer = new FileWriter(filePath3)) {
            gsonMovie.toJson(movies, writer);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    protected void showMovieR(){
        sMv.clear();
        for (Movie movie : movies) {
            sMv.appendText(movie.toString());
            sMv.appendText("\n");
        }
    }

}
