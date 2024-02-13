package ex03;

import java.util.UUID;

public class Transaction {
    private UUID identifier;
    private User recipient;
    private User sender;
    private Var category;
    private Integer amount;

    public enum Var {
        INCOME,
        OUTCOME
    }

    public Transaction(UUID identifier, User sender, User recipient, Var category, Integer amount){
        this.identifier = UUID.randomUUID();
        this.recipient = recipient;
        this.sender = sender;
        this.category = category;
        setAmount(amount);
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        if (this.category == Var.INCOME && amount >= 0  && sender.getBalance() >= amount)
            this.amount = amount;
        else if (this.category == Var.OUTCOME && amount <= 0 && recipient.getBalance() >= amount * (-1))
            this.amount = amount;
        else
            System.out.println("Transaction error");
    }

    public UUID getIdentifier() {
        return identifier;
    }

    public String getRecipient() {
        return this.recipient.getName();
    }

    public String getSender() {
        return this.sender.getName();
    }

    public Var getCategory(){
        return this.category;
    }
}
