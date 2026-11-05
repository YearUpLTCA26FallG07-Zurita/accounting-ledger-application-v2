package com.pluralsight;

import java.io.*;
import java.util.ArrayList;

public class Process {
public static ArrayList<Transaction> transactions = new ArrayList<>();
    public static ArrayList<Transaction> loadTransaction() {

        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {

            String line;

            while ((line = reader.readLine()) != null) {
                String[] listing = line.split("\\|");
                Transaction newListing = new Transaction(listing[0], listing[1], listing[2], listing[3], Double.parseDouble(listing[4]));
            }
        } catch (IOException e) {
            System.out.println("No transactions found.");
        }
        return transactions;
    }

    public static void writeTransaction(Transaction newListing) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.csv", true))) {
            writer.write(newListing.toString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("There was an error writing.");
        }
    }

    public static void addDeposit() {
        String name = Console.promptForString("Enter the name of the payer: ");
        double deposit = Console.promptForDouble("\nEnter your amount: ");
        String description = Console.promptForString("Enter short description of the deposit: ");

        if (deposit > 0) {
            Transaction transaction = new Transaction(name, description, deposit);
            transactions.add(transaction);
            writeTransaction(transaction);
        } else {
            System.out.println("Please enter an amount greater than $0.");
        }


    }
}
