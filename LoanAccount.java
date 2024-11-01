import java.time.LocalDate;

public class LoanAccount {

    private String loanId;
    private Customer customer;
    private double loanAmount;
    private double interestRate;
    private int loanTerm;
    private double balance;
    private double installment;
    private LocalDate loanStart;
    private String status;
    private double lateFee;

    public LoanAccount(String loanId, Customer customer, double loanAmount, double interestRate, int loanTerm, LocalDate loanStart) {
        this.loanId = loanId;
        this.customer = customer;
        this.loanAmount = loanAmount;
        this.interestRate = interestRate;
        this.loanTerm = loanTerm;
        this.loanStart = loanStart;
        this.balance = loanAmount;
        this.status = "Active";
        this.lateFee = 0.5;
        this.installment = calculateInstallment();
    }

    private double calculateInstallment() {
        double monthlyInterestRate = interestRate / 12 / 100;
        double installmentAmount = loanAmount * monthlyInterestRate / (1 - Math.pow(1 + monthlyInterestRate, -loanTerm))
        return installmentAmount;
    }

    public void getLoanDetails() {
        System.out.println("Loan ID: " + loanId);
        System.out.println("Customer: " + customer);
        System.out.println("Loan Amount: " + loanAmount);
        System.out.println("Interest Rate: " + interestRate);
        System.out.println("Loan Term (months): " + loanTerm);
        System.out.println("Balance: " + balance);
        System.out.println("Installment: " + installment);
        System.out.println("Loan Start Date: " + loanStart);
        System.out.println("Status: " + status);
        System.out.println("Late Fee: " + lateFee);
    }

    public void makePayment(Double amount) {
        if (amount > balance) {
            System.out.println("Amount Not Enough to make Payment");
        } else {
            balance = balance - amount;
            System.out.println("Payment of " + amount + " made. Remaining balance: " + balance);
        }
    }

    public void closeLoan() {
        if (balance == 0) {
            status = "Closed";
            System.out.println("Loan has been closed");
        } else {
            System.out.println("Remaining amount " + balance + " still needs to be paid");
        }
    }

    public void calculateEnd() {
        LocalDate endDate = loanStart.plusMonths(loanTerm);
        System.out.println("Loan end date: " + endDate);
    }

    public void updateInterest(double newInterestRate) {
        this.interestRate = newInterestRate;
        this.installment = calculateInstallment();
        System.out.println("Interest rate updated to " + interestRate + "%. New installment: " + installment);
    }

    public void applyDelay() {
        balance += lateFee;
        System.out.println("Delay fee of " + lateFee + " applied. New amount to be paid: " + balance);
    }

    public void updateLateFee(double newLateFee) {
        this.lateFee = newLateFee;
        System.out.println("Late fee updated to: " + lateFee);
    }

    public void increaseLoan(double additionalAmount) {
        if ("Active".equals(status)) {
            this.loanAmount += additionalAmount;
            this.balance += additionalAmount;
            this.installment = calculateInstallment();
            System.out.println("Loan increased by " + additionalAmount + ". New balance: " + balance);
        } else {
            System.out.println("Cannot increase loan on closed or inactive accounts.");
        }
    }

    public void updateInstallment() {
        this.installment = calculateInstallment();
        System.out.println("Installment recalculated: " + installment);
    }

    public void updateTerm(int newLoanTerm) {
        this.loanTerm = newLoanTerm;
        this.installment = calculateInstallment();
        System.out.println("Loan term updated to " + loanTerm + " months. New installment: " + installment);
    }
}