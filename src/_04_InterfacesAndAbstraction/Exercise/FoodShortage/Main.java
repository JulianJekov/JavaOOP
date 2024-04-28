package _04_InterfacesAndAbstraction.Exercise.FoodShortage;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int commandsCount = Integer.parseInt(scanner.nextLine());

        Map<String, Buyer> people = new HashMap<>();

        for (int i = 0; i < commandsCount; i++) {

            String[] peopleData = scanner.nextLine().split("\\s+");

            if (peopleData.length == 4) {
                String citizenName = peopleData[0];
                int citizenAge = Integer.parseInt(peopleData[1]);
                String citizenId = peopleData[2];
                String citizenBirthDate = peopleData[3];
                Buyer citizen = new Citizen(citizenName, citizenAge, citizenId, citizenBirthDate);
                people.put(citizenName, citizen);
            } else {
                String rebelName = peopleData[0];
                int rebelAge = Integer.parseInt(peopleData[1]);
                String rebelGroup = peopleData[2];
                Buyer rebel = new Rebel(rebelName, rebelAge, rebelGroup);
                people.put(rebelName,rebel);
            }
        }
        String nameOfPerson = scanner.nextLine();

        while (!"End".equals(nameOfPerson)){
            if(people.containsKey(nameOfPerson)){
                people.get(nameOfPerson).buyFood();
            }
            nameOfPerson = scanner.nextLine();
        }
        System.out.println(people.values().stream().mapToInt(Buyer::getFood).sum());
    }
}
