package Chainblock;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class ChainblockImplTest {

    private Chainblock chainblock;
    private List<Transaction> transactions;

    @Before
    public void setUp() {
        this.chainblock = new ChainblockImpl();
        this.transactions = new ArrayList<>();
        addTransactionsToList();
    }

    @Test
    public void testAddShouldAddTransaction() {
        chainblock.add(transactions.get(0));
        assertEquals(1, chainblock.getCount());
        chainblock.add(transactions.get(1));
        assertEquals(2, chainblock.getCount());
    }

    @Test
    public void testAddShouldNotAddDuplicateTransaction() {
        chainblock.add(transactions.get(0));
        chainblock.add(transactions.get(0));
        assertEquals(1, chainblock.getCount());
    }

    @Test
    public void testContainsWithTransactionShouldReturnFalse() {
        chainblock.add(transactions.get(0));
        boolean chainBlockContainsTransaction = chainblock.contains(transactions.get(1));
        Assert.assertFalse(chainBlockContainsTransaction);
    }

    @Test
    public void testContainsWithTransactionShouldReturnTrue() {
        chainblock.add(transactions.get(0));
        boolean chainBlockContainsTransaction = chainblock.contains(transactions.get(0).getId());
        Assert.assertTrue(chainBlockContainsTransaction);
    }

    @Test
    public void testContainsWithIdShouldReturnFalse() {
        chainblock.add(transactions.get(0));
        boolean chainBlockContainsTransaction = chainblock.contains(transactions.get(1).getId());
        Assert.assertFalse(chainBlockContainsTransaction);
    }

    @Test
    public void testContainsWithIdShouldReturnTrue() {
        chainblock.add(transactions.get(0));
        boolean chainBlockContainsTransaction = chainblock.contains(transactions.get(0));
        Assert.assertTrue(chainBlockContainsTransaction);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testChangeTransactionStatusShouldThrowWithMissingTransaction() {
        chainblock.changeTransactionStatus(100, TransactionStatus.FAILED);
    }

    @Test
    public void testChangeTransactionStatusShouldChangeStatus() {
        chainblock.add(transactions.get(0));
        chainblock.changeTransactionStatus(transactions.get(0).getId(), TransactionStatus.FAILED);
        TransactionStatus newTransactionStatus = chainblock.getById(transactions.get(0).getId()).getStatus();
        assertEquals(TransactionStatus.FAILED, newTransactionStatus);
    }

    @Test
    public void testGetByIdShouldReturnTheTransaction() {
        chainblock.add(transactions.get(0));
        Transaction actual = chainblock.getById(transactions.get(0).getId());
        assertEquals(transactions.get(0), actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByIdShouldThrowWithMissingTransaction() {
        fillChainblockWithTransactions();
        chainblock.getById(100);
    }

    @Test
    public void testRemoveTransactionByIdShouldRemoveTheTransactionWithGivenId() {
        chainblock.add(transactions.get(0));
        chainblock.add(transactions.get(1));
        int expectedCount = chainblock.getCount();
        chainblock.removeTransactionById(transactions.get(0).getId());
        int actualCount = chainblock.getCount();
        Assert.assertEquals(expectedCount, actualCount + 1);
        Assert.assertFalse(chainblock.contains(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveTransactionByIdShouldThrowWithMissingId() {
        fillChainblockWithTransactions();
        chainblock.removeTransactionById(100);
    }

    @Test
    public void testGetByTransactionStatusShouldReturnTheTransactionsWithGivenStatusDescendingOrderByAmount() {
        fillChainblockWithTransactions();
        List<Transaction> expected = transactions.stream()
                .filter(t -> t.getStatus().equals(TransactionStatus.SUCCESSFUL))
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .collect(Collectors.toList());
        Iterable<Transaction> actual = chainblock.getByTransactionStatus(TransactionStatus.SUCCESSFUL);
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByTransactionStatusShouldThrowIfMissingSuchTransactions() {
        fillChainblockWithTransactions();
        chainblock.getByTransactionStatus(TransactionStatus.ABORTED);
    }

    @Test
    public void testGetAllSendersWithTransactionStatusShouldReturnCollectionWithSortedNames() {
        fillChainblockWithTransactions();
        List<String> expected = transactions.stream()
                .filter(t -> t.getStatus().equals(TransactionStatus.SUCCESSFUL))
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .map(Transaction::getFrom)
                .collect(Collectors.toList());
        Iterable<String> actual = chainblock.getAllSendersWithTransactionStatus(TransactionStatus.SUCCESSFUL);
        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetAllSendersWithTransactionStatusShouldThrowIfNoTransactions() {
        fillChainblockWithTransactions();
        chainblock.getAllSendersWithTransactionStatus(TransactionStatus.ABORTED);
    }

    @Test
    public void testGetAllReceiversWithTransactionStatusShouldReturnCollectionWithSortedNames() {
        fillChainblockWithTransactions();
        List<String> expected = transactions.stream()
                .filter(t -> t.getStatus().equals(TransactionStatus.SUCCESSFUL))
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .map(Transaction::getTo)
                .collect(Collectors.toList());
        Iterable<String> actual = chainblock.getAllReceiversWithTransactionStatus(TransactionStatus.SUCCESSFUL);
        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetAllReceiversWithTransactionStatusShouldThrowIfNoTransactions() {
        fillChainblockWithTransactions();
        chainblock.getAllReceiversWithTransactionStatus(TransactionStatus.ABORTED);
    }

    @Test
    public void testGetAllOrderedByAmountDescendingThenByIdShouldReturnOrderedTransactions() {
        fillChainblockWithTransactions();
        List<Transaction> expected = transactions
                .stream()
                .sorted(Comparator.comparing(Transaction::getAmount).reversed().thenComparing(Transaction::getId))
                .collect(Collectors.toList());
        Iterable<Transaction> actual = chainblock.getAllOrderedByAmountDescendingThenById();
        Assert.assertEquals(expected, actual);
        System.out.println();
    }

    @Test
    public void testGetBySenderOrderedByAmountDescendingShouldReturnTransactionsWithSenderOrderedByAmountDescending() {
        fillChainblockWithTransactions();
        String sender = transactions.get(0).getFrom();
        List<Transaction> expected = transactions.stream()
                .filter(t -> t.getFrom().equals(sender))
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .collect(Collectors.toList());
        Iterable<Transaction> actual = chainblock.getBySenderOrderedByAmountDescending(sender);
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBySenderOrderedByAmountDescendingShouldThrowWithMissingSender() {
        fillChainblockWithTransactions();
        chainblock.getBySenderOrderedByAmountDescending("Ivan");

    }

    @Test
    public void testGetByReceiverOrderedByAmountThenByIdShouldReturnTransactionsWithReceiverOrdered() {
        fillChainblockWithTransactions();
        String receiver = transactions.get(0).getTo();
        List<Transaction> expected = transactions.stream()
                .filter(t -> t.getTo().equals(receiver))
                .sorted(Comparator.comparing(Transaction::getAmount).reversed().thenComparing(Transaction::getId))
                .collect(Collectors.toList());
        Iterable<Transaction> actual = chainblock.getByReceiverOrderedByAmountThenById(receiver);
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByReceiverOrderedByAmountThenByIdShouldThrowIfNoSuchReceiver() {
        fillChainblockWithTransactions();
        chainblock.getByReceiverOrderedByAmountThenById("Ivan");
    }

    @Test
    public void testGetByTransactionStatusAndMaximumAmountShouldReturnAllTransactionsWithGivenStatusAndMaxAmount() {
        fillChainblockWithTransactions();
        List<Transaction> expected = transactions.stream()
                .filter(t -> t.getStatus().equals(TransactionStatus.SUCCESSFUL) && t.getAmount() <= 10)
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .collect(Collectors.toList());
        double maxAmount = transactions.get(1).getAmount();
        Iterable<Transaction> actual = chainblock.getByTransactionStatusAndMaximumAmount
                (TransactionStatus.SUCCESSFUL, maxAmount);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetBySenderAndMinimumAmountShouldReturnAllTransactionsWithGivenSenderAndMinAmount() {
        fillChainblockWithTransactions();
        String sender = transactions.get(0).getFrom();
        double minAmount = transactions.get(2).getAmount() - 1;
        List<Transaction> expected = transactions.stream()
                .filter(t -> t.getFrom().equals(sender) && t.getAmount() > minAmount)
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .collect(Collectors.toList());

        Iterable<Transaction> actual = chainblock.getBySenderAndMinimumAmountDescending(sender, minAmount);

        Assert.assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBySenderAndMinimumAmountShouldThrowWithMissingTransaction() {
        fillChainblockWithTransactions();
        chainblock.getBySenderAndMinimumAmountDescending("Pesho", 100);
    }

    @Test
    public void testGetByReceiverAndAmountRangeShouldReturnTransactionsWithReceiverAndAmountInRange() {
        fillChainblockWithTransactions();
        String receiver = transactions.get(2).getTo();
        List<Transaction> expected = transactions.stream()
                .filter(t -> t.getTo().equals(receiver) && t.getAmount() >= 10 && t.getAmount() < 12)
                .sorted(Comparator.comparing(Transaction::getAmount).reversed().thenComparing(Transaction::getId))
                .collect(Collectors.toList());
        Iterable<Transaction> actual = chainblock.getByReceiverAndAmountRange(receiver, 10, 12);
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByReceiverAndAmountRangeShouldShouldThrowWithMissingTransactions() {
        fillChainblockWithTransactions();
        chainblock.getByReceiverAndAmountRange("Ivan", 100, 200);
    }

    @Test
    public void testGetAllInAmountRangeShouldReturnAllTransactionsInGivenAmountRange() {
        fillChainblockWithTransactions();
        List<Transaction> expected = transactions.stream()
                .filter(t -> t.getAmount() >= 9 && t.getAmount() <= 20)
                .collect(Collectors.toList());
        Iterable<Transaction> actual = chainblock.getAllInAmountRange(9, 20);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testIteratorShouldReturnTrueIfCollectionHasNext() {
        fillChainblockWithTransactions();
        boolean actual = chainblock.iterator().hasNext();
        Assert.assertTrue(actual);
    }

    private void addTransactionsToList() {
        transactions = List.of(
                new TransactionImpl(0, TransactionStatus.SUCCESSFUL,
                        "Pesho", "Sasho", 11.20),
                new TransactionImpl(1, TransactionStatus.SUCCESSFUL,
                        "Pesho", "Toshko", 10),
                new TransactionImpl(2, TransactionStatus.UNAUTHORIZED,
                        "Sasho", "Pesho", 11.20),
                new TransactionImpl(3, TransactionStatus.FAILED,
                        "Toshko", "Sasho", 12.20),
                new TransactionImpl(4, TransactionStatus.SUCCESSFUL,
                        "Sasho", "Pesho", 10.50)
        );

    }

    private void fillChainblockWithTransactions() {
        transactions.forEach(chainblock::add);
    }
}