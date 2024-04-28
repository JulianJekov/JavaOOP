package Chainblock;

import java.util.*;
import java.util.stream.Collectors;

public class ChainblockImpl implements Chainblock {
    private Map<Integer, Transaction> transactionMap;

    public ChainblockImpl() {
        this.transactionMap = new LinkedHashMap<>();
    }

    public int getCount() {
        return transactionMap.size();
    }

    public void add(Transaction transaction) {
        transactionMap.putIfAbsent(transaction.getId(), transaction);
    }

    public boolean contains(Transaction transaction) {
        return contains(transaction.getId());
    }

    public boolean contains(int id) {
        return transactionMap.containsKey(id);
    }

    public void changeTransactionStatus(int id, TransactionStatus newStatus) {
        Transaction transaction = getById(id);

        transaction.setStatus(newStatus);
    }

    public void removeTransactionById(int id) {
        Transaction transaction = getById(id);
        transactionMap.remove(transaction.getId());
    }

    public Transaction getById(int id) {
        return transactionMap.values().stream().filter(t -> t.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No such Chainblock.Transaction with id " + id));
    }

    // TODO: Ask ChatGPT
    public Iterable<Transaction> getByTransactionStatus(TransactionStatus status) {

        List<Transaction> transactions = transactionMap.values().stream()
                .filter(t -> t.getStatus().equals(status))
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .collect(Collectors.toList());
        if (transactions.isEmpty()) {
            throw new IllegalArgumentException("No such Chainblock.Transaction with Status " + status);
        }
        return transactions;

    }

    public Iterable<String> getAllSendersWithTransactionStatus(TransactionStatus status) {
        List<Transaction> transactionsByStatus = convertIterableToList(getByTransactionStatus(status));
        return transactionsByStatus.stream()
                .map(Transaction::getFrom)
                .collect(Collectors.toList());
    }

    private List<Transaction> convertIterableToList(Iterable<Transaction> iterable) {
        List<Transaction> list = new ArrayList<>();
        iterable.forEach(list::add);
        return list;
    }

    public Iterable<String> getAllReceiversWithTransactionStatus(TransactionStatus status) {
        List<Transaction> transactionsByReceivers = convertIterableToList(getByTransactionStatus(status));
        return transactionsByReceivers.stream()
                .map(Transaction::getTo)
                .collect(Collectors.toList());
    }

    public Iterable<Transaction> getAllOrderedByAmountDescendingThenById() {
        return transactionMap.values().stream()
                .sorted(Comparator.comparing(Transaction::getAmount).reversed()
                        .thenComparing(Transaction::getId))
                .collect(Collectors.toList());

    }

    public Iterable<Transaction> getBySenderOrderedByAmountDescending(String sender) {
        List<Transaction> transactionsBySenderOrdered = transactionMap.values().stream()
                .filter(t -> t.getFrom().equals(sender))
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .collect(Collectors.toList());
        if (transactionsBySenderOrdered.isEmpty()) {
            throw new IllegalArgumentException("No such Chainblock.Transaction with Sender" + sender);
        }
        return transactionsBySenderOrdered;
    }

    public Iterable<Transaction> getByReceiverOrderedByAmountThenById(String receiver) {
        List<Transaction> transactionsByReceiverOrdered = (convertIterableToList(getAllOrderedByAmountDescendingThenById()))
                .stream()
                .filter(t -> t.getTo().equals(receiver))
                .collect(Collectors.toList());
        if (transactionsByReceiverOrdered.isEmpty()) {
            throw new IllegalArgumentException("No such Chainblock.Transaction with Receiver " + receiver);
        }
        return transactionsByReceiverOrdered;
    }

    public Iterable<Transaction> getByTransactionStatusAndMaximumAmount(TransactionStatus status, double amount) {
        return transactionMap.values().stream()
                .filter(t -> t.getStatus().equals(status) && t.getAmount() <= amount)
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .collect(Collectors.toList());

    }

    public Iterable<Transaction> getBySenderAndMinimumAmountDescending(String sender, double amount) {
        List<Transaction> transactionsGetBySenderAndMinAmountOrdered = convertIterableToList(getBySenderOrderedByAmountDescending(sender))
                .stream()
                .filter(t -> t.getAmount() > amount).collect(Collectors.toList());
        if(transactionsGetBySenderAndMinAmountOrdered.isEmpty()) {
            throw new IllegalArgumentException("No such Chainblock.Transaction with Sender" + sender +
                    " or too low Amount " + amount );
        }
        return transactionsGetBySenderAndMinAmountOrdered;
    }

    public Iterable<Transaction> getByReceiverAndAmountRange(String receiver, double low, double high) {
        List<Transaction> transactionsByReceiverOrdered = convertIterableToList(getAllOrderedByAmountDescendingThenById())
                .stream()
                .filter(t -> t.getTo().equals(receiver))
                .collect(Collectors.toList());
        if(transactionsByReceiverOrdered.isEmpty()) {
            throw new IllegalArgumentException("No such Transaction with Receiver " + receiver);
        }
        return transactionsByReceiverOrdered;
    }

    public Iterable<Transaction> getAllInAmountRange(double low, double high) {
        return transactionMap.values().stream()
                .filter(t -> t.getAmount() >= low && t.getAmount() <= high)
                .collect(Collectors.toList());
    }

    public Iterator<Transaction> iterator() {
        return transactionMap.values().iterator();
    }
}
