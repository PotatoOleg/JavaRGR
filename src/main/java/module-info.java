module com.example.javargr {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javargr to javafx.fxml;
    exports com.example.javargr;
    exports com.example.javargr.Controllers;
    opens com.example.javargr.Controllers to javafx.fxml;
    exports com.example.javargr.Models;
    opens com.example.javargr.Models to javafx.fxml;

}