package com.example.javargr.Controllers;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.example.javargr.Accounts.BankAccount;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AppController {

        private String userName = null;
        private User activeUser = null;

        public static AppController instance;


        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private Text usernameText;

        @FXML
        private Button refreshAccountsButton;

        @FXML
        private Button AddAccountButton;
        @FXML
        private Button OpenOperationsButton;

        @FXML
        private TextArea textField;

        @FXML
        void initialize() {

            instance = this;
            userName = Controller.GetUser().GetUserName();

            usernameText.setText(userName);

            activeUser = Controller.GetUser();

            activeUser.SetAppController(this);

            refreshAccountsButton.setOnAction(actionEvent -> {
                activeUser.BankAccountsRequest();

                System.out.println("BUTTON");

            });

            OpenOperationsButton.setOnAction(actionEvent -> {

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/Forms/BankOperations.fxml"));

                try {
                    loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                Parent root = loader.getRoot();

                Stage stage = new Stage();

                stage.setScene(new Scene(root));
                stage.showAndWait();

            });

            AddAccountButton.setOnAction(actionEvent -> {

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/Forms/AddBankAccount.fxml"));

                try {
                    loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                Parent root = loader.getRoot();

                Stage stage = new Stage();

                stage.setScene(new Scene(root));
                stage.showAndWait();


                //activeUser.AddAccountToUser(userName,7);
            });




        }


        
        public void RefreshAccountList(String stringToParce){
            String[] words = stringToParce.split(" ");

            textField.setText("");

            for(int i = 0;i<words.length;i++){
                if(i%2 == 0){
                    textField.appendText("ID: "+words[i] + " ");
                }else{
                    textField.appendText("Current money: "+words[i]+"р");
                    textField.appendText("\n");
                }

            }
        }

        public void CloseWindow(Stage stage){
            activeUser.StopConnection();
            stage.close();
        }


    }


