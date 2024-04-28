package _02_Encapsulation.Lab.P01EncapsulationLab;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfPeople = Integer.parseInt(scanner.nextLine());

        List<Person> people = new ArrayList<>();


        for (int i = 0; i < numberOfPeople; i++) {
            Person person = createPerson(scanner);
            if(person != null) {
                people.add(person);
            }
        }


        Team team = new Team("Black Eagles");
       // people.forEach(team::addPlayer);
        System.out.printf("First team have %d players%n",team.getFirstTeam().size());
        System.out.printf("Reserve team have %d players%n",team.getReserveTeam().size());

        for (Person person : people) {
            if(person.getAge() < 40){
                team.getFirstTeam().add(person);
            }else {
                team.getReserveTeam().add(person);
            }
        }
        System.out.printf("First team have %d players%n",team.getFirstTeam().size());
        System.out.printf("Reserve team have %d players%n",team.getReserveTeam().size());
    }

    private static Person createPerson(Scanner scanner) {
        String[] input = scanner.nextLine().split(" ");
        String firstName = input[0];
        String lastName = input[1];
        int age = Integer.parseInt(input[2]);
        double salary = Double.parseDouble(input[3]);
        try {
            return new Person(firstName, lastName, age, salary);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}