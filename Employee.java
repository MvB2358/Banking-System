import java.util.Date;
import java.util.regex.*;

public class Employee {
    private String employeeId;
    private String employeeName;
    private String designation;
    private String department;
    private double salary;
    private int phoneNumber;
    private String email;
    private Date joiningDate;

    private Bank bank;
    private Branch branch;

    private int card_number = 1;

    public Employee(String employeeId, String employeeName, Bank bank, Branch branch, String designation,
            String department, double salary, int phoneNumber, String email, Date joiningDate) {
        this.employeeId = employeeId;
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

    public void editPhone(int phoneNumber) {
        this.phoneNumber = phoneNumber;
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
            System.out.println("Invalid salary. Salary must be a non-negative number.");
        }
    }

    public void verifyLoan(Loan loanApplication) {
        boolean setStatus = true;
        loanApplication.updateVerification(setStatus, this);
    }

    public void createCreditCard() {
        String creditCardNumber = String.format("%04d %04d %04d %04d", (card_number / 100000000) % 10000, (card_number / 10000) % 10000, (card_number / 100) % 10000, card_number % 10000);
        card_number++;
        System.out.println("Generated Credit Card Number: " + creditCardNumber);
    }

    public void openAccount(Customer customer, String type) {
        if (type.equals("Savings")) {
            // Savings account
        } else {
            // Current account
        }
        System.out.println(type + "Account opened for Customer: " + customer.getName());
    }

    public void closeAccount(Account account) {
        String current_status = account.getStatus();
        if (current_status.equals("Active")) {
            account.setStatus("Closed");
            System.out.println("Account closed for customer");
        } else {
            System.out.println("Account is already closed");
        }
    }
}