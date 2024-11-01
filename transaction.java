
import java.time.LocalDateTime;

public class Transaction {
    private String transactionId;
    private Account sourceAccount;
    private Account destinationAccount;
    private String transactionType;
    private double amount;
    private LocalDateTime transactionDate;
    private String transactionStatus;
    private double transactionFee;
    private String failureReason;

    public Transaction(String transactionId, Account sourceAccount, Account destinationAccount,
                       String transactionType, double amount) {
        this.transactionId = transactionId;
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionDate = LocalDateTime.now();
        this.transactionStatus = "Pending";
        this.transactionFee = calculateTransactionFees();
    }

    public void getTransactionDetails() {
        System.out.println("Transaction ID: " + transactionId);
        System.out.println("Source Account: " + sourceAccount.getAccountId());
        System.out.println("Destination Account: " + destinationAccount.getAccountId());
        System.out.println("Transaction Type: " + transactionType);
        System.out.println("Amount: " + amount);
        System.out.println("Transaction Date: " + transactionDate);
        System.out.println("Transaction Status: " + transactionStatus);
        System.out.println("Transaction Fee: " + transactionFee);
        if (transactionStatus.equals("Failed")) {
            System.out.println("Failure Reason: " + failureReason);
        }
    }

    public double calculateTransactionFees() {
        double fee = 0;
        switch (transactionType) {
            case "Transfer":
                fee = amount * 0.02;
                break;
            case "Deposit":
                fee = 1.00;
                break;
            case "Withdrawal":
                fee = amount * 0.015;
                break;
            default:
                fee = 0;
                break;
        }
        return fee;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public Account getSourceAccount() {
        return sourceAccount;
    }

    public Account getDestinationAccount() {
        return destinationAccount;
    }

    public void setTransactionStatus(String status, String failureReason) {
        this.transactionStatus = status;
        this.failureReason = (status.equals("Failed")) ? failureReason : null;
    }
}
