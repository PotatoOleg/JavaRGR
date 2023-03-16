package com.example.javargr.SupportFiles;

import com.example.javargr.Accounts.BankAccount;

public class Operations<T extends Number> {
    public static Operations instance;
    private boolean transactionOngoing = false;

    public Operations(){
        instance = this;
    }

    public boolean WithdrawFromAccount(BankAccount acc, T value){
        if (!acc.IsBlocked()) {
            transactionOngoing = true;
            acc.setCurrentMoney(acc.GetCurrentMoney()-value.doubleValue());
            transactionOngoing = false;
            return true;
        } else {
            return false;
        }
    }

    public boolean AddMoney(BankAccount acc,T income) {
        if(!acc.IsBlocked()){
            //System.out.println("addMoneyOperation");
            transactionOngoing = true;
            acc.setCurrentMoney(acc.GetCurrentMoney()+income.doubleValue());
            //System.out.println("Баланс после пополнения"+acc.GetCurrentMoney());
            transactionOngoing = false;
            return true;
        }else{
            return false;
        }
    }

    public boolean Transfer(BankAccount sender, BankAccount recipient, T transferValue) {
        if (!sender.IsBlocked() && !recipient.IsBlocked()) {
            transactionOngoing = true;
            sender.setCurrentMoney(sender.GetCurrentMoney()-transferValue.doubleValue());
            AddMoney(recipient,transferValue);
            transactionOngoing = false;
            return true;

        } else {
            return false;
        }
    }


}
