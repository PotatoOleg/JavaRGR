package com.example.javargr.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class BankoperationsController {

    User activeUser = null;
    String userName =null;



    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField moneyField;

    @FXML
    private Button addMoneyButton;

    @FXML
    private Button withdrawFromAccountButton;

    @FXML
    private Button transitionButton;

    @FXML
    private TextField id_userAccount_field;

    @FXML
    private TextField id_recepientAccount_field;

    @FXML
    void initialize() {

        activeUser = Controller.GetUser();

        userName = Controller.GetUser().GetUserName();


        addMoneyButton.setOnAction(actionEvent -> {

            activeUser.AddMoneyOnAccount(Integer.parseInt(id_userAccount_field.getText()),Integer.parseInt(moneyField.getText()));

            Stage stage = (Stage) addMoneyButton.getScene().getWindow();
            stage.close();
        });

        withdrawFromAccountButton.setOnAction(actionEvent -> {

            activeUser.WithdrawMoneyFromAccount(userName,Integer.parseInt(id_userAccount_field.getText()),Integer.parseInt(moneyField.getText()));

            Stage stage = (Stage) addMoneyButton.getScene().getWindow();
            stage.close();
        });

        transitionButton.setOnAction(actionEvent -> {
            activeUser.MakeTransition(userName,Integer.parseInt(id_userAccount_field.getText()),Integer.parseInt(id_recepientAccount_field.getText()),Integer.parseInt(moneyField.getText()));
            Stage stage = (Stage) addMoneyButton.getScene().getWindow();
            stage.close();
        });

    }

}

