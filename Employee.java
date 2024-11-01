import java.util.Date;

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
        this.email = email;
    }

    public void updateSalary(double salary) {
        this.salary = salary;
    }

    public void verifyLoan(Loan loanApplication) {
        boolean setStatus = true;
        loanApplication.updateVerification(setStatus, this);
    }

    public void createCreditCard() {
        String creditCardNumber = "4131 1234 5674 1579";
    }

    public void openAccount(Customer customer, String type) {
        Account newAccount = new Account(customer, type);
        System.out.println("Account opened for Customer: " + customer.getName());
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