import java.util.Date;

class Card {
    private String cardNumber;
    private String name;
    private int cvv;
    private Date expiryDate;
    private String status;

 
    public Card(String cardNumber, String name, int cvv, Date expiryDate) {
        this.cardNumber = cardNumber;
        this.name = name;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
        this.status = "Inactive"; 
        
    }

    
    public void blockCard() {
        status = "Blocked";
        System.out.println("Card has been blocked.");
    }


    public void unblockCard() {
        status = "Active";
        System.out.println("Card has been unblocked.");
    }

    public void activateCard() {
        status = "Active";
        System.out.println("Card has been activated.");
    }


    public String getCardDetails() {
        return "Card Number: " + cardNumber +
               "\nName: " + name +
               "\nExpiry Date: " + expiryDate +
               "\nStatus: " + status;
    }


    public void withdraw(double amount) {
        if (status.equals("Active")) {
           
            System.out.println("Amount " + amount + " withdrawn.");
        } else {
            System.out.println("Card is blocked. Cannot withdraw.");
        }
    }

    // Method to deposit amount using the card
    public void deposit(double amount) {
        if (status.equals("Active")) {
            
            System.out.println("Amount " + amount + " deposited.");
        } else {
            System.out.println("Card is blocked. Cannot deposit.");
        }
    }


    public String getCardNumber() {
        return cardNumber;
    }

    public String getName() {
        return name;
    }

    public int getCvv() {
        return cvv;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public String getStatus() {
        return status;
    }
}
