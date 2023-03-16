package com.example.javargr.Users;

import com.example.javargr.Accounts.BankAccount;
import com.example.javargr.SupportFiles.Operations;

import java.util.ArrayList;
import java.util.List;

public class EntityUser implements IUser {
    private String userName;
    private Type userType = Type.ENTITY;
    private List<BankAccount> bankAccounts = new ArrayList<BankAccount>();

    public EntityUser(String userName){
        this.userName = userName;
    }

    @Override
    public String GerUserName() {
        return userName;
    }

    @Override
    public Type GetUserType() {
        return userType;
    }

    @Override
    public List<BankAccount> GetBankAccountList() {
        if (!bankAccounts.isEmpty()) {
            return bankAccounts;
        } else {
            return null;
        }
    }

    @Override
    public double GetMoneySum() {
        double sum = 0;

        for (BankAccount bankAccount : bankAccounts) {
            sum += bankAccount.GetCurrentMoney();
        }

        return sum;
    }

    @Override
    public boolean AddBankAccount(BankAccount acc) {
        if(acc != null){
            bankAccounts.add(acc);
            acc.SetUser(this.userName);
            return true;
        }else{
            return false;
        }

    }

    @Override
    public boolean Transfer(BankAccount sender, BankAccount recipient, double transferValue) {
        if (Operations.instance.Transfer(sender, recipient, transferValue)) {
            return true;
        } else {
            return false;
        }
    }


}
