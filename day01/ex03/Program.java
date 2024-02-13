package ex03;

import java.util.UUID;

import ex03.Transaction.Var;

public class Program {
    public static void main(String[] args) throws TransactionNotFoundException {
        TransactionsLinkedList list = new TransactionsLinkedList();
        Transaction[] array;
        UUID ud;

        list.addTransaction(new Transaction(UUID.randomUUID(), new User("John", 1000),
                            new User("Mike", 1000), Var.INCOME, 500));

        list.addTransaction(new Transaction(UUID.randomUUID(), new User("Mike", 1000),
                            new User("Q", 1000), Var.OUTCOME, -500));

        System.out.println("BEFOR");
        array = list.toArray();
        for (int i = 0; i < array.length; i++) {
            System.out.println("Sender: " + array[i].getSender());
            System.out.println("Recipient: " + array[i].getRecipient());
            System.out.println("Transaction id: " + array[i].getIdentifier());
            System.out.println("Amount: " + array[i].getAmount());
            System.out.println();
        }

        list.removeById(array[0].getIdentifier());

        System.out.println("AFTER");
        array = list.toArray();
        for (int i = 0; i < array.length; i++) {
            System.out.println("Sender: " + array[i].getSender());
            System.out.println("Recipient: " + array[i].getRecipient());
            System.out.println("Transaction id: " + array[i].getIdentifier());
            System.out.println("Amount: " + array[i].getAmount());
            System.out.println();
        }

        ud = UUID.randomUUID();
        list.removeById(ud);
    }
}
