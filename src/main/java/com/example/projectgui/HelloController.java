package com.example.projectgui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class HelloController {

    @FXML
    protected TextField id_field,  name_field, firstField, secondField, age_field, movieId, title_field,genre_field, movId, idM, ReturnMId, returnMvId, Payement_field;

    @FXML
    protected ComboBox<String> choiceMember;

    @FXML
    protected Label RentedText, firstLabel, secondLabel;

    @FXML
    protected DatePicker date_field, DateReturn;

    @FXML
    protected TextArea studentArea, memberArea, showMovieArea, sMv;

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
        studentArea.clear();
        memberArea.clear();
        for (External_Member member : members) {
            memberArea.appendText(member.toString());
            memberArea.appendText("\n");
        }
        for (Student student : students) {
            studentArea.appendText(student.toString());
            studentArea.appendText("\n");
        }
        showMovieArea.clear();
        for (Movie movie : movies) {
            showMovieArea.appendText(movie.toString());
            showMovieArea.appendText("\n");
        }
        choiceMember.setItems(FXCollections.observableArrayList("Student", "External Member"));
        choiceMember.setPromptText("Choice");
        sMv.clear();
        for (Movie movie : movies) {
            sMv.appendText(movie.toString2());
            sMv.appendText("\n");
        }
    }

    @FXML
    protected void choice(){
        String value = choiceMember.getValue();
        if (value != null && !value.trim().isEmpty()) {
            if(value.equals("Student")){
                firstLabel.setText("School :");
                firstField.setPromptText("School Name");
                secondLabel.setText("Grade :");
                secondField.setPromptText("Grade");
            }
            if(value.equals("External Member")){
                firstLabel.setText("Job :");
                firstField.setPromptText("Job Name");
                secondLabel.setText("Organization :");
                secondField.setPromptText("Organization Name");
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Select Option");
            alert.showAndWait();
        }
    }

    @FXML
    protected void addMember() {
        for(int i=0; i<students.size()&&i< members.size(); i++) {
            boolean flag = true;
                if (id_field.getText().equalsIgnoreCase(students.get(i).getId()) || id_field.getText().equalsIgnoreCase(members.get(i).getId())) {
                    flag = false;
                    Alert warningAlert = new Alert(Alert.AlertType.WARNING);
                    warningAlert.setTitle("Warning");
                    warningAlert.setHeaderText("Duplicate Id");
                    warningAlert.setContentText("Can't add member because id already exists");
                    warningAlert.showAndWait();
                }
                else {
                    if (!id_field.getText().isEmpty() && !name_field.getText().isEmpty() && !age_field.getText().isEmpty()) {
                        try {
                            if (!id_field.getText().matches("[A-Z]\\d+")) {
                                throw new Exception();
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            flag = false;
                            Alert IdAlert = new Alert(Alert.AlertType.INFORMATION);
                            IdAlert.setTitle("Info");
                            IdAlert.setHeaderText("ID wrongly put");
                            IdAlert.setContentText("The Id should start with a capital letter followed with numbers");
                            IdAlert.showAndWait();
                        }
                        try {
                            if (!name_field.getText().matches("[A-Za-z]")) {
                                throw new Exception();
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            flag = false;
                            Alert NameAlert = new Alert(Alert.AlertType.INFORMATION);
                            NameAlert.setTitle("Info");
                            NameAlert.setHeaderText("Name wrongly put");
                            NameAlert.setContentText("The name should only contain letters");
                            NameAlert.showAndWait();
                        }
                        int age = Integer.parseInt(age_field.getText());
                        int grade = Integer.parseInt(secondField.getText());
                        try {
                            if (age > 100 && age < 16 && !age_field.getText().matches("[0-9]")) {
                                throw new Exception();
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            flag = false;
                            Alert AgeAlert = new Alert(Alert.AlertType.INFORMATION);
                            AgeAlert.setTitle("Info");
                            AgeAlert.setHeaderText("Age wrongly put");
                            AgeAlert.setContentText("The age should only contain numbers between 16 & 100");
                            AgeAlert.showAndWait();
                        }
                        try {
                            if (grade < 0 && grade > 100) {
                                throw new Exception();
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            flag = false;
                            Alert GradeAlert = new Alert(Alert.AlertType.INFORMATION);
                            GradeAlert.setTitle("Info");
                            GradeAlert.setHeaderText("Grade wrongly put");
                            GradeAlert.setContentText("The grade should only contain numbers");
                            GradeAlert.showAndWait();
                        }
                        try{
                            if(!firstField.getText().matches("[A-Za-z]")||!secondField.getText().matches("[A-Za-z]")){
                                throw new Exception();
                            }
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                            Alert InfoAlert = new Alert(Alert.AlertType.INFORMATION);
                            InfoAlert.setTitle("Info");
                            InfoAlert.setHeaderText("Input wrongly put");
                            InfoAlert.setContentText("The input should contain letters");
                            InfoAlert.showAndWait();
                        }
                        if (flag) {
                            students.add(new Student(id_field.getText(), name_field.getText(), age, firstField.getText(), grade));
                            members.add(new External_Member(id_field.getText(), name_field.getText(), age, firstField.getText(), secondField.getText()));
                            try (FileWriter writer = new FileWriter(filePath1)) {
                                gsonStudent.toJson(students, writer);
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        try (FileWriter writer = new FileWriter(filePath2)) {
                            gsonMember.toJson(members, writer);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

        }
        studentArea.clear();
        memberArea.clear();
        for (External_Member member : members) {
            memberArea.appendText(member.toString());
            memberArea.appendText("\n");
        }
        for (Student student : students) {
            studentArea.appendText(student.toString());
            studentArea.appendText("\n");
        }
    }

    @FXML
    protected void listMember(){
        studentArea.clear();
        memberArea.clear();
        for (External_Member member : members) {
            memberArea.appendText(member.toString());
            memberArea.appendText("\n");
        }
        for (Student student : students) {
            studentArea.appendText(student.toString());
            studentArea.appendText("\n");
        }
    }

    @FXML
    protected void addMovie() {
        boolean flag = true;
        for(int i=0; i< movies.size(); i++) {
            if (movieId.getText().equalsIgnoreCase(movies.get(i).getId())) {
                flag = false;
                Alert warningAlert = new Alert(Alert.AlertType.WARNING);
                warningAlert.setTitle("Warning");
                warningAlert.setHeaderText("Duplicate Id");
                warningAlert.setContentText("Can't add movie because id already exists");
                warningAlert.showAndWait();
            } else {
                if (!movieId.getText().isEmpty() && !title_field.getText().isEmpty() && !genre_field.getText().isEmpty()) {
                    try{
                        if(!movieId.getText().matches("[A-Z]\\d+")) {
                            throw new Exception();
                        }
                    }catch (Exception e) {
                        System.out.println(e.getMessage());
                        flag = false;
                        Alert IdAlert = new Alert(Alert.AlertType.INFORMATION);
                        IdAlert.setTitle("Info");
                        IdAlert.setHeaderText("ID wrongly put");
                        IdAlert.setContentText("The Id should start with a capital letter followed with numbers");
                        IdAlert.showAndWait();
                    }
                    try{
                        if(!genre_field.getText().matches("[A-Za-z]+")){
                            throw new Exception();
                        }
                    }catch (Exception e) {
                        System.out.println(e.getMessage());
                        flag = false;
                        Alert GenreAlert = new Alert(Alert.AlertType.INFORMATION);
                        GenreAlert.setTitle("Info");
                        GenreAlert.setHeaderText("Genre wrongly put");
                        GenreAlert.setContentText("The genre should only contain letters");
                        GenreAlert.showAndWait();
                    }
                    if(flag) {
                        movies.add(new Movie(movieId.getText(), title_field.getText(), genre_field.getText(), true));
                        try (FileWriter writer = new FileWriter(filePath3)) {
                            gsonMovie.toJson(movies, writer);
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                }
                showMovieArea.clear();
                for (Movie movie : movies) {
                    showMovieArea.appendText(movie.toString());
                    showMovieArea.appendText("\n");
                }
                flag = false;
            }
        }
    }

    @FXML
    protected void listMovies() {
        showMovieArea.clear();
        for (Movie movie : movies) {
            showMovieArea.appendText(movie.toString());
            showMovieArea.appendText("\n");
        }
    }

    @FXML
    protected void addMovieRent(){
        boolean flag = true;
        for(int i=0;i<movies.size();i++){
            if(!(movies.get(i).getId().equalsIgnoreCase(movId.getText())) || movies.get(i).isAvailability()==false){
                flag=false;
                Alert warningAlert = new Alert(Alert.AlertType.WARNING);
                warningAlert.setTitle("Warning");
                warningAlert.setHeaderText("Id not found or Movie not available");
                warningAlert.setContentText("Can't rent movie that doesn't exist or that is not available");
                warningAlert.showAndWait();
            }
            try{
                if(!movId.getText().matches("[A-Z]\\d+")||!idM.getText().matches("[A-Z]\\d+")) {
                    throw new Exception();
                }
            }catch (Exception e) {
                System.out.println(e.getMessage());
                flag = false;
                Alert IdAlert = new Alert(Alert.AlertType.INFORMATION);
                IdAlert.setTitle("Info");
                IdAlert.setHeaderText("ID wrongly put");
                IdAlert.setContentText("The Id should start with a capital letter followed with numbers");
                IdAlert.showAndWait();
            }
            if(movies.get(i).getId().equalsIgnoreCase(movId.getText()) && movies.get(i).isAvailability()==true && flag==true) {
                String date = String.valueOf(date_field);
                movies.get(i).setDate(date);
                movies.get(i).setAvailability(false);
                String test = String.valueOf(date_field);
                System.out.println(test);
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
                try (FileWriter writer = new FileWriter(filePath3)) {
                    gsonMovie.toJson(movies, writer);
                } catch (Exception e) {
                    System.out.println(e);
                }
                flag = false;
            }
        }
        sMv.clear();
        for (Movie movie : movies) {
            sMv.appendText(movie.toString2());
            sMv.appendText("\n");
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
                        movie.setStudentId(null);
                        movie.setMemberId(null);
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
        sMv.clear();
        for (Movie movie : movies) {
            sMv.appendText(movie.toString2());
            sMv.appendText("\n");
        }
    }

    @FXML
    protected void listRent(){
        sMv.clear();
        for (Movie movie : movies) {
            sMv.appendText(movie.toString2());
            sMv.appendText("\n");
        }
    }

}
