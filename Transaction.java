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
            case "Transfer":
                transactionFee = amount * 0.005;
                break;
            case "Deposit":
                transactionFee = 0.0;
                break;
            case "Withdraw":
                transactionFee = amount * 0.01;
                break;
            default:
                transactionFee = 0.0;
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

    public boolean processTransaction() {
        transactionFee = calculateTransactionFees();
        boolean result = false;

        if (sourceAccount.getBalance() >= (amount + transactionFee)) {//by transaction type and set boolean for source account
            sourceAccount.withdraw(amount + transactionFee);
            result = true;
            transactionStatus = "Withdrawn";

            if (destinationAccount != null) {
                destinationAccount.deposit(amount);
                transactionStatus = "Completed";
            } else {
                transactionStatus = "Failed";
                failureReason = "Destination Account Not Specified";
                result = false;
            }
        } else {
            transactionStatus = "Failed";
            failureReason = "Insufficient Funds";
        }

        return result;
    }
}
