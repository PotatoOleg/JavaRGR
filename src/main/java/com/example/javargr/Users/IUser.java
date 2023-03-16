package com.example.javargr.Users;

import com.example.javargr.Accounts.BankAccount;

import java.util.List;

public interface IUser {

    public String GerUserName();

    public Type GetUserType();

    public List<BankAccount> GetBankAccountList();

    public double GetMoneySum();

    public boolean AddBankAccount(BankAccount acc);

    public boolean Transfer(BankAccount sender,BankAccount recipient, double transferValue);

}
