import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final Branch branch;  // Assume the Branch is already initialized with employees, customers, accounts

    public TransactionController(Branch branch) {
        this.branch = branch;
    }

    // Handle Deposit transactions
    @PostMapping("/deposit")
    public ResponseEntity<String> handleDeposit(@RequestParam String accountId, @RequestParam double amount) {
        Account destinationAccount = findAccountById(accountId);
        if (destinationAccount != null) {
            // Create a new Deposit transaction
            Transaction depositTransaction = new Transaction("T" + System.currentTimeMillis(),
                    null, destinationAccount, "Deposit", amount, LocalDateTime.now());
            boolean isSuccess = depositTransaction.processTransaction();
            depositTransaction.getTransactionDetails();  // Display transaction details in logs

            if (isSuccess) {
                return ResponseEntity.ok("Deposit successful!");
            } else {
                return ResponseEntity.status(400).body("Deposit failed: " + depositTransaction.getFailureReason());
            }
        } else {
            return ResponseEntity.status(404).body("Account not found.");
        }
    }

    // Handle Withdraw transactions
    @PostMapping("/withdraw")
    public ResponseEntity<String> handleWithdraw(@RequestParam String accountId, @RequestParam double amount) {
        Account sourceAccount = findAccountById(accountId);
        if (sourceAccount != null) {
            // Create a new Withdraw transaction
            Transaction withdrawTransaction = new Transaction("T" + System.currentTimeMillis(),
                    sourceAccount, null, "Withdraw", amount, LocalDateTime.now());
            boolean isSuccess = withdrawTransaction.processTransaction();
            withdrawTransaction.getTransactionDetails();  // Display transaction details in logs

            if (isSuccess) {
                return ResponseEntity.ok("Withdrawal successful!");
            } else {
                return ResponseEntity.status(400).body("Withdrawal failed: " + withdrawTransaction.getFailureReason());
            }
        } else {
            return ResponseEntity.status(404).body("Account not found.");
        }
    }

    // Handle Transfer transactions
    @PostMapping("/transfer")
    public ResponseEntity<String> handleTransfer(@RequestParam String sourceAccountId, @RequestParam String destinationAccountId, @RequestParam double amount) {
        Account sourceAccount = findAccountById(sourceAccountId);
        Account destinationAccount = findAccountById(destinationAccountId);

        if (sourceAccount != null && destinationAccount != null) {
            // Create a new Transfer transaction
            Transaction transferTransaction = new Transaction("T" + System.currentTimeMillis(),
                    sourceAccount, destinationAccount, "Transfer", amount, LocalDateTime.now());
            boolean isSuccess = transferTransaction.processTransaction();
            transferTransaction.getTransactionDetails();  // Display transaction details in logs

            if (isSuccess) {
                return ResponseEntity.ok("Transfer successful!");
            } else {
                return ResponseEntity.status(400).body("Transfer failed: " + transferTransaction.getFailureReason());
            }
        } else {
            return ResponseEntity.status(404).body("Account(s) not found.");
        }
    }

    // Helper method to find Account by ID
    private Account findAccountById(String accountId) {
        // Assume the Branch has a method to get all accounts
        return branch.getAccounts().stream()
                .filter(acc -> acc.getAccountNumber() == Integer.parseInt(accountId))
                .findFirst()
                .orElse(null);
    }
}
