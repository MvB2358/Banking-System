import java.time.LocalDate;

public class RecurringDeposit {

    private String recurringId;
    private double balance;
    private double interestRate;
    private int frequency;
    private double depositAmount;
    private LocalDate maturity;
    private String status;
    private double maturityAmount;

    public RecurringDeposit(String recurringId, double interestRate, int frequency, double depositAmount, LocalDate maturity) {
        this.recurringId = recurringId;
        this.interestRate = interestRate;
        this.frequency = frequency;
        this.depositAmount = depositAmount;
        this.maturity = maturity;
        this.balance = 0.0;
        this.status = "Active";
        this.maturityAmount = 0.0;
    }

    public void getRdDetails() {
        System.out.println("Recurring Deposit ID: " + recurringId);
        System.out.println("Balance: " + balance);
        System.out.println("Interest Rate: " + interestRate + "%");
        System.out.println("Frequency: " + frequency + " days");
        System.out.println("Deposit Amount: " + depositAmount);
        System.out.println("Maturity Date: " + maturity);
        System.out.println("Status: " + status);
        System.out.println("Maturity Amount: " + maturityAmount);
    }

    public void makeDeposit() {
        if (status.equals("Active")) {
            balance += depositAmount;
            System.out.println("Deposit of " + depositAmount + " made. New balance: " + balance);
        } else {
            System.out.println("Cannot make deposit to an inactive account");
        }
    }

    public void updateInterest(double newInterestRate) {
        this.interestRate = newInterestRate;
        System.out.println("Interest rate updated to: " + interestRate + "%");
    }

    public void updateFrequency(int newFrequency) {
        this.frequency = newFrequency;
        System.out.println("Deposit frequency updated to every " + frequency + " days.");
    }

    public void updateDeposit(double newDepositAmount) {
        this.depositAmount = newDepositAmount;
        System.out.println("Deposit amount updated to: " + depositAmount);
    }

    public double calculateTotalAmount() {
        int months = (int) maturity.until(LocalDate.now()).toTotalMonths();
        double totalAmount = balance * Math.pow(1 + (interestRate / 12 / 100), months);
        this.maturityAmount = totalAmount;
        System.out.println("Maturity amount calculated: " + maturityAmount);
        return maturityAmount;
    }

    public void closeAccount() {
        if (status.equals("Active")) {
            status = "Closed";
            System.out.println("Recurring Deposit account closed. Final balance: " + balance);
        } else {
            System.out.println("Account is already closed.");
        }
    }
}