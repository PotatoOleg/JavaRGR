package com.example.javargr.Accounts;

import com.example.javargr.Users.*;

public class PrivateBankAccount extends BankAccount {

    private String owner;


    AccountType type = AccountType.PRIVATE;

    public PrivateBankAccount(String userOwner,int accountId){
        SetUser(userOwner);
        super.accountId = accountId;
    }

    @Override
    public void SetUser(String user) {
        if(owner == null){
            owner = user;
        }else{
            //Error:У данного аккаунта имеется пользователь
        }

    }

}
