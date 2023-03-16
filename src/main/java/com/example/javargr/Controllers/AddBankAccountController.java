package com.example.javargr.Controllers;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddBankAccountController {

    String userName = "";
    User activeUser = null;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField accountId;

    @FXML
    private Button addAccountButton;

    @FXML
    void initialize() {

        activeUser = Controller.GetUser();
        userName = Controller.GetUser().GetUserName();

        addAccountButton.setOnAction(actionEvent -> {
            if(accountId.getText()!="") {
                activeUser.AddAccountToUser(userName, Integer.parseInt(accountId.getText()));

                Stage stage = (Stage) addAccountButton.getScene().getWindow();
                stage.close();
            }
        });

    }


}

