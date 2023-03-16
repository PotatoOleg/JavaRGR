package com.example.javargr.Controllers;

import com.example.javargr.Accounts.BankAccount;
import com.example.javargr.Models.UserModel;
import com.example.javargr.SupportFiles.Connection;
import com.example.javargr.SupportFiles.Message;
import com.example.javargr.SupportFiles.MessageType;

import java.net.Socket;
import java.util.List;

public class User {
    private Connection connection;
    private static UserModel model;

    private User user;
    private boolean isConnect = false;

    public boolean IsConnect(){
        return isConnect;
    }

    public void SetConnect(boolean connect){
        isConnect = connect;
    }
    private Controller mainController;
    private String username;

    private AppController appController = null;

    public User(Controller controller){
        System.out.println("User created!");
        user = this;
        model = new UserModel();
        mainController = controller;
        username = controller.GetActiveUserName();

        ConnectToServer(); //соединяемся с сервером при входе пользователя
        UserThreat listener = new UserThreat(connection,appController);
        listener.start();
    }

    void ConnectToServer(){
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

    public void StopConnection(){
        isConnect = false;
    }

    public String GetUserName(){
        return username;
    }

    public void SetAppController(AppController appController){
        this.appController = appController;
    }

    protected void SendMessageOnServer(){
        try{
            //connection.Send(new Message());
        }catch (Exception e){

        }
    }

    void AddAccountToUser(String userName,int AccountID){
        try {
            System.out.println("Добавление аккаунта");
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
            connection.Send(new Message(username,MessageType.GET_USER_ACCOUNTS));
            System.out.println("Сообщение отправлено");
        }catch (Exception e){

        }
    }





    class UserThreat extends Thread{
        private Connection connection;
        AppController appController;
        UserThreat(Connection connection,AppController appController){
            System.out.println("UserthreadCreated!");

            this.appController = appController;
            this.connection = connection;
        }

        @Override
        public void run(){
            while(user.IsConnect()){
                if(user.IsConnect()){
                    System.out.println("Reciving messeges...");
                    ReceiveMessageFromServer();
                    //user.SetConnect(false);
                }
            }
        }

        protected void ReceiveMessageFromServer() {
            while (isConnect) {
                try {
                    Message message = connection.Receive();

                    switch (message.GetMessageType()) {
                        case GET_ACCOUNTS_ECHO -> {
                            //System.out.println("!!!");
                            String usersBankAccountsToParce = message.GetBankAccounts();
                            //System.out.println(usersBankAccountsToParce);
                            AppController.instance.RefreshAccountList(usersBankAccountsToParce);
                            //System.out.println("Список аккаунтов обновлён!");
                            //Обновить список аккаунтов пользователей на панели
                            break;
                        }
                        case NAME_IS_ON_USE -> {
                            break;
                        }
                        case MONEY_ADDED -> {
                            break;
                        }
                        case REQUEST_NAME_USER -> {
                            NameUserRegistration();
                            break;
                        }
                    }
                    //

                    //логика обработки сообщения в соответствии с его типом

                    //

                } catch (Exception e) {

                }

                }
            }
        protected void NameUserRegistration(){
            String username =mainController.GetActiveUserName();
            if(username!=""){
                SendUserName(username);
            }

        }

        void SendUserName(String username){
            try {
                connection.Send(new Message(username));
            }catch (Exception e){

            }
        }



    }


}
