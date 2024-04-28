package _04_InterfacesAndAbstraction.Exercise.Telephony;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> phonesToCall = Arrays.stream(scanner.nextLine().split("\\s+")).map(String::valueOf).collect(Collectors.toList());
        List<String> sitesToVisit = Arrays.stream(scanner.nextLine().split("\\s+")).map(String::valueOf).collect(Collectors.toList());

        Smartphone smartphone = new Smartphone(phonesToCall,sitesToVisit);

        System.out.print(smartphone.call());
        System.out.print(smartphone.browse());

    }
}
