module com.ubb.en.attendaceapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.ubb.en.attendaceapp to javafx.fxml;
    opens com.ubb.en.attendaceapp.controller to javafx.fxml;
    opens com.ubb.en.attendaceapp.model to javafx.fxml;
    exports com.ubb.en.attendaceapp;
    exports com.ubb.en.attendaceapp.controller;
    exports com.ubb.en.attendaceapp.model;

}