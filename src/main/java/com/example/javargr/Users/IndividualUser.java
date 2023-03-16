package com.example.javargr.Users;

import com.example.javargr.Accounts.BankAccount;
import com.example.javargr.SupportFiles.Operations;

import java.util.ArrayList;
import java.util.List;

public class IndividualUser implements IUser {
    private String userName;
    private Type userType = Type.INDIVIDUAL;
    private List<BankAccount> bankAccounts = new ArrayList<BankAccount>();

    private double mounthLimit = 1000000;
    private double currentMoneyTransition;

    public IndividualUser(String userName){
        this.userName = userName;
        currentMoneyTransition = 0;
    }

    public boolean AddBankAccount(BankAccount newAccount) {
        if (newAccount != null) {
            bankAccounts.add(newAccount);
            return true;
        } else {
            System.out.println("Аккаунт отсутствует");
            return false;
        }
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

    public void SetMounthLimit(double mounthLimit){
        this.mounthLimit = mounthLimit;
    }

    public double GetMounthLimit(){
        return this.mounthLimit;
    }

    public void CurrentMoneyTransitionToZero(){
        currentMoneyTransition = 0d;
    }

    public double GetCurrentMoneyTransition(){
        return currentMoneyTransition;
    }

    public boolean LimitCheck(double transferValue){
        if((currentMoneyTransition+transferValue)<mounthLimit){
            currentMoneyTransition += transferValue;
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
