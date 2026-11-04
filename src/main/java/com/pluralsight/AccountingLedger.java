package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class AccountingLedger {
    public static ArrayList<Transaction> transactions = new ArrayList<>();
    public static void main(String[] args) throws InterruptedException, IOException {

        try {
            BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] listing = line.split("\\|");
                Transaction t = new Transaction(listing[0], listing[1], listing[2], listing[3], Double.parseDouble(listing[4]));
            }
        }
        catch (IOException e) {
            System.out.println("No transactions found.");
        }

       // try (BufferedWriter transactions = new BufferedWriter(new FileWriter("transactions.csv"))) {

        Transaction t = new Transaction(LocalDate.now().toString(), LocalTime.now().toString(), "Paycheck", "Employer", 500.00);



       // Menu.menuMain();
    }
}
