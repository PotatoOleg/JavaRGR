package com.example.javargr.Accounts;

import com.example.javargr.SupportFiles.Operations;
import com.example.javargr.Users.IUser;


public abstract class BankAccount {
    protected double currentMoney;
    protected boolean isBlocked=false;
    protected String currentUser;
    protected int accountId;

    //public BankAccount(){}

    public double Withdraw(double value) {

        if(Operations.instance.WithdrawFromAccount(this, value)){
            return currentMoney;
        }else{
            return Double.MIN_VALUE;
        }
    }

    public boolean IsBlocked() {
        return isBlocked;
    }

    public double GetCurrentMoney() {
        if (!isBlocked) {
            return currentMoney;
        } else {
            return Double.MIN_VALUE;
        }
    }

    public int GetAccountId(){
        return accountId;
    }
    public void setCurrentMoney(double currentMoney) {
        this.currentMoney = currentMoney;
    }

    public String getUser(){return currentUser;}

    public void SetBlock(boolean block){
        isBlocked = block;
    }

    public boolean AddConditionCheck(String user){
        return true;
    }

    public abstract void SetUser(String user);
}
