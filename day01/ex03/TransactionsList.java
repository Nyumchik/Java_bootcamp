package ex03;

import java.util.UUID;

public interface TransactionsList {
    public void addTransaction(Transaction transaction);
    public void removeById(UUID id) throws TransactionNotFoundException;
    public Transaction[] toArray();
}
