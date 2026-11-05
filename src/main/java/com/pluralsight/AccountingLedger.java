package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class AccountingLedger {

    public static void main(String[] args) throws InterruptedException, IOException {

        ArrayList<Transaction> transactions = Process.loadTransaction();
        Menu.menuMain();
    }
}

