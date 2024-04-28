package _04_InterfacesAndAbstraction.Exercise.CollectionHierarchy;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Addable addCollection = new AddCollection();
        AddRemovable addRemovable = new AddRemoveCollection();
        MyList myList = new MyListImpl();

        String[] collection = scanner.nextLine().split("\\s+");
        int countRemoveOperations = Integer.parseInt(scanner.nextLine());

        performAddOperations(addCollection, collection);
        performAddOperations(addRemovable,collection);
        performAddOperations(myList,collection);

        performRemovedItems(addRemovable, countRemoveOperations);
        performRemovedItems(myList,countRemoveOperations);
    }

    private static void performRemovedItems(AddRemovable addRemovable, int countRemoveOperations) {
        for (int i = 0; i < countRemoveOperations; i++) {
            System.out.print(addRemovable.remove() + " ");
        }
        System.out.println();
    }

    private static void performAddOperations(Addable addable, String[] collection) {
        for (String element : collection) {
            System.out.print(addable.add(element) + " ");
        }
        System.out.println();
    }
}
