package Chainblock;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        ChainblockImpl chainblock = new ChainblockImpl();
        chainblock.getBySenderOrderedByAmountDescending("Ivan");
    }
}
