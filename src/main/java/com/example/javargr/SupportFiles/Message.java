package com.example.javargr.SupportFiles;

import com.example.javargr.Accounts.AccountType;
import com.example.javargr.Accounts.BankAccount;
import com.example.javargr.Accounts.PrivateBankAccount;
import com.example.javargr.Users.Type;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Message implements Serializable {
    private MessageType messageType;
    private String bankAccounts;
    private String userSender;
    private String userRecepient;
    private int userBankAccountID;
    private int recepientBankAccountID;

    private int moneyValue;

    AccountType accountType;




    public Message(MessageType type){

        messageType = type;

        userSender = null;
        userBankAccountID = 0;
        accountType = null;
        userRecepient = null;
        recepientBankAccountID = 0;
        moneyValue = 0;
        bankAccounts = null;
    }

    public Message(String userName, int accountID){           //  создание аккаунту пользователя

        messageType = MessageType.ADD_BANK_ACCOUNT;

        userSender = userName;
        userBankAccountID = accountID;
        accountType = null;
        userRecepient = null;
        recepientBankAccountID = 0;
        moneyValue = 0;
        bankAccounts =null;

    }

    public Message(String userName){                                 // добавление пользователя/запрос от сервера

        messageType = MessageType.ADD_USER;

        userSender = userName;
        userBankAccountID = 0;
        accountType = null;
        userRecepient = null;
        recepientBankAccountID = 0;
        moneyValue = 0;
        bankAccounts = null;

    }


    public Message(String senderName,int idAccountSender,int idAccountRecepient,int transitValue){  //денежный перевод между счетами

        messageType = MessageType.TRANSIT_MONEY;

        userSender = senderName;
        userBankAccountID = idAccountSender;
        accountType = null;
        userRecepient = null;
        recepientBankAccountID = idAccountRecepient;
        moneyValue = transitValue;
        bankAccounts = null;
    }

    public Message(int accountID,int addValue){         // добавление денежной суммы

        messageType = MessageType.ADD_MONEY;

        userSender = null;
        userBankAccountID = accountID;
        accountType = null;
        userRecepient = null;
        recepientBankAccountID = 0;
        moneyValue = addValue;
        bankAccounts = null;

    }

    public Message(String user, int accountID,int withdrawWalue){       // снятие денежных средств
        messageType = MessageType.WIDTHROW_MONEY;

        userSender = user;
        userBankAccountID = accountID;
        accountType = null;
        userRecepient = null;
        recepientBankAccountID = 0;
        moneyValue = withdrawWalue;
        bankAccounts = null;
    }

    public Message(String accounts_userName,MessageType mesType){         // возвращение списка аккаунтов пользователя
        messageType = mesType;

        if(mesType == MessageType.GET_USER_ACCOUNTS){
            bankAccounts = null;
            userSender = accounts_userName;
        } else if (mesType==MessageType.GET_ACCOUNTS_ECHO) {
            bankAccounts = accounts_userName;
            userSender = null;
        }
        //userSender = null;
        userBankAccountID = 0;
        accountType = null;
        userRecepient = null;
        recepientBankAccountID = 0;
        moneyValue = 0;
        //bankAccounts = accounts_userName;
        //System.out.println(accounts.size());
    }

    public MessageType GetMessageType(){
        return  messageType;
    }
    public String GetUserSender(){
        return  userSender;
    }
    public int GetUserBankAccountID(){
        return  userBankAccountID;
    }
    public AccountType GetAccountType(){
        return  accountType;
    }
    public String GetUserRecepient(){
        return  userRecepient;
    }
    public int GetRecepientBankAccountID(){
        return  recepientBankAccountID;
    }
    public int GetMoneyValue(){
        return  moneyValue;
    }
    public String GetBankAccounts(){
        return  bankAccounts;
    }

}
