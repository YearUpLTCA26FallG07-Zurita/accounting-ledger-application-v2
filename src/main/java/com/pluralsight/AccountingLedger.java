package com.pluralsight;

public class AccountingLedger {

    public static String filenameBalance = "account_balance.csv";
    public static String filenameTransactions = "transactions.csv";

    public static void main(String[] args) {

        ProcessGeneral.loadTransaction();
        ProcessGeneral.loadBalance();

        Menu.menuMain();
    }
}