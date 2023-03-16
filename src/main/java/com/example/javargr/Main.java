package com.example.javargr;

import com.example.javargr.Models.UserModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import com.example.javargr.Accounts.*;
import com.example.javargr.SupportFiles.*;
import com.example.javargr.Users.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main extends Application {

    private Connection connection;
    private static UserModel model;

    private boolean isConnect = false;

    public boolean IsConnect(){
        return isConnect;
    }

    public void SetConnect(boolean connect){
        isConnect = connect;
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/Forms/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 400);
        stage.setTitle("RGR");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        //Отрисовка формы
        launch();
        //Связь с сервером
//        Main main = new Main();
//        model = new UserModel();
//        main.ConnectToServer();
//        while(true){
//            if(main.IsConnect()){
//
//                main.NameUserRegistration();
//                main.ReceiveMessageFromServer();
//                main.SetConnect(false);
//            }
//        }
    }

    private void ConnectToServer(){
        if(!isConnect){
            while(true){
                try{
                    Socket socket = new Socket("localhost",9999);
                    connection = new Connection(socket);
                    isConnect = true;
                    break;
                }catch (Exception e){

                }
            }
        }
    }

    protected void NameUserRegistration(){
        while(true){
            try {
                Message message = connection.Receive();         //Принятие запроса от сервера

                if(message.GetMessageType() == MessageType.REQUEST_NAME_USER){      //Если это запрос регистрации
                    //String nameUser = nameOfUser
                    //connection.Send(new Message(Message(MessageType)));  Отправляем имя пользователя (взять с интерфейса)
                }

                if(message.GetMessageType()==MessageType.ECHO){
                                                                    // имя принято
                break;
                }

            }catch (Exception e){

            }

        }
    }

    protected void SendMessageOnServer(){
        try{
            //connection.Send(new Message());
        }catch (Exception e){

        }
    }

    void AddAccountToUser(String userName,int AccountID){
        try {
            connection.Send(new Message(userName,AccountID));
        }catch (Exception e){

        }
    }

    void MakeTransition(String userName, int accountSenderID,int accountRecepientID,int transitValue){
        try {
            connection.Send(new Message(userName,accountSenderID,accountRecepientID,transitValue));
        }catch (Exception e){

        }
    }

    void AddMoneyOnAccount(int accountID,int addValue){
        try {
            connection.Send(new Message(accountID,addValue));
        }catch (Exception e){

        }
    }

    void WithdrawMoneyFromAccount(String user, int accountID,int withdrawWalue){
        try {
            connection.Send(new Message(user,accountID,withdrawWalue));
        }catch (Exception e){

        }
    }

    void BankAccountsRequest(){
        try {
            connection.Send(new Message(MessageType.GET_USER_ACCOUNTS));
        }catch (Exception e){

        }
    }



    protected void ReceiveMessageFromServer(){
        while(isConnect){
            try{
                Message message = connection.Receive();

                //

                //логика обработки сообщения в соответствии с его типом

                //

            }catch (Exception e){

            }

        }
    }
}