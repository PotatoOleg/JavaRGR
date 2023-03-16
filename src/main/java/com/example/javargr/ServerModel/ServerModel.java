package com.example.javargr.ServerModel;

import com.example.javargr.Accounts.*;
import com.example.javargr.SupportFiles.Connection;
import com.example.javargr.SupportFiles.Operations;
import com.example.javargr.Users.IUser;
import com.example.javargr.Users.Type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerModel {
    private Map<BankAccount, String> user_account = new HashMap<BankAccount, String>();
    private Map<String, Connection> user_connection = new HashMap<>();
    private Operations operations = new Operations<>();


    protected Map<BankAccount, String > getUserAccounts(){
        return user_account;
    }

    public void AddUser(String userName,Connection connection){
        user_connection.put(userName,connection);
    }

    public void AddAccount(String userName, int accountID){             // создание аккаунтов
//        switch (accountType){
//            case PRIVATE -> {
//                AddNewUserAccountLink(userName,new PrivateBankAccount(userName,accountID));
//                break;
//            }
//            case CORPORATE -> {
//                AddNewUserAccountLink(userName,new CorporateBankAccount(userName,accountID));
//                break;
//            }
//            case CONTRIBUTION ->{
//                AddNewUserAccountLink(userName,new Contribution(userName,accountID));
//                break;
//            }default -> {
//
//            }
//        }
        System.out.println("Model:"+userName+ "ID:"+ accountID);
        AddNewUserAccountLink(userName,new PrivateBankAccount(userName,accountID));
    }

    public void AddNewUserAccountLink(String userName,BankAccount newAccount){
        System.out.println("AddNewUserAccountLink :"+ userName+ "ID"+ newAccount.GetAccountId());
        user_account.put(newAccount,userName);                                                          //поменять местами ключ и акк

    }

    //Возвращает список аккаунтов пользователя, размещённых на сервере
    public List<BankAccount> GetAccountsOfUser(String userName){
        List<BankAccount> accountsOfUser = new ArrayList<>();

        for(Map.Entry< BankAccount ,String>entry: user_account.entrySet()){
            String userKey = entry.getValue();
            System.out.println("FOR_STATMENT: "+userKey);

            System.out.println(userName+" ?= "+userKey);
            if(userKey.equals(userName)){
                System.out.println("IF_STATMENT: "+userKey);
                System.out.println(entry.getKey().GetAccountId());
                accountsOfUser.add(entry.getKey());
            }
        }

        System.out.println(user_account.size()+ " User_account_size");

        return accountsOfUser;
    }

    public boolean TransitMoney(String senderName,int idAccountSender,int idAccountRecepient,int transitValue){
        BankAccount sender = null;
        BankAccount recepient = null;

        for(Map.Entry< BankAccount ,String>entry: user_account.entrySet()){                       ///// поиск аккаунта отправителя среди аккаунтов, находящихся на сервере по имени отправителя и id-аккаунта
            String userKey = entry.getValue();
            if(userKey.equals(senderName) && entry.getKey().GetAccountId()==idAccountSender){
                sender=entry.getKey();
            }
        }
        if(sender==null){
            return false;
        }

        for(Map.Entry< BankAccount ,String>entry: user_account.entrySet()){                       ///// поиск аккаунта получателя среди аккаунтов, находящихся на сервере по имени получателя и id-аккаунта
            if(entry.getKey().GetAccountId()==idAccountRecepient){
                recepient=entry.getKey();
            }
        }
        if(recepient==null){
            return false;
        }

        if(Operations.instance.Transfer(sender,recepient,transitValue)){                        //// перевод средств с аккаунта на аккаунт
            return true;
        }else{
            return false;
        }
    }

    public boolean addMoneyOnAccount(int accountID,int addValue){
        BankAccount account = null;
        System.out.println("AddMoney acc id: "+ accountID+ " money: "+addValue);

        System.out.println("Размер мапы "+user_account.size());

        for(Map.Entry< BankAccount ,String>entry: user_account.entrySet()){                       ///// поиск аккаунта с нужным ID
            System.out.println(entry.getKey().GetAccountId());
            if(entry.getKey().GetAccountId() == accountID) {

                account = entry.getKey();
            }
        }

        System.out.println("Пойманный аккаунт"+account.GetAccountId());

        if(account==null){
            System.out.println("Povorot ne tuda");
            return  false;
        }else{
            System.out.println("Povorot tuda");
            Operations.instance.AddMoney(account,addValue);  //пополнение счёта
            return true;
        }


    }

    public boolean withdrawMoneyFromAccount(String user, int accountID,int withdrawWalue){
        BankAccount account = null;

        for(Map.Entry<BankAccount ,String >entry: user_account.entrySet()){                       ///// поиск аккаунта с нужным ID
            String usernameKey = entry.getValue();
            if(usernameKey == user && entry.getKey().GetAccountId() == accountID) {
                account = entry.getKey();
            }
        }

        if(account == null){
            return false;
        }

        if(Operations.instance.WithdrawFromAccount(account,withdrawWalue)){
            return true;
        }else {
            return  false;
        }

    }

}
