import java.time.LocalDate;

public class LoanApplication {
    private static int applicationCounter = 1;
    private String applicationId;
    private Customer customer;
    private double loanAmount;
    private String loanType;
    private String loanStatus;
    private boolean verified;
    private Employee verifiedBy;
    private LocalDate applicationDate;
    private LocalDate approvalDate;

    public LoanApplication(Customer customer, double loanAmount, String loanType, LocalDate applicationDate) {
        this.applicationId = generateApplicationId();
        this.customer = customer;
        this.loanAmount = loanAmount;
        this.loanType = loanType;
        this.applicationDate = applicationDate;
        this.loanStatus = "Pending";
        this.verified = false;
    }

    private String generateApplicationId() {
        String id = String.format("%09d", applicationCounter);
        applicationCounter++;
        return "LAP" + id;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void getLoanDetails() {
        System.out.println("Loan Application ID: " + applicationId);
        System.out.println("Customer: " + customer.getName());
        System.out.println("Loan Amount: " + loanAmount);
        System.out.println("Loan Type: " + loanType);
        System.out.println("Application Date: " + applicationDate);
        System.out.println("Approval Date: " + (approvalDate != null ? approvalDate : "Not Approved"));
        System.out.println("Status: " + loanStatus);
        System.out.println("Verified: " + (verified ? "Yes" : "No"));
        System.out.println("Verified By: " + (verifiedBy != null ? verifiedBy.getEmployeeName() : "Not Verified"));
    }

    public void updateLoanStatus(String status) {
        this.loanStatus = status;
        if ("Approved".equalsIgnoreCase(status)) {
            this.approvalDate = LocalDate.now();
        } else {
            this.approvalDate = null;
        }
    }

    public void updateVerification(boolean bool) {
        this.verified = bool;
        if (!bool) {
            this.verifiedBy = null;
        }
    }

    public void setVerifiedBy(Employee employee) {
        if (verified) {
            this.verifiedBy = employee;
        }
    }

    public boolean isVerified() {
        return verified;
    }
}
