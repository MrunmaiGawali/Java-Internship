import java.util.*;

public class taskFive {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Bank Menu ---");
            System.out.println("1. Create account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Show account statement");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");
            String opt = scanner.nextLine();

            switch (opt) {
                case "1":
                    System.out.print("Enter account ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter account holder name: ");
                    String name = scanner.nextLine();
                    bank.createAccount(id, name);
                    break;
                case "2":
                    System.out.print("Enter account ID: ");
                    String depId = scanner.nextLine();
                    System.out.print("Enter amount to deposit: ");
                    double depAmt = readDouble(scanner);
                    bank.depositToAccount(depId, depAmt);
                    break;
                case "3":
                    System.out.print("Enter account ID: ");
                    String witId = scanner.nextLine();
                    System.out.print("Enter amount to withdraw: ");
                    double witAmt = readDouble(scanner);
                    bank.withdrawFromAccount(witId, witAmt);
                    break;
                case "4":
                    System.out.print("Enter account ID: ");
                    String stmId = scanner.nextLine();
                    bank.printAccountDetails(stmId);
                    break;
                case "5":
                    System.out.println("Thanks for using. Goodbye.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static double readDouble(Scanner scanner) {
        double x = 0;
        try {
            String s = scanner.nextLine();
            x = Double.parseDouble(s);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format, using 0.");
        }
        return x;
    }
}

class Account {
    private String accountId;
    private String accountHolder;
    private double balance;
    private List<String> transactions;

    public Account(String accountId, String accountHolder) {
        this.accountId = accountId;
        this.accountHolder = accountHolder;
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
        transactions.add(String.format("Account created for %s (ID: %s) with balance %.2f",
                                       accountHolder, accountId, balance));
    }

    public String getAccountId() {
        return accountId;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
            transactions.add(String.format("Failed deposit attempt: %.2f", amount));
            return;
        }
        balance += amount;
        String rec = String.format("Deposited: %.2f; New Balance: %.2f", amount, balance);
        transactions.add(rec);
        System.out.println("Deposit successful.");
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            transactions.add(String.format("Failed withdrawal attempt: %.2f", amount));
            return;
        }
        if (amount > balance) {
            System.out.println("Insufficient funds for withdrawal of " + amount);
            transactions.add(String.format("Failed withdrawal: %.2f; Balance: %.2f", amount, balance));
        } else {
            balance -= amount;
            String rec = String.format("Withdrew: %.2f; New Balance: %.2f", amount, balance);
            transactions.add(rec);
            System.out.println("Withdrawal successful.");
        }
    }

    public void printStatement() {
        System.out.println("Statement for Account ID: " + accountId + " (" + accountHolder + ")");
        System.out.println("Current Balance: " + String.format("%.2f", balance));
        System.out.println("Transactions:");
        for (String t : transactions) {
            System.out.println("  " + t);
        }
    }
}

class Bank {
    private Map<String, Account> accounts;

    public Bank() {
        this.accounts = new HashMap<>();
    }

    public void createAccount(String id, String name) {
        if (accounts.containsKey(id)) {
            System.out.println("Account with ID " + id + " already exists.");
        } else {
            Account acc = new Account(id, name);
            accounts.put(id, acc);
            System.out.println("Account created with ID: " + id);
        }
    }

    public void depositToAccount(String id, double amount) {
        Account acc = accounts.get(id);
        if (acc != null) {
            acc.deposit(amount);
        } else {
            System.out.println("Account ID " + id + " not found.");
        }
    }

    public void withdrawFromAccount(String id, double amount) {
        Account acc = accounts.get(id);
        if (acc != null) {
            acc.withdraw(amount);
        } else {
            System.out.println("Account ID " + id + " not found.");
        }
    }

    public void printAccountDetails(String id) {
        Account acc = accounts.get(id);
        if (acc != null) {
            acc.printStatement();
        } else {
            System.out.println("Account ID " + id + " not found.");
        }
    }
}
