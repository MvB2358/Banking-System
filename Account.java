import java.util.ArrayList;

class Account {
    private Customer accountHolder;
    private int accountNumber;
    private double balance;
    private Branch branch;
    private ArrayList<FixedDeposit> fixedDeposits;
    private ArrayList<RecurringDeposit> recurringDeposits;
    private ArrayList<LoanAccount> loanAccounts;
    private ArrayList<Transaction> transactions;

    // Constructor
    public Account(Customer accountHolder, int accountNumber, Branch branch) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.balance = 0.0; // Initial balance
        this.branch = branch;
        this.fixedDeposits = new FixedDeposit[] ;
        this.recurringDeposits = new ReadableDeposit[] ;
        this.loanAccounts = new LoanAccount[];
        this.transactions = new Transactttions[];
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountDetails() {
        return "Account Number: " + accountNumber +
               "\nAccount Holder: " + accountHolder.getName() +
               "\nBalance: " + balance +
               "\nBranch: " + branch.getBranchName();
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public Bank getBank() {
        return branch.getBank(); 
    }

    public Branch getBranch() {
        return branch;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance && amount > 0) {
            balance -= amount;
            transactions.add(new Transaction("Withdrawal", amount));
            return true;
        }
        return false;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactions.add(new Transaction("Deposit", amount));
            return true;
        }
        return false;
    }

    public void closeFD(FixedDeposit FD) {
        if (fixedDeposits.remove(FD)) {
            // Additional logic to transfer funds or notify the customer
            System.out.println("Fixed Deposit closed.");
        }
    }

    public void prematureFD(FixedDeposit FD) {
        // Logic for handling premature closure of fixed deposits
        System.out.println("Fixed Deposit closed prematurely.");
    }

    public void closeRD(RecurringDeposit RD) {
        if (recurringDeposits.remove(RD)) {
            // Logic for closing recurring deposits
            System.out.println("Recurring Deposit closed.");
        }
    }

    public void prematureRD(RecurringDeposit RD) {
        // Logic for handling premature closure of recurring deposits
        System.out.println("Recurring Deposit closed prematurely.");
    }

    public void addFD(FixedDeposit FD) {
        fixedDeposits.add(FD);
        System.out.println("Fixed Deposit added.");
    }

    public void addRD(RecurringDeposit RD) {
        recurringDeposits.add(RD);
        System.out.println("Recurring Deposit added.");
    }

    public void depositRD(RecurringDeposit RD,int amount) {
        RD.balance = RD.balance + amount;
        System.out.println("Deposited Amount succesfully")
    }

    public void addLoanAccount(LoanAccount LA) {
        loanAccounts.add(LA);
        System.out.println("Loan Account added.");
    }

    public void removeLoanAccount(LoanAccount LA) {
        loanAccounts.remove(LA);
        System.out.println("Loan Account removed.");
    }

    public void repayLoan(LoanAccount LA) {
        System.out.println("Loan repaid.");
    }

    public Transaction[] getTransactions() {
        return transactions.toArray(new Transaction[0]);
    }

    public void handleTransaction(Transaction transaction) {
      
        if (transaction.getType().equals("Deposit")) {
            deposit(transaction.getAmount());
        } else if (transaction.getType().equals("Withdrawal")) {
            withdraw(transaction.getAmount());
        }
    }
}
