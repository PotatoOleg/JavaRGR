package com.example.javargr;

import com.example.javargr.Accounts.BankAccount;
import com.example.javargr.Accounts.PrivateBankAccount;
import com.example.javargr.ServerModel.ServerModel;
import com.example.javargr.SupportFiles.Connection;
import com.example.javargr.SupportFiles.Message;
import com.example.javargr.SupportFiles.MessageType;
import com.example.javargr.SupportFiles.Operations;
import com.example.javargr.Users.IUser;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;

public class Server {
    private ServerSocket serverSocket;

    private  static ServerModel model;
    private static boolean isServerRunning = false;


    public static void main(String[] args) {
    Server server = new Server();
    model = new ServerModel();
    server.StartServer();

    while(true){
        if(isServerRunning){
            server.AcceptServer();
            isServerRunning =false;
        } else if (!isServerRunning) {
            break;
        }
    }
        server.StopServer();
    }

    private void StartServer(){             // запуск сервера
        try {
            serverSocket = new ServerSocket(9999);
            isServerRunning = true;

        }catch (Exception e){

        }
    }

    private void StopServer(){                      // остановка сервера
        try{
            if(serverSocket != null && !serverSocket.isClosed()){
            //user_account.clear();
            serverSocket.close();
            }
        }catch (Exception e){

        }
    }

    protected void AcceptServer(){
        while (true){
            try {
                Socket socket = serverSocket.accept();
                new ServerThread(socket).start();
            } catch (IOException e) {

            }
        }
    }

    private class ServerThread extends Thread{
        private Socket socket;
        private boolean isRunning;
        private String username;

        public ServerThread(Socket socket){
            this.socket=socket;
            System.out.println(socket.getLocalAddress());

            isRunning = true;

        }

        private  String RequestUserName(Connection connection) throws IOException {     //апрос имени активного пользователя
            while (true){
                try{
                    connection.Send(new Message(MessageType.REQUEST_NAME_USER));
                    Message responseMessage = connection.Receive();
                    username = responseMessage.GetUserSender();
                }catch (Exception e){

                }
            }

        }

        //запрос на добавление пользователя
//        private String AddUser(Connection connection){
//            while(true){
//                try{
//
//                    connection.Send(new Message(MessageType.REQUEST_NAME_USER));    // создание соединения с сервером 1-го клиента и отправка запроса имени
//                    Message responceMessage = connection.Receive();                 // принятие ответа
//                    String userName = responceMessage.GetUserSender();              // вычлинение имени пользователя из полученного сообщения
//
//                    if(userName!="") {
//                        model.AddUser(userName, connection);                             // добавление клиента к перечню пользователей
//                        connection.Send(new Message(MessageType.ECHO));                 // ответное сообщение сервера
//
//                        return userName;
//                    }else{
//                        connection.Send(new Message(MessageType.NAME_IS_ON_USE));
//                    }
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                } catch (ClassNotFoundException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }

        //принятие сообщений
        private void TakeMassage(Connection connection, String username){
            while(true){
                try{
                    Message message = connection.Receive();     //принимаев входящее сообщение


                    ////

                    //Область обработки типов запросов

                    ////

                }catch (Exception e){
                    break;
                }
            }
        }

        private void RequestAccept(Connection connection,String username){
            while (isRunning){
                try {

                    Message message = connection.Receive();

                    switch (message.GetMessageType()){
                        case ADD_BANK_ACCOUNT -> {

                            model.AddAccount(message.GetUserSender(),message.GetUserBankAccountID());
                            System.out.println("Аккаунт добавлен ID "+ message.GetUserBankAccountID());
                            break;
                        }case GET_USER_ACCOUNTS -> {
                            //System.out.println("Sender!!----"+message.GetUserSender());
                            List<BankAccount> accounts= model.GetAccountsOfUser(message.GetUserSender());
                            //System.out.println(accounts.size());
                            //System.out.println(ConvertAccountsArray(accounts));
                            connection.Send(new Message(ConvertAccountsArray(accounts),MessageType.GET_ACCOUNTS_ECHO));
                            //System.out.println("Отправляю список аккаунтов");

                            break;
                        }case ADD_MONEY -> {
                            model.addMoneyOnAccount(message.GetUserBankAccountID(),message.GetMoneyValue());
                            break;
                        }case TRANSIT_MONEY -> {
                            model.TransitMoney(message.GetUserSender(),message.GetUserBankAccountID(),message.GetRecepientBankAccountID(),message.GetMoneyValue());
                            break;
                        }case WIDTHROW_MONEY -> {
                            System.out.println("Запрос на снятие средств");
                            model.withdrawMoneyFromAccount(message.GetUserSender(),message.GetUserBankAccountID(),message.GetMoneyValue());
                            break;
                        }case END_SESSION -> {

                            isRunning = false;
                            break;
                        }
                    }
                } catch (Exception e){

                }
            }
        }

        private String ConvertAccountsArray(List<BankAccount> arr){
            String accToParce="";

            System.out.println("Количество аккаунтов"+arr.size());

            if(arr.size()!=0){
                for (BankAccount acc:arr){
                    System.out.println(acc.GetAccountId());
                    accToParce = accToParce + String.valueOf(acc.GetAccountId()) + " " + acc.GetCurrentMoney()+ " ";
                }

            }
            System.out.println("Распарсенные аккаунты"+accToParce);
            return accToParce;
        }

        @Override
        public void run(){
            try {
                Connection connection = new Connection(socket);

                //String username = AddUser(connection);
                //AddUser(connection);
                //RequestUserName(connection);
                RequestAccept(connection,username);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            //Сюды запихать функцию обработки запросов
            for(int i=0;i<10;i++){
                System.out.println(i);
            }
            /*while(true){
                RequestAccept();
            }*/
        }
    }







}
