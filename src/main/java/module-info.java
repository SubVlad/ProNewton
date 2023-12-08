module com.example.pronewton {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.pronewton to javafx.fxml;
    exports com.example.pronewton;
}