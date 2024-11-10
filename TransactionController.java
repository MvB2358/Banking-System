import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final Branch branch;

    public TransactionController(Branch branch) {
        this.branch = branch;
    }

    // Page to select transaction type (Deposit, Withdraw, Transfer)
    @GetMapping("/types")
    public String showTransactionTypes() {
        return "transactionTypePage"; // Returns transactionTypePage.html
    }

    // Handle Deposit transaction
    @PostMapping("/deposit")
    public String handleDeposit(@RequestParam String accountId, @RequestParam double amount, Model model) {
        Account destinationAccount = findAccountById(accountId);
        if (destinationAccount == null) {
            return "error";  // Error page if account not found
        }

        // Create a new Deposit transaction
        Transaction depositTransaction = new Transaction(null, destinationAccount, "Deposit", amount);
        boolean isSuccess = depositTransaction.processTransaction();

        // Add transaction details to the model
        model.addAttribute("transactionDetails", depositTransaction.getTransactionDetails());

        if (isSuccess) {
            return "transactionDetails"; // Returns transactionDetails.html for successful transaction
        } else {
            model.addAttribute("error", "Deposit failed: " + depositTransaction.getFailureReason());
            return "error"; // Returns an error page if transaction failed
        }
    }

    // Handle Withdraw transaction
    @PostMapping("/withdraw")
    public String handleWithdraw(@RequestParam String accountId, @RequestParam double amount, Model model) {
        Account sourceAccount = findAccountById(accountId);
        if (sourceAccount == null) {
            return "error";  // Error page if account not found
        }

        // Create a new Withdraw transaction
        Transaction withdrawTransaction = new Transaction(sourceAccount, null, "Withdraw", amount);
        boolean isSuccess = withdrawTransaction.processTransaction();

        // Add transaction details to the model
        model.addAttribute("transactionDetails", withdrawTransaction.getTransactionDetails());

        if (isSuccess) {
            return "transactionDetails"; // Returns transactionDetails.html for successful transaction
        } else {
            model.addAttribute("error", "Withdrawal failed: " + withdrawTransaction.getFailureReason());
            return "error"; // Returns an error page if transaction failed
        }
    }

    // Handle Transfer transaction
    @PostMapping("/transfer")
    public String handleTransfer(@RequestParam String sourceAccountId, @RequestParam String destinationAccountId, @RequestParam double amount, Model model) {
        Account sourceAccount = findAccountById(sourceAccountId);
        Account destinationAccount = findAccountById(destinationAccountId);

        if (sourceAccount == null || destinationAccount == null) {
            return "error";  // Error page if account not found
        }

        // Create a new Transfer transaction
        Transaction transferTransaction = new Transaction(sourceAccount, destinationAccount, "Transfer", amount);
        boolean isSuccess = transferTransaction.processTransaction();

        // Add transaction details to the model
        model.addAttribute("transactionDetails", transferTransaction.getTransactionDetails());

        if (isSuccess) {
            return "transactionDetails"; // Returns transactionDetails.html for successful transaction
        } else {
            model.addAttribute("error", "Transfer failed: " + transferTransaction.getFailureReason());
            return "error"; // Returns an error page if transaction failed
        }
    }

    // Helper method to find Account by ID
    private Account findAccountById(String accountId) {
        try {
            int accId = Integer.parseInt(accountId);
            return branch.getAccounts().stream()
                    .filter(acc -> acc.getAccountNumber() == accId)
                    .findFirst()
                    .orElse(null);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    // Get Transaction details (Page to view a specific transaction's details)
    @GetMapping("/details/{transactionId}")
    public String getTransactionDetails(@PathVariable String transactionId, Model model) {
        // Find the transaction by transaction ID
        Transaction transaction = findTransactionById(transactionId);
        if (transaction == null) {
            model.addAttribute("error", "Transaction not found.");
            return "error"; // Returns error page if transaction not found
        }

        // Add transaction details to the model
        model.addAttribute("transactionDetails", transaction.getTransactionDetails());

        // Return transaction details page
        return "transactionDetails"; // Returns transactionDetails.html for displaying transaction details
    }

    // Helper method to find Transaction by ID (this can be adapted to your system)
    private Transaction findTransactionById(String transactionId) {
        // This is just a placeholder method. In a real-world application, 
        // this would query a database or service to retrieve the transaction by its ID.
        // For now, assuming you can retrieve it based on the ID from some source.
        return branch.getTransactions().stream()
                     .filter(t -> t.getTransactionId().equals(transactionId))
                     .findFirst()
                     .orElse(null);
    }
}
