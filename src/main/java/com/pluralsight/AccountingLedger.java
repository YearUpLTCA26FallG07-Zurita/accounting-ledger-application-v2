package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class AccountingLedger {

    public static String filenameBalance = "account_balance.csv";
    public static String filenameTransactions = "transactions.csv";
    public static void main(String[] args) {

        ArrayList<Transaction> transactions = Process.loadTransaction();
        Process.loadBalance();
        double balance = Process.balance;
        Menu.menuMain();
    }
}

