package ex03;

import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList{
    private TransactionListNode start;
    private TransactionListNode end;
    private Integer length;

    private class TransactionListNode {
        Transaction data;
        TransactionListNode next;
        TransactionListNode prev;
        private TransactionListNode(Transaction data,  TransactionListNode next, TransactionListNode prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }
    public TransactionsLinkedList(){
        this.start = null;
        this.end = null;
        this.length = 0;
    }
   
    public void addTransaction(Transaction transaction) {
        TransactionListNode node = new TransactionListNode(transaction, null,  null);
        if(start == null) {
            start = node;
            end = node;
            length++;
        } else {
            end.next = node;
            node.prev = end;
            end = end.next;
            this.length++;
        }
    }

    public void removeById(UUID identifier) throws TransactionNotFoundException {
        TransactionListNode tmp = start;
        if (tmp == end) {
            throw new TransactionNotFoundException();
        }
        while(tmp != null) {
            if(tmp.data.getIdentifier() == identifier) {
                TransactionListNode item = new TransactionListNode(tmp.data, tmp.next, tmp.prev);
                if (item.prev == null) {
                    start = item.next;
                } else {
                    item.prev.next = item.next;
                    tmp.prev = null;
                }
                if (item.next == null) {
                    end = item.prev;
                } else {
                    item.next.prev = item.prev;
                    tmp.next = null;
                }
                tmp.data = null;
                length--;
                return ;
            }
            tmp = tmp.next;
        }
        throw new TransactionNotFoundException();
    }

    public Transaction[] toArray() {
        Transaction[] array = new Transaction[length];
        TransactionListNode tmp = start;
        int i = 0;
        while(tmp != null) {
            array[i] = tmp.data;
            tmp = tmp.next;
            i++;
        }
        return array;
    }
}
