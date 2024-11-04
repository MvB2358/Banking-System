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

    public Transaction(String transactionId, Account sourceAccount, Account destinationAccount, String transactionType, double amount, LocalDateTime transactionDate) {
        this.transactionId = transactionId;
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.transactionStatus = "Pending";
    }

    public void getTransactionDetails() {
        System.out.println("Transaction ID: " + transactionId);
        System.out.println("Source Account: " + sourceAccount.getAccountId());
        System.out.println("Destination Account: " + destinationAccount.getAccountId());
        System.out.println("Type: " + transactionType);
        System.out.println("Amount: " + amount);
        System.out.println("Date: " + transactionDate);
        System.out.println("Status: " + transactionStatus);
        System.out.println("Fee: " + transactionFee);
        System.out.println("Failure Reason: " + (failureReason != null ? failureReason : "None"));
    }

    public double calculateTransactionFees() {
        switch (transactionType) {
            case "Domestic":
                transactionFee = amount * 0.01; // 1% fee for domestic transactions
                break;
            case "International":
                transactionFee = amount * 0.02; // 2% fee for international transactions
                break;
            case "Business":
                transactionFee = amount * 0.015; // 1.5% fee for business transactions
                break;
            default:
                transactionFee = amount * 0.005; 
                break;
        }
        return transactionFee;
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

    public void processTransaction() {
        transactionFee = calculateTransactionFees();
        if (sourceAccount.getBalance() >= (amount + transactionFee)) {
            sourceAccount.debit(amount + transactionFee);
            destinationAccount.credit(amount);
            transactionStatus = "Completed";
        } else {
            transactionStatus = "Failed";
            failureReason = "Insufficient Funds";
        }
    }
}
