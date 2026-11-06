package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;

public class LedgerReport {

    public static void monthToDate() {
        YearMonth month = YearMonth.now();

        LocalDate startDate = month.atDay(1);
        LocalDate endDate = LocalDate.now();
//        for(Transaction t : Process.transactions){
//
//        }
        try (BufferedReader reader = new BufferedReader(new FileReader(AccountingLedger.filenameTransactions))) {
            String line;
            System.out.println("------------------------------------------------------------------------------");
            while ((line = reader.readLine()) != null) {
                String[] listing = line.split("\\|");

                LocalDate transactionDate = LocalDate.parse(listing[0].trim());

                if (!transactionDate.isBefore(startDate) && !transactionDate.isAfter(endDate)) {
                    System.out.println(line);
                }
            }
            System.out.println("------------------------------------------------------------------------------");
            Thread.sleep(3000);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        MenuDialogue.textReport();
    }

    public static void previousMonth() {
        YearMonth previousMonth = YearMonth.now().minusMonths(1);

        LocalDate startDate = previousMonth.atDay(1);
        LocalDate endDate = previousMonth.atEndOfMonth();

        try (BufferedReader reader = new BufferedReader(new FileReader(AccountingLedger.filenameTransactions))) {
            String line;
            System.out.println("------------------------------------------------------------------------------");
            while ((line = reader.readLine()) != null) {
                String[] listing = line.split("\\|");

                LocalDate transactionDate = LocalDate.parse(listing[0].trim());

                if (!transactionDate.isBefore(startDate) && !transactionDate.isAfter(endDate)) {
                    System.out.println(line);
                }
            }
            System.out.println("------------------------------------------------------------------------------");
            Thread.sleep(3000);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        MenuDialogue.textReport();
    }

    public static void yearToDate() {
        Year year = Year.now();

        LocalDate startDate = year.atDay(1);
        LocalDate endDate = LocalDate.now();

        try (BufferedReader reader = new BufferedReader(new FileReader(AccountingLedger.filenameTransactions))) {
            String line;
            System.out.println("------------------------------------------------------------------------------");
            while ((line = reader.readLine()) != null) {
                String[] listing = line.split("\\|");

                LocalDate transactionDate = LocalDate.parse(listing[0].trim());

                if (!transactionDate.isBefore(startDate) && !transactionDate.isAfter(endDate)) {
                    System.out.println(line);
                }
            }
            System.out.println("------------------------------------------------------------------------------");
            Thread.sleep(3000);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        MenuDialogue.textReport();
    }

    public static void previousYear() {
        Year currentYear = Year.now();

        LocalDate startDate = currentYear.atDay(1);
        LocalDate endDate = currentYear.atMonth(12).atEndOfMonth();

        try (BufferedReader reader = new BufferedReader(new FileReader(AccountingLedger.filenameTransactions))) {
            String line;
            System.out.println("------------------------------------------------------------------------------");
            while ((line = reader.readLine()) != null) {
                String[] listing = line.split("\\|");

                LocalDate transactionDate = LocalDate.parse(listing[0].trim());

                if (!transactionDate.isBefore(startDate) && !transactionDate.isAfter(endDate)) {
                    System.out.println(line);
                }
            }
            System.out.println("------------------------------------------------------------------------------");
            Thread.sleep(3000);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        MenuDialogue.textReport();
    }

    public static void searchByVendor() {
        String searchVendor = Console.promptForString("Enter the name of the vendor: ");

        try (BufferedReader reader = new BufferedReader(new FileReader(AccountingLedger.filenameTransactions))) {
            String line;
            System.out.println("------------------------------------------------------------------------------");
            while ((line = reader.readLine()) != null) {
                String[] listing = line.split("\\|");

                String vendor = listing[2].trim();

                if (searchVendor.equalsIgnoreCase(vendor)) {
                    System.out.println(line);
                }
            }
            System.out.println("------------------------------------------------------------------------------");
            Thread.sleep(3000);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        MenuDialogue.textReport();
    }

    public static void customSearch() {

        System.out.println("""
                Enter the following filter info.
                If a filter doesn't apply, press Enter.
                """);

        while (true) {
            System.out.println("------------------------------------------------------------------------------");
            String rangeStart = Console.promptForString("Start date (yyyy-MM-dd): ").trim();
            String rangeEnd = Console.promptForString("End date (yyyy-MM-dd): ").trim();
            String vendor = Console.promptForString("Vendor/Company: ").trim();
            String description = Console.promptForString("Transaction description: ").trim();
            String amount = Console.promptForString("Transaction price: ").trim().replace(",", "").replace("$", "");
            System.out.println("------------------------------------------------------------------------------");

            LocalDate startDate = null;
            LocalDate endDate = null;
            double price = 0;

            try {
                if (!rangeStart.isEmpty()) startDate = LocalDate.parse(rangeStart);
                if (!rangeEnd.isEmpty()) endDate = LocalDate.parse(rangeEnd);
                if (!amount.isEmpty()) price = Double.parseDouble(amount);
                ArrayList<Transaction> finalListing = new ArrayList<>();

                for (Transaction t : Process.transactions) {
                    if (startDate != null && t.getDate().isBefore(startDate)) {
                        continue;
                    }
                    if (endDate != null && t.getDate().isAfter(endDate)) {
                        continue;
                    }
                    if (!description.isEmpty() && !t.getDescription().equalsIgnoreCase(description)) {
                        continue;
                    }
                    if (!vendor.isEmpty() && !t.getVendor().equalsIgnoreCase(vendor)) {
                        continue;
                    }
                    if (price != 0 && Math.abs(t.getAmount()) != Math.abs(price)) {
                        continue;
                    }
                    finalListing.add(t);
                }
                System.out.println("------------------------------------------------------------------------------");
                if (finalListing.isEmpty()) {
                    System.out.println("No transactions found. Enter filters.");
                } else {
                    for (Transaction t : finalListing) {
                        System.out.println(t);
                    }
                }
                System.out.println("------------------------------------------------------------------------------");
                Thread.sleep(3000);
                MenuDialogue.textReport();
                return;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}