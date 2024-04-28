package _04_InterfacesAndAbstraction.Exercise.BirthdayCelebrations;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String command = scanner.nextLine();
        List<Identifiable> peopleAndRobots = new ArrayList<>();
        List<Birthable> peopleAndPets = new ArrayList<>();

        while (!"End".equals(command)) {

            String[] commandData = command.split("\\s+");

            if (commandData[0].equals("InterfacesAndAbstraction.Exercise.FoodShortage.Citizen")) {
                String citizenName = commandData[1];
                int citizenAge = Integer.parseInt(commandData[2]);
                String citizenId = commandData[3];
                String citizenBirthDate = commandData[4];
                Birthable person = new Citizen(citizenName, citizenAge, citizenId, citizenBirthDate);
                peopleAndPets.add(person);
            } else if (commandData[0].equals("Pet")) {
                String petName = commandData[1];
                String petBirthDate = commandData[2];
                Birthable pet = new Pet(petName, petBirthDate);
                peopleAndPets.add(pet);
            } else {
                String robotModel = commandData[1];
                String robotId = commandData[2];
                Identifiable robot = new Robot(robotModel, robotId);
                peopleAndRobots.add(robot);
            }

            command = scanner.nextLine();
        }
        String specificYear = scanner.nextLine();
        //boolean specificYearExist = peopleAndPets.stream().anyMatch(b->b.getBirthDate().endsWith(specificYear));
       // if(specificYearExist) {
            peopleAndPets.stream().filter(b -> b.getBirthDate().endsWith(specificYear)).forEach(b -> System.out.println(b.getBirthDate()));
       // } else {
        //    System.out.println("<no output>");
       // }
    }
}
