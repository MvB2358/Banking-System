import java.util.ArrayList;
import java.util.Date;

class CreditCard extends Card {
    private double withdrawLimit;
    private double interest;
    private double amountWithdrawn;
    private ArrayList<Transaction> transactions;

    // Constructor
    public CreditCard(int cardNumber, String name, int cvv, Date expiryDate, Account account, double withdrawLimit, double interest) {
        super(cardNumber, name, cvv, expiryDate, account); 
        this.withdrawLimit = withdrawLimit;
        this.interest = interest;
        this.amountWithdrawn = 0.0;
        this.transactions = new ArrayList<>();
    }

    // Override withdraw method to check against credit limits
    @Override
    public void withdraw(double amount) {
        if (getStatus().equals("Active")) {
            if (amountWithdrawn + amount <= withdrawLimit) {
                amountWithdrawn += amount;
                transactions.add(new Transaction("Credit Withdrawal", amount));
                System.out.println("Withdrawn: " + amount + ". Total withdrawn: " + amountWithdrawn);
            } else {
                System.out.println("Withdrawal denied. Exceeds credit limit.");
            }
        } else {
            System.out.println("Card is blocked. Cannot withdraw.");
        }
    }


    public void payCredit(double amount, int days) {
        double interestAmount = amount * (interest / 100) * (days / 365.0); // Simple interest calculation
        double totalPayment = amount + interestAmount;
        amountWithdrawn -= amount; // Reduce the outstanding credit balance
        transactions.add(new Transaction("Credit Payment", totalPayment));
        System.out.println("Credit of " + amount + " paid with interest of " + interestAmount + 
                           ". Total payment: " + totalPayment);
    }

  
    @Override
    public String getCardDetails() {
        return super.getCardDetails() + 
               "\nWithdraw Limit: " + withdrawLimit +
               "\nInterest Rate: " + interest +
               "\nTotal Amount Withdrawn: " + amountWithdrawn;
    }

    // Getter for transactions history
    public Transaction[] getTransactions() {
        return transactions.toArray(new Transaction[0]);
    }

    // Additional getters if needed
    public double getWithdrawLimit() {
        return withdrawLimit;
    }

    public double getInterest() {
        return interest;
    }

    public double getAmountWithdrawn() {
        return amountWithdrawn;
    }
}
