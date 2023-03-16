package com.example.javargr.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.javargr.Main;
import com.example.javargr.Models.UserModel;
import com.example.javargr.SupportFiles.Connection;
import com.example.javargr.Users.EntityUser;
import com.example.javargr.Users.IUser;
import com.example.javargr.Users.IndividualUser;
import com.example.javargr.Users.Type;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {

    AppController appController = null;
    AddBankAccountController addBankAccountController = null;
    BankoperationsController bankoperationsController = null;

    private static User activeUser = null;
    String username = "";

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button button;

    @FXML
    private TextField userNameField;



    @FXML
    void initialize() {
    button.setOnAction(actionEvent -> {

        username = userNameField.getText().trim();
        //Type userType = Type.NONE;


        if(!username.equals("")){
            //AddUser
           //IndividualUser user = new IndividualUser(username);

        }else{
            System.out.println("ERROR: Name is null");
        }


        //открытие второй формы
        activeUser = new User(this);
        button.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Forms/app.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        SendUserName(loader.getController());

        Parent root = loader.getRoot();

        Stage stage = new Stage();

        stage.setScene(new Scene(root));

        appController = loader.getController();
        //
        //appController.SetUser(username,activeUser);
        stage.showAndWait();

    });

    }


    void SendUserName(AppController appController){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Forms/app.fxml"));


    }




    //----------------------------------------------------------------------------



    public String GetActiveUserName(){
        return username;
    }

    public static User GetUser(){
        return activeUser;
    }

}
