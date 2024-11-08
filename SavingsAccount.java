public class SavingsAccount extends Account {
    private static double interestRate = 5.0; // Default interest rate
    private double minBalance;
    private double transactionLimit;
    private int maxNoTransactions;
    private double charge;
    private boolean isViolating;
    private int currentTransactions;

    public SavingsAccount(double minBalance, double transactionLimit, int maxNoTransactions) {
        super(); // Call to Account's constructor if required
        this.minBalance = minBalance;
        this.transactionLimit = transactionLimit;
        this.maxNoTransactions = maxNoTransactions;
        this.charge = 0.0;
        this.isViolating = false;
        this.currentTransactions = 0;
    }

    public double calculateInterest() {
        // Assuming interest is calculated on the current balance
        return balance * (interestRate / 100);
    }

    public boolean withdraw(double amount) {
        boolean success = false; // Track if withdrawal is successful
        System.out.println("You have requested to withdraw: " + amount);
        if (balance - amount >= minBalance) {
            balance -= amount;
            success = true;
            System.out.println("Withdrawal successful. New balance: " + balance);
        } else {
            System.out.println("Insufficient balance to meet minimum balance requirements.");
        }
        return success;
    }

    public boolean deposit(double amount) {
        boolean success = false; // Track if deposit is successful
        System.out.println("You have deposited: " + amount);
        if (amount > 0) {
            balance += amount;
            success = true;
            System.out.println("Deposit successful. New balance: " + balance);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
        return success;
    }

    public double getBalance() {
        return balance;
    }

    public void handleTransaction(double amount) {
        if (amount > transactionLimit) {
            System.out.println("Transaction limit exceeded. Transaction denied.");
            isViolating = true;
        } else if (currentTransactions >= maxNoTransactions) {
            System.out.println("Maximum number of transactions exceeded. Transaction denied.");
            isViolating = true;
        } else {
            currentTransactions++;
            balance -= amount;  // Assuming transaction involves a deduction (e.g., purchase or withdrawal)
            System.out.println("Transaction successful. New balance: " + balance);
            isViolating = false;
        }
    }

    public void imposeFine() {
        charge = 50.0; // Example fine amount
        if (isViolating) {
            balance -= charge;
            System.out.println("Fine imposed: " + charge);
        } else {
            System.out.println("No fine imposed as there was no violation.");
        }
    }
}
