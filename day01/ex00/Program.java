package ex00;

import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        User userJohn = new User(1, "John", 1000);
        User userMike = new User(2, "Mike", 1000);

        System.out.println(userJohn.getIdentifier() + " " + userJohn.getName() + " " + userJohn.getBalance());

        Transaction credit = new Transaction(UUID.randomUUID(), userJohn, userMike, Transaction.Var.OUTCOME, -500);
        Transaction debit = new Transaction(UUID.randomUUID(), userMike, userJohn, Transaction.Var.INCOME, 500);
        
        System.out.println(credit.getSender() + " -> " + credit.getRecipient() + ", " + credit.getAmount()
                + ", " + credit.getCategory() + ", " + credit.getIdentifier());
        System.out.println(debit.getSender() + " -> " + debit.getRecipient() + ", " + debit.getAmount()
                + ", " + debit.getCategory() + ", " + debit.getIdentifier());
    }
}
