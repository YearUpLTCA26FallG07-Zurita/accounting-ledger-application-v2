package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;

public class LedgerReport {

    public static void monthToDate() {
        YearMonth month = YearMonth.now();

        LocalDate startDate = month.atDay(1);
        LocalDate endDate = LocalDate.now();

        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
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
    }

        public static void previousMonth() {
            YearMonth previousMonth = YearMonth.now().minusMonths(1);

            LocalDate startDate = previousMonth.atDay(1);
            LocalDate endDate = previousMonth.atEndOfMonth();

            try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
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
        }

    public static void yearToDate() {
        Year year = Year.now();

        LocalDate startDate = year.atDay(1);
        LocalDate endDate = LocalDate.now();

        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
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
    }

    public static void previousYear() {
        Year currentYear = Year.now();

        LocalDate startDate = currentYear.atDay(1);
        LocalDate endDate = currentYear.atMonth(12).atEndOfMonth();

        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
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
    }

    public static void searchByVendor() {
        String searchVendor = Console.promptForString("Enter the name of the vendor: ");

        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
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
    }
}

