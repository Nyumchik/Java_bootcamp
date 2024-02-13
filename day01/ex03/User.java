package ex03;

public class User {
    private Integer identifier;
    private String name;
    private Integer balance;
    private TransactionsList transactions;

    public User(String name, Integer balance) {
        this.identifier = UserIdsGenerator.getInstance().generateId();
        this.name = name;
        setBalance(balance);
        this.transactions = new TransactionsLinkedList();
    }

    public Integer getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        if (balance < 0) {
            this.balance = 0;
        } else {
            this.balance = balance;
        }
    }

    public TransactionsList getTransactions() {
		return transactions;
	}
}
