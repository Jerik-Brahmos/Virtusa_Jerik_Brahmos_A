/* Java Project
Problem Statement: Banking System Simulation
Banks require secure and efficient systems to manage customer transactions. Build a Java-based banking simulation.
Objectives
- Simulate core banking operations
- Handle user accounts and transactions
Key Features
- Create account (Savings/Current)
- Deposit, withdraw
- Balance inquiry
- Transaction history
- Basic authentication (username/password)
*/

import java.util.*;
class Account {
    private String username;
    private String password;
    private String accountType;
    private double balance;
    private List<String> transactions;

    public Account(String username,String password,String accountType) {
        this.username = username;
        this.password = password;
        this.accountType = accountType;
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
    }

    public boolean checkUsername(String user) {
        return username.equals(user);
    }

    public boolean checkPassword(String pass) {
        return password.equals(pass);
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }
        balance += amount;
        transactions.add("Deposited: Rs." + amount);
        System.out.println("Amount deposited successfully.");
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            balance -= amount;
            transactions.add("Withdrawn: Rs." + amount);
            System.out.println("Amount withdrawn successfully.");
        }
    }

    public void checkBalance() {
        System.out.println("Current Balance: Rs." + balance);
    }

    public void showTransactions() {
        System.out.println("\n--- Transaction History ---");
        if (transactions.isEmpty()) {
            System.out.println("No transactions.");
        } else {
            for (String t : transactions) {
                System.out.println(t);
            }
        }
    }
}

public class BankingSystem {

    public static boolean isValidPassword(String password) {
        boolean hasUpper = false;
        boolean hasDigit = false;

        if (password.length() < 8) {
            System.out.println("Password too short (min 8 chars)");
            return false;
        }

        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (Character.isUpperCase(ch)) hasUpper = true;
            if (Character.isDigit(ch)) hasDigit = true;
        }

        if (!hasUpper) {
            System.out.println("Missing uppercase letter");
        }
        if (!hasDigit) {
            System.out.println("Missing digit");
        }
        return hasUpper && hasDigit;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Banking System");
        System.out.print("Create Username: ");
        String username = sc.nextLine();
        String password;
        while (true) {
            System.out.print("Create Password: ");
            password = sc.nextLine();
            if (isValidPassword(password)) break;
            else System.out.println("Try again.\n");
        }
        System.out.print("Enter Account Type (Savings/Current): ");
        String type = sc.nextLine();
        System.out.println("Account created successfully.");

        Account acc = new Account(username, password, type);

        System.out.println("\n--- Login ---");
        while (true) {
            System.out.print("Enter Username: ");
            String u = sc.nextLine();
            System.out.print("Enter Password: ");
            String p = sc.nextLine();
            if (!acc.checkUsername(u)) {
                System.out.println("Username not found.\n");
            } else if (!acc.checkPassword(p)) {
                System.out.println("Incorrect password.\n");
            } else {
                System.out.println("Login Successful!!!");
                break;
            }
        }

        while (true) {
            System.out.println("\n--- Menu ---");
            System.out.println("1.Deposit");
            System.out.println("2.Withdraw");
            System.out.println("3.Check Balance");
            System.out.println("4.Transaction History");
            System.out.println("5.Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter amount to deposit: ");
                    double dep = sc.nextDouble();
                    acc.deposit(dep);
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double wit = sc.nextDouble();
                    acc.withdraw(wit);
                    break;
                case 3:
                    acc.checkBalance();
                    break;
                case 4:
                    acc.showTransactions();
                    break;
                case 5:
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}