public class SavingsAccount extends Account {
    private static double interestRate = 5.0; // Default interest rate
    private double minBalance;
    private double transactionLimit;
    private int maxNoTransactions;
    private double charge;
    private boolean isViolating;

    public SavingsAccount(double minBalance, double transactionLimit, int maxNoTransactions) {
        super(); // Call to Account's constructor if required
        this.minBalance = minBalance;
        this.transactionLimit = transactionLimit;
        this.maxNoTransactions = maxNoTransactions;
        this.charge = 0.0;
        this.isViolating = false;
    }

    public double calculateInterest() {
        // Assuming interest is calculated on the current balance
        return balance * (interestRate / 100);
    }

    public void withdraw(double amount){
        System.out.println("You have requested to withdraw: " + amount);
        if (balance - amount >= minBalance) {
            balance -= amount;
        }
        else{
            System.out.println("Insufficient balance to meet minimum balance requirements.");
        }
    }

    public void deposit(double amount){
        System.out.println("You have deposited: " + amount);
        balance += amount;
    }

    public double getBalance(){
        return balance;
    }

    public void handleTransaction(double amount){
        // Example logic for transaction limits
        if(amount > transactionLimit){
            System.out.println("Transaction limit exceeded.");
            isViolating = true;
        }
        else{
            System.out.println("Transaction successful.");
        }
    }

    public void imposeFine(){
        charge = 50.0; // Example fine amount
        balance -= charge;
        System.out.println("Fine imposed: " + charge);
    }
}
