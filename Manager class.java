public class Manager extends Employee {

    public Manager(String employeeId, String employeeName, Bank bank, Branch branch, 
                   String designation, String department, double salary, 
                   int phoneNumber, String email, Date joiningDate) {
        super(employeeId, employeeName, bank, branch, designation, department, salary, phoneNumber, email, joiningDate);
    }

    public void approveLoan(LoanApplication application) {
        if (application != null && !application.getLoanStatus().equalsIgnoreCase("Approved")) {
            if (application.isVerified()) {
                application.updateLoanStatus("Approved");
                application.setVerifiedBy(this);
                System.out.println("Loan application for " + application.getCustomer().getCustomerName() + " approved by " + getEmployeeName());
            } else {
                application.updateLoanStatus("Rejected");
                System.out.println("Loan application for " + application.getCustomer().getCustomerName() + " rejected by " + getEmployeeName() + " as it is not verified.");
            }
        } else {
            System.out.println("Loan application is already approved or null.");
        }
    }

    public void generateEmployeeReport(Employee employee) {
        if (employee != null) {
            System.out.println("Employee Report for ID: " + employee.getEmployeeId());
            System.out.println("Name: " + employee.getEmployeeName());
            System.out.println("Designation: " + employee.getDesignation());
            System.out.println("Department: " + employee.getDepartment());
            System.out.println("Salary: " + employee.getSalary());
        } else {
            System.out.println("Invalid employee information.");
        }
    }

    public double getSalary() {
        return super.getSalary();
    }

    public void provideBonus(Employee employee, double bonus) {
        if (employee != null && bonus > 0) {
            double updatedSalary = employee.getSalary() + bonus;
            employee.updateSalary(updatedSalary);
            System.out.println("Bonus of " + bonus + " provided to Employee ID: " + employee.getEmployeeId());
        } else {
            System.out.println("Invalid employee or bonus amount.");
        }
    }
}
