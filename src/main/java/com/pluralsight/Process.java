package com.pluralsight;

import java.io.*;
import java.util.ArrayList;

public class Process {
    public static ArrayList<Transaction> transactions = new ArrayList<>();

    /**
     * Method that uses a BufferedReader to read off every entry in transactions.csv and load them into the program to be used in the Ledger.
     */
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

    /**
     * Method that uses a BufferedWriter to take constructed Transaction objects and enter them into the transactions.csv file. BufferedReader will then take those values and do its aforementioned job.
     */
    public static void writeTransaction(Transaction newListing) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.csv", true))) {
            writer.write(newListing.toString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("There was an error writing.");
        }
    }

    /**
     * Method that keeps track of account balance and updates it accordingly.
     */
    public static double loadBalance(double change) {
        double balance = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader("account_balance.csv"))) {
            String line = reader.readLine();
            if (line != null) {
                balance = Double.parseDouble(line.trim());
            }
        } catch (FileNotFoundException e) {
            // first run, start at 0
        } catch (IOException | NumberFormatException e) {
            System.out.println("Balance could not be read.");
        }

        balance += change;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("account_balance.csv"))) {
            writer.write(String.format("%.2f", balance));
        } catch (IOException e) {
            System.out.println("Balance could not be saved.");
        }

        return balance;
    }

    /**
     * Method used in the Deposits menu. Method will ask the user deposit information.
     * Method then constructs Transaction object out of this information to send to writeTransaction().
     * Also, date and time are never prompted to the user; by default, formatted LocalDate and LocalTime assign them.
     * Lastly, account balance is updated and saved in a separate file.
     */
    public static double balance = 0;
    public static void addDeposit() {
        System.out.println("-------------------------------------");
        String name = Console.promptForString("Enter the name of the payer: ");
        double deposit = Console.promptForDouble("Enter deposit amount: ");
        String description = Console.promptForString("Enter short description of the deposit: ");
        System.out.println("-------------------------------------");

        if (deposit > 0) {
            Transaction transaction = new Transaction(description, name, deposit);
            transactions.add(transaction);
            writeTransaction(transaction);
            balance = loadBalance(deposit);
            System.out.printf("Deposit recorded. New balance: $%.2f%n", balance);
        } else {
            System.out.println("Please enter an amount greater than $0.");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("account_balance.csv"))) {
                writer.write(String.format("$%.2f", balance));
                writer.newLine();
            } catch (IOException e) {
                System.out.println("There was an error writing.");
            }
        }
    }
    public static void makePayment(){
        System.out.println("-------------------------------------");
        String name = Console.promptForString("\nEnter the name of the recipient: ");
        double deposit = Console.promptForDouble("\nEnter payment amount: ");
        String description = Console.promptForString("\nEnter short description of the deposit: ");
        System.out.println("-------------------------------------");

        if (deposit > 0) {
            Transaction transaction = new Transaction(description, name, deposit);
            transactions.add(transaction);
            writeTransaction(transaction);
            balance = loadBalance(-deposit);
            System.out.printf("Payment recorded. New balance: $%.2f%n", balance);
        } else {
            System.out.println("Please enter an amount greater than $0.");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("account_balance.csv"))) {
                writer.write(String.format("$%.2f", balance));
                writer.newLine();
            } catch (IOException e) {
                System.out.println("There was an error writing.");
            }
        }
    }
}
