package com.pluralsight;

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;

public class ProcessGeneral {
    public static ArrayList<Transaction> transactions = new ArrayList<>();

    /**
     * Method that uses a BufferedReader to read off every entry in transactions.csv and load them into the program to be used in the Ledger.
     */
    public static ArrayList<Transaction> loadTransaction() {
        transactions.clear();   // avoids duplicates if it's ever called twice

        try (BufferedReader reader = new BufferedReader(new FileReader(AccountingLedger.filenameTransactions))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] listing = line.replaceAll("\u001B\\[[0-9;]*m", "").split("\\|");
                if (listing.length < 5) continue;   // skip blank/bad lines

                try {
                    Transaction newListing = new Transaction(
                            listing[0].trim(),
                            listing[1].trim(),
                            listing[3].trim().replace("\"", ""),   // description
                            listing[2].trim(),                      // vendor
                            Double.parseDouble(listing[4].trim().replace(",", "").replace("$", "")));
                    transactions.add(newListing);
                } catch (Exception e) {
                    TextColors.menu("Skipped bad line: " + line + " (" + e.getMessage() + ")");
                }
            }
        } catch (IOException e) {
            TextColors.menu("No transactions found.");
        }
        return transactions;
    }

    /**
     * Method that uses a BufferedWriter to take constructed Transaction objects and enter them into the transactions.csv file.
     * BufferedReader will then take those values and do its aforementioned job.
     */
    public static void writeTransaction(Transaction newListing) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(AccountingLedger.filenameTransactions, true))) {
            writer.write(newListing.toString());
            writer.newLine();
        } catch (IOException e) {
            TextColors.menu("There was an error writing.");
        }
    }

    /**
     * Method that keeps track of account balance and updates it accordingly.
     */

    public static double balance = 0;

    public static void loadBalance() {
        File file = new File(AccountingLedger.filenameBalance);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while (line != null && line.isBlank()) {   // skip blank lines
                line = reader.readLine();
            }
            if (line != null) {
                // the file only ever holds a plain number, so a comma is a decimal comma
                balance = Double.parseDouble(line.trim().replace(',', '.'));
            }
        } catch (FileNotFoundException e) {
            balance = 0;
            saveBalance();   // create the file
        } catch (IOException | NumberFormatException e) {
            TextColors.menu("Balance could not be read from " + file.getAbsolutePath());
        }
    }

    /**
     * Method used in the Deposits menu. Method will ask the user deposit information.
     * Method then constructs Transaction object out of this information to send to writeTransaction().
     * Also, date and time are never prompted to the user; by default, formatted LocalDate and LocalTime assign them.
     * Lastly, account balance is updated and saved in a separate file.
     */
    public static void saveBalance() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(AccountingLedger.filenameBalance))) {
            writer.write(String.format(Locale.US, "%.2f", balance));
        } catch (IOException e) {
            TextColors.menu("Balance could not be saved.");
        }
    }

    public static void addDeposit() {

        TextColors.dash();
        String name = Console.promptForString("Enter the name of the payer: ");
        double deposit = Console.promptForDouble("Enter deposit amount: ");
        String description = Console.promptForString("Enter short description of the deposit: ");
        TextColors.dash();

        if (deposit > 0) {
            Transaction transaction = new Transaction(name, description, deposit);
            transactions.add(transaction);
            writeTransaction(transaction);
            balance += deposit;
            saveBalance();
        } else {
            TextColors.menu("Please enter an amount greater than $0.");
        }
    }

    public static void makePayment(String name, String description, double amount) {
        double payment = -Math.abs(amount);

        Transaction transaction = new Transaction(name, description, payment);
        transactions.add(transaction);
        writeTransaction(transaction);
        balance += payment;
        saveBalance();

        TextColors.printf(TextColors.BRIGHT_RED, "Payment recorded. New balance: $%,.2f%n", balance);
    }
}