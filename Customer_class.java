import java.util.ArrayList;
import java.util.List;

class Customer{
    private String CIF;
    private String name;
    private String address;
    private int phone_no;
    private List<Account> accounts;  
    private List<Card> cards;        
    
    public Customer(String CIF, String name, String address, int phone_no){
        this.CIF = CIF;
        this.name = name;
        this.address = address;
        this.phone_no = phone_no;
        this.accounts = new ArrayList<>();  
        this.cards = new ArrayList<>();     
    }

    public void addAccount(Account account){
        if (account != null){
            accounts.add(account);  
            System.out.println("Account added: " + account.getAccountNumber());
        } 
		else{
            System.out.println("Invalid account!");
        }
    }


    public void closeAccount(Account account){
        int index = accounts.indexOf(account);  
        if (index != -1) {  
            accounts.set(index, null);
            System.out.println("Account closed: " + account.getAccountNumber());
        } else {
            System.out.println("Account not found!");
        }
    }

    public Account[] getAccountDetails() {
        return accounts.toArray(new Account[0]);
    }

    public void addCard(Card card) {
        if (card != null) {
            cards.add(card);  
            System.out.println("Card added: " + card.getCardNumber());
        } else {
            System.out.println("Invalid card!");
        }
    }

    public void removeCard(Card card) {
        if (cards.remove(card)) {  
            System.out.println("Card removed: " + card.getCardNumber());
        } else {
            System.out.println("Card not found!");
        }
    }

    public Card[] getCardDetails() {
        return cards.toArray(new Card[0]);  
    }

    public String getCIF() {
        return CIF;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getPhoneNo() {
        return phone_no;
    }
}