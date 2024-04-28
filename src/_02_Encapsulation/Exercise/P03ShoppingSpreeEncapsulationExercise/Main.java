package _02_Encapsulation.Exercise.P03ShoppingSpreeEncapsulationExercise;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Person> people = new LinkedHashMap<>();
        Map<String, Product> products = new LinkedHashMap<>();

        String[] peopleInput = scanner.nextLine().split(";");

        if (!createPerson(people, peopleInput)){
            return;
        }

        String[] productInput = scanner.nextLine().split(";");

        if (!createProduct(products, productInput)){
            return;
        }

        String command = scanner.nextLine();

        while (!"END".equals(command)){
            String[] commandParts = command.split("\\s+");
            String personName = commandParts[0];
            String productName = commandParts[1];


            Person buyer = people.get(personName);
            Product productToBuy = products.get(productName);
            try {
                buyer.buyProduct(productToBuy);
                System.out.printf("%s bought %s%n",personName,productName);
            }catch (IllegalArgumentException ex){
                System.out.println(ex.getMessage());
            }
            command = scanner.nextLine();
        }
        people.values().forEach(System.out::println);
    }

    private static boolean createProduct(Map<String, Product> products, String[] productInput) {
        for (String productData : productInput) {
            String[] productParts = productData.split("=");
            String name = productParts[0];
            double cost = Double.parseDouble(productParts[1]);
            try {
                Product product = new Product(name, cost);
                products.put(name,product);
            } catch (IllegalArgumentException ex){
                System.out.println(ex.getMessage());
                return false;
            }
        }
        return true;
    }

    private static boolean createPerson(Map<String, Person> people, String[] peopleInput) {
        for (String personData : peopleInput) {
            String[] personParts = personData.split("=");
            String name = personParts[0];
            double money = Double.parseDouble(personParts[1]);
            try {
                Person person = new Person(name, money);
                people.put(name, person);
            }catch (IllegalArgumentException ex){
                System.out.println(ex.getMessage());
                return false;
            }
        }
        return true;
    }
}
