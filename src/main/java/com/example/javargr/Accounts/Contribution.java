package com.example.javargr.Accounts;

import com.example.javargr.Accounts.*;
import com.example.javargr.SupportFiles.Operations;
import com.example.javargr.Users.IUser;

public class Contribution extends BankAccount {
    private double retentionPeriod = -1;
    private int interestRate;
    private String accountUser;
    protected int accountId;

    AccountType type = AccountType.CONTRIBUTION;

    //Contribution(){}

    public Contribution(String user,int accountId){
        accountUser = user;
        this.accountId = accountId;
    }

    @Override
    public double Withdraw(double value) {
        if(retentionPeriod <0){
            Operations.instance.WithdrawFromAccount(this, value);
            return currentMoney;
        }else{
            return 0;
        }

    }

    public double GetRetentionPeriod(){
        return retentionPeriod;
    }

    public void SetRetentionPeriod(double retentionPeriod) {
        this.retentionPeriod = retentionPeriod;
    }

    public int GetInterestRate(){
        return interestRate;
    }

    public void SetInterestRate(int persent){
        interestRate = persent+1;
    }

    public double MoneyIncome(){
        currentMoney *= interestRate;
        return currentMoney;
    }

    @Override
    public void SetUser(String user) {
        if(accountUser == null){
            accountUser = user;
        }else{
            //Error:У данного аккаунта имеется пользователь
        }

    }


}
