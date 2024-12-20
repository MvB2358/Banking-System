import java.util.Calendar;
import java.util.Date;
import java.util.regex.*;
import java.util.Scanner;
public class Employee {
    private static long nextID = 1;
    private String employeeId;
    private String employeeName;
    private String designation;
    private String department;
    private double salary;
    private long phoneNumber;
    private String email;
    private Date joiningDate;

    private Bank bank;
    private Branch branch;

    private int card_number = 1;

    public Employee(String employeeName, Bank bank, Branch branch, String designation,
            String department, double salary, long phoneNumber, String email, Date joiningDate) {
        this.employeeId = "E" + String.format("%09d", nextID++);
        this.employeeName = employeeName;
        this.bank = bank;
        this.branch = branch;
        this.designation = designation;
        this.department = department;
        this.salary = salary;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.joiningDate = joiningDate;
    }

    public void getEmployeeDetails() {
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Name: " + employeeName);
        System.out.println("Designation: " + designation);
        System.out.println("Department: " + department);
        System.out.println("Salary: " + salary);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Email: " + email);
        System.out.println("Joining Date: " + joiningDate);
    }

    public String getEmployeeId() {
        return this.employeeId;
    }

    public String getEmployeeName() {
        return this.employeeName;
    }

    public String getDesignation() {
        return this.designation;
    }

    public String getDepartment() {
        return this.department;
    }

    public double getSalary() {
        return this.salary;
    }

    public long getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getEmail() {
        return this.email;
    }

    public Date getJoiningDate() {
        return this.joiningDate;
    }

    public Bank getBank() {
        return this.bank;
    }

    public Branch getBranch() {
        return this.branch;
    }

    public void editPhone(long phoneNumber) {
        if (String.valueOf(phoneNumber).matches("\\d{10}")) {
            this.phoneNumber = phoneNumber;
            System.out.println("The phone number has been updated to " + phoneNumber);
        } else {
            System.out.println("Enter a valid phone number");
        }
    }

    public void editEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        
        if (email != null && !email.isEmpty()) {
            if(email.matches(emailRegex)) {
                this.email = email;
                System.out.println("Email updated successfully!");
            } else {
                System.out.println("Enter a valid email address");
            }
        } else {
            System.out.println("Email was not updated. Please try again");
        }
    }

    public void updateSalary(double salary) {
        if (Double.isFinite(salary) && salary >= 0) {
            this.salary = salary;
            System.out.println("Salary updated successfully!");
        } else {
            System.out.println("Enter a valid salary");
        }
    }

    public void updateDesignation(String newDesignation) {
        this.designation = newDesignation;
        System.out.println("Designation updated to " + newDesignation + " for employee " + this.employeeName);
    }

    public void updateDepartment(String newDepartment) {
        this.department = newDepartment;
        System.out.println("Department updated to " + newDepartment + " for employee " + this.employeeName);
    }

    public void updateBranch(Branch branch) {
        this.branch = branch;
    }

    public void verifyLoan(Loan loanApplication) {
        loanApplication.getLoanDetails();
        Scanner s = new Scanner(System.in);
        System.out.println("Do you want to verify the Loan Application?\nPress 1 for yes\tPress 0 for no");
        int input = s.nextInt();
        boolean setStatus;
        if(i == 1) setStatus = true;
        else if (i == 0) setStatus = false;
        else System.out.println("Invalid input. Please Try again.");
        loanApplication.updateVerification(setStatus, this);
    }

    public void createCreditCard(Account account, String name) {
        String cardNumber = String.format("%04d %04d %04d %04d", (this.card_number / 100000000) % 10000, (this.card_number / 10000) % 10000, (this.card_number / 100) % 10000, this.card_number % 10000);
        this.card_number++;

        String holder_name = name.toUpperCase();
        int cvv = 333;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 4);
        Date expiryDate = calendar.getTime();
        double withdrawLimit = 50000.50;
        double interest = 2;

        CreditCard card = new CreditCard(cardNumber, holder_name, cvv, expiryDate, account, withdrawLimit, interest);
        System.out.println("Generate Credit card number " + cardNumber + " for account " + account.getAccountNumber());
    }

    public void openAccount(Customer customer, String type, Branch branch, double amount, double salary) {
        if (type.equals("Savings")) {
            double minBalance = 1000;
            double transactionLimit = 50000.00;
            int maxNoTransactions = 150;

            SavingsAccount savingsAccount = new SavingsAccount(minBalance, transactionLimit, maxNoTransactions);
            System.out.println(type + " Account opened for Customer: " + customer.getName() + " with account number " + savingsAccount.getAccountNumber());
        } else {
            CurrentAccount currentAccount = new CurrentAccount(customer, branch, amount, salary);
            System.out.println(type + " Account opened for Customer: " + customer.getName());
        }
    }

    public void closeAccount(Account account) {
        String current_status = account.getStatus();
        if (current_status.equals("Active")) {
            account.setStatus("Closed");
            System.out.println("Account " + account.getAccountNumber() + " has been closed");
        } else {
            System.out.println("Account " + account.getAccountNumber() + " is already closed");
        }
    }
}
