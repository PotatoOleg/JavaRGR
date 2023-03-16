package com.example.javargr.Accounts;

import com.example.javargr.Users.IUser;
import com.example.javargr.Users.Type;

import java.util.ArrayList;
import java.util.List;

public class CorporateBankAccount extends BankAccount {
    private List<String> users = new ArrayList<String>();
    protected int accountId;

    AccountType type = AccountType.CORPORATE;

    //public CorporateBankAccount(){}

    public CorporateBankAccount(String user,int accountId){
        AddUser(user);
        this.accountId = accountId;
    }

    @Override
    public boolean AddConditionCheck(String user){
        /*if(user.GetUserType() == Type.ENTITY){
            return true;
        }else{
            return false;
        }*/
        return true;
    }

    @Override
    public void SetUser(String user) {
        AddUser(user);
    }

    public boolean AddUser(String user){
        if(user != null){
            users.add(user);
            return true;
        }else{
            System.out.println("Некорректный тип пользователя");
            return false;
        }
    }
}
