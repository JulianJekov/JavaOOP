package _05_Polymorphism.Exercise.VehiclesExtension;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Vehicle> vehicleMap = new LinkedHashMap<>();
        fillMap(scanner, vehicleMap);
        int numberOfCommands = Integer.parseInt(scanner.nextLine());
        commandParser(scanner, numberOfCommands, vehicleMap);
        printVehicles(vehicleMap);
    }

    private static void printVehicles(Map<String, Vehicle> vehicleMap) {
        vehicleMap.values().forEach(System.out::println);
    }

    private static void commandParser(Scanner scanner, int numberOfCommands, Map<String, Vehicle> vehicleMap) {
        for (int i = 0; i < numberOfCommands; i++) {
            String[] commandData = scanner.nextLine().split("\\s+");

            String commandName = commandData[0];
            String vehicleType = commandData[1];
            double argument = Double.parseDouble(commandData[2]);

            executeCommands(commandName, vehicleType, argument, vehicleMap);
        }
    }

    private static void executeCommands(String commandName, String vehicleType, double argument, Map<String, Vehicle> vehicleMap) {
        switch (commandName) {
            case "DriveEmpty":
                drive(vehicleType, argument, vehicleMap);
                break;
            case "Drive":
                driveAC(vehicleType,argument,vehicleMap);
                break;
            case "Refuel":
                try{
                    refuel(vehicleType, argument, vehicleMap);
                }catch (IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }
        }
    }

    private static void refuel(String vehicleType, double argument, Map<String, Vehicle> vehicleMap) {
        vehicleMap.get(vehicleType).refuel(argument);
    }
    private static void driveAC(String vehicleType, double argument, Map<String, Vehicle> vehicleMap) {
        System.out.println(vehicleMap.get(vehicleType).driveAC(argument));
    }

    private static void drive(String vehicleType, double argument, Map<String, Vehicle> vehicleMap) {
        System.out.println(vehicleMap.get(vehicleType).drive(argument));
    }
    private static void fillMap(Scanner scanner, Map<String, Vehicle> vehicleMap) {
        vehicleMap.put("Car",getVehicle(scanner));
        vehicleMap.put("Truck",getVehicle(scanner));
        vehicleMap.put("Bus",getVehicle(scanner));
    }

    private static Vehicle getVehicle(Scanner scanner) {
        String[] vehicleData = scanner.nextLine().split("\\s+");
        String vehicleType = vehicleData[0];
        double fuelAmount = Double.parseDouble(vehicleData[1]);
        double fuelConsumption = Double.parseDouble(vehicleData[2]);
        double tankCapacity = Double.parseDouble(vehicleData[3]);
        switch (vehicleType) {
            case "Car":
                return new Car(fuelAmount, fuelConsumption, tankCapacity);
            case "Truck":
                return new Truck(fuelAmount, fuelConsumption, tankCapacity);
            case "Bus":
                return new Bus(fuelAmount, fuelConsumption, tankCapacity);
            default:
                return null;
        }
    }
}
