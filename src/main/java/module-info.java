module com.example.projectgui {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens com.example.projectgui to javafx.fxml;
    exports com.example.projectgui;
}