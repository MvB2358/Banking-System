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
    }

    public double calculateInterest() {
        // Assuming interest is calculated on the current balance
        return balance * (interestRate / 100);
    }

    public boolean withdraw(double amount){
        // create and include a boolean variable
        System.out.println("You have requested to withdraw: " + amount);
        if (balance - amount >= minBalance) {
            balance -= amount;
        }
        else{
            System.out.println("Insufficient balance to meet minimum balance requirements.");
        }
    }

    public boolean deposit(double amount){
        // create and include a boolean variable
        System.out.println("You have deposited: " + amount);
        balance += amount;
    }

    public double getBalance(){
        return balance;
    }

    public void handleTransaction(double amount){
        // Include CurrentTransactions to check less than limit and do super.transaction()
        if(amount > transactionLimit){
            System.out.println("Transaction limit exceeded.");
            isViolating = true;
        }
        
            System.out.println("Transaction successful.");   
        
    }

    public void imposeFine(){
        charge = 50.0; // Example fine amount
        balance -= charge;
        System.out.println("Fine imposed: " + charge);
    }
}
