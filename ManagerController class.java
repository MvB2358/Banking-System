import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerController {

    private Branch branch;  // Assuming you have a branch object initialized somehow
    private Manager manager;  // Assuming you have a manager object initialized somehow

    public ManagerController() {
        // Initialize the branch object and manager (for demonstration purposes)
        // In a real scenario, you'd inject this via constructor or service
        branch = new Branch("B001", "Main Branch", "123 Main St", "555-1234", new Manager("M001", "Alice", new Bank(), branch, "Manager", "Finance", 70000, 123456789, "alice@bank.com", LocalDate.now()));
        manager = branch.getBranchManager(); // Getting the manager from branch
    }

    // Show loan approval page
    @GetMapping("/approveLoan/{applicationId}")
    public String showApproveLoanPage(@PathVariable String applicationId, Model model) {
        // Find the loan application by applicationId in the branch's loanApplications list
        LoanApplication loanApplication = findLoanApplicationById(applicationId);

        if (loanApplication != null) {
            model.addAttribute("loanApplication", loanApplication);
            return "approveLoan";  // Render the loan approval page (approveLoan.html)
        } else {
            model.addAttribute("error", "Loan Application not found.");
            return "error";  // If loan application not found, show error page
        }
    }

    // Approve or Reject loan (Handle POST request for approval)
    @PostMapping("/approveLoan")
    public String approveLoan(@RequestParam String applicationId, @RequestParam String status, Model model) {
        LoanApplication loanApplication = findLoanApplicationById(applicationId);
        if (loanApplication != null) {
            String message = "";

            // Only approve if loan is verified
            if (loanApplication.isVerified()) {
                loanApplication.updateLoanStatus(status);  // Update status to Approved/Rejected
                if ("Approved".equalsIgnoreCase(status)) {
                    loanApplication.setVerifiedBy(manager);
                    message = "Loan application for " + loanApplication.getCustomer().getCustomerName() + " has been approved.";
                } else {
                    message = "Loan application for " + loanApplication.getCustomer().getCustomerName() + " has been rejected.";
                }
            } else {
                message = "Loan application for " + loanApplication.getCustomer().getCustomerName() + " cannot be approved as it is not verified.";
            }

            model.addAttribute("message", message);
            return "loanApprovalStatus";  // Render the status page after loan approval
        } else {
            model.addAttribute("error", "Loan Application not found.");
            return "error";  // If loan application not found, show error page
        }
    }

    // Show employee report page
    @GetMapping("/generateEmployeeReport/{employeeId}")
    public String showEmployeeReportPage(@PathVariable String employeeId, Model model) {
        // Find the employee by employeeId in the branch's employee list
        Employee employee = findEmployeeById(employeeId);
        if (employee != null) {
            model.addAttribute("employee", employee);
            return "employeeReport";  // Render the employee report page (employeeReport.html)
        } else {
            model.addAttribute("error", "Employee not found.");
            return "error";  // If employee not found, show error page
        }
    }

    // Provide bonus page (GET)
    @GetMapping("/provideBonus")
    public String showProvideBonusPage() {
        return "provideBonus";  // Render page where bonus can be entered (provideBonus.html)
    }

    // Handle bonus provision (POST)
    @PostMapping("/provideBonus")
    public String provideBonus(@RequestParam String employeeId, @RequestParam double bonus, Model model) {
        Employee employee = findEmployeeById(employeeId);
        if (employee != null && bonus > 0) {
            double updatedSalary = employee.getSalary() + bonus;
            employee.updateSalary(updatedSalary);
            String message = "Bonus of " + bonus + " provided to Employee ID: " + employeeId;
            model.addAttribute("message", message);
            return "bonusStatus";  // Render status after bonus provision
        } else {
            model.addAttribute("error", "Invalid employee or bonus amount.");
            return "error";  // If employee not found or invalid bonus amount, show error page
        }
    }

    // Helper method to find loan application by ID
    private LoanApplication findLoanApplicationById(String applicationId) {
        List<LoanApplication> loanApplications = branch.getLoanApplications();
        for (LoanApplication loanApplication : loanApplications) {
            if (loanApplication.getApplicationId().equals(applicationId)) {
                return loanApplication;
            }
        }
        return null; // If not found
    }

    // Helper method to find employee by ID
    private Employee findEmployeeById(String employeeId) {
        List<Employee> employees = branch.getEmployees();
        for (Employee employee : employees) {
            if (employee.getEmployeeID().equals(employeeId)) {
                return employee;
            }
        }
        return null; // If not found
    }
}
