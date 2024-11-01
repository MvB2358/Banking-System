
public class Manager extends Employee {

    public Manager(String employeeId, String employeeName, Bank bank, Branch branch, 
                   String designation, String department, double salary, 
                   int phoneNumber, String email, Date joiningDate) {
        super(employeeId, employeeName, bank, branch, designation, department, salary, phoneNumber, email, joiningDate);
    }

    
    public void approveLoan(LoanApplication application) {
        if (application != null && !application.isApproved()) {
            application.setApproved(true);
            System.out.println("Loan application for " + application.getApplicantName() + " approved.");
        } else {
            System.out.println("Loan application is already approved or null.");
        }
    }


    public void generateEmployeeReport(Employee employee) {
        if (employee != null) {
            System.out.println("Generating report for Employee ID: " + employee.getEmployeeID());
            System.out.println("Name: " + employee.getEmployeeName());
            System.out.println("Designation: " + employee.getDesignation());
            System.out.println("Salary: " + employee.getSalary());
        } else {
            System.out.println("Invalid employee information.");
        }
    }


    public void provideBonus(Employee employee, double bonus) {
        if (employee != null && bonus > 0) {
            double updatedSalary = employee.getSalary() + bonus;
            employee.setSalary(updatedSalary);
            System.out.println("Bonus of " + bonus + " provided to Employee ID: " + employee.getEmployeeID());
        } else {
            System.out.println("Invalid employee or bonus amount.");
        }
    }
}
