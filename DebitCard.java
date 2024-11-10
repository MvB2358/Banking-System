import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

class DebitCard extends Card {
    private Account account;
    private ArrayList<Transaction> transactions;

    public DebitCard(String cardNumber, String name, int cvv, Date expiryDate, Account account) {
        super(cardNumber, name, cvv, expiryDate);
        this.account = account;
        this.transactions = new ArrayList<>();
    }

    @Override
    public void withdraw(double amount) {
        if (getStatus().equals("Active")) {
            if (amount > 0 && amount <= account.getBalance()) {
                account.withdraw(amount);
                transactions.add(new Transaction(account, null, "Debit Withdrawal"));
                System.out.println("Withdrawn: " + amount);
            } else {
                System.out.println("Insufficient balance for withdrawal.");
            }
        } else {
            System.out.println("Card is blocked. Cannot withdraw.");
        }
    }

    @Override
    public void deposit(double amount) {
        if (getStatus().equals("Active")) {
            if (amount > 0) {
                account.deposit(amount);
                transactions.add(new Transaction(null, account, "Debit Deposit", amount));
                System.out.println("Deposited: " + amount);
            } else {
                System.out.println("Deposit amount must be positive.");
            }
        } else {
            System.out.println("Card is blocked. Cannot deposit.");
        }
    }

    public ArrayList<Transaction> getTransactions() {
        return new ArrayList<>(transactions);
    }

    @Override
    public String getCardDetails() {
        return super.getCardDetails() + 
               "\nLinked Account Balance: " + account.getBalance();
    }
}
