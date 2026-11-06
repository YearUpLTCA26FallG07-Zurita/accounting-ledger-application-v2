# Accounting Ledger

A command-line Java application for tracking personal finances. Record deposits and payments, keep a running balance, and review your history through a color-coded ledger with date-based reports and a custom search. All data is saved to plain CSV files, so your records persist between sessions.

## Features

- **Deposits and payments**: record money in and out with a payer or recipient, an amount, and a short description. Date and time are stamped automatically.
- **Balance tracking**: a running balance is saved after every transaction and reloaded on startup.
- **Payment safeguards**: payments are checked against your balance, and each one goes through a confirm, revise, or cancel step before it is saved.
- **Ledger views**: show all entries, deposits only, or payments only.
- **Reports**: month to date, previous month, year to date, previous year, and search by vendor.
- **Custom search**: combine any of start date, end date, vendor, description, and price. Leave a filter blank to skip it.
- **Color-coded interface**: every kind of text has its own color, so menus, deposits, payments, and prompts are easy to tell apart.
- **Input validation**: invalid menu choices, numbers, and dates are rejected and re-prompted instead of crashing the app.

## Tech Stack

- Java 17 or newer (uses text blocks and switch expressions)
- No external dependencies
- File I/O with `BufferedReader` and `BufferedWriter`
- `java.time` for dates and times

## Getting Started

### Prerequisites

- JDK 17 or newer
- A terminal that supports ANSI colors, such as IntelliJ's Run window, Windows Terminal, PowerShell, or any macOS/Linux terminal. The legacy Windows `cmd.exe` shows raw color codes instead of colors.

### Run with IntelliJ IDEA

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/accounting-ledger.git
   ```
2. Open the project folder in IntelliJ IDEA.
3. Run `AccountingLedger.java`.

### Run from the command line

From the project root, compile and run (adjust the source path if your folder layout differs):

```bash
javac -d out src/com/pluralsight/*.java
java -cp out com.pluralsight.AccountingLedger
```

The data files `transactions.csv` and `account_balance.csv` are created in the folder you run the program from.

## Usage

The app opens on the main menu. Enter the letter for an option and press Enter. In every submenu, `X` goes back.

```
-------------------------------------
Main Menu

D) Add Deposit
P) Make A Payment
L) Access Ledger

X) Exit the Application
```

| Menu | Option | Action |
|---|---|---|
| Main | `D` | Add a deposit |
| Main | `P` | Make a payment |
| Main | `L` | Open the ledger |
| Ledger | `A` / `D` / `P` | View all entries, deposits only, or payments only |
| Ledger | `R` | Open the reports menu |
| Reports | `1` to `4` | Month to date, previous month, year to date, previous year |
| Reports | `5` | Search by vendor |
| Reports | `6` | Custom search with combined filters |

### Color Guide

| Element | Color |
|---|---|
| Dashed dividers | Bright blue |
| Menu text | Bright cyan |
| Exit and back options | Red |
| Deposits | Bright green |
| Payments | Bright red |
| Input prompts | Bright yellow |
| Transaction rows | White |

## Project Structure

```
src/com/pluralsight/
├── AccountingLedger.java   Entry point: loads saved data, starts the menu
├── Menu.java               Menu flow and user navigation
├── MenuDialogue.java       Text for each menu screen
├── ProcessGeneral.java     Loading, saving, deposits, payments, and balance
├── ProcessLedger.java      Reports and custom search
├── Transaction.java        Transaction model and display formatting
├── Console.java            Scanner-based input prompts
└── TextColors.java         ANSI color constants and print helpers
```

## Data Files

**`transactions.csv`** stores one transaction per line, separated by pipes:

```
2026-11-05   | 11:33 AM     | Acme Corp            | "Monthly Salary"       |  $2,500.00
2026-11-03   | 01:40 PM     | Best Buy             | "Electronics"          | -$129.99
```

Fields are date, time, vendor, description, and amount. Deposits are positive and payments are negative. The column spacing and the green and red amounts are produced by `Transaction.toString()`, so the color codes are stored in the file and stripped again when it is read back.

**`account_balance.csv`** holds a single number, the current balance, rewritten after every transaction.

To start fresh, delete both files.

## Known Limitations

- Times use the `hh:mm a` format (for example `11:33 AM`) and expect an English locale.
- Amounts are stored as `double`, which is fine for a learning project but not for real accounting.
- The ledger and report views read lines directly from the file, so a manually edited file with a malformed line can cause a read error.

## Possible Improvements

- Use `BigDecimal` for money
- Category tags and monthly summaries
- Edit and delete transactions
- Unit tests for filtering and parsing

## Author

**Your Name**
[GitHub](https://github.com/your-username)
