package _04_InterfacesAndAbstraction.Lab.BorderControl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String command = scanner.nextLine();

        List<Identifiable> citizenList = new ArrayList<>();
        List<Identifiable> robotList = new ArrayList<>();

        while (!"End".equals(command)) {

            String[] commandData = command.split(" ");

            if (commandData.length == 2) {
                String robotModel = commandData[0];
                String robotId = commandData[1];
                Robot robot = new Robot(robotModel, robotId);
                robotList.add(robot);
            } else {
                String citizenName = commandData[0];
                int citizenAge = Integer.parseInt(commandData[1]);
                String citizenId = commandData[2];
                Citizen citizen = new Citizen(citizenName, citizenAge, citizenId);
                citizenList.add(citizen);
            }

            command = scanner.nextLine();
        }
        String lastDigitOfFakeId = scanner.nextLine();

        printFakeId(citizenList, lastDigitOfFakeId);
        printFakeId(robotList, lastDigitOfFakeId);

    }

    private static void printFakeId(List<Identifiable> list, String lastDigit){
        for (Identifiable element : list) {
            if(element.getId().endsWith(lastDigit)){
                System.out.println(element.getId().trim());
            }
        }
    }
}
