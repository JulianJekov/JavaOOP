package _02_Encapsulation.Exercise.P05FootballTeamGeneratorEncapsulationExercise;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        Map<String, Team> teams = new LinkedHashMap<>();

        while (!"END".equals(input)) {
            try {
                String[] commandParts = input.split(";");
                String commandName = commandParts[0];
                String teamName = commandParts[1];

                switch (commandName) {
                    case "Team":
                        createTeam(teams, teamName);
                        break;
                    case "Add":
                        addPlayer(teams, commandParts, teamName);
                        break;
                    case "Remove":
                        removePlayer(teams.get(teamName), commandParts[2]);
                        break;
                    case "Rating":
                        overallRating(teams, teamName);
                }
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
            input = scanner.nextLine();
        }

    }

    private static void overallRating(Map<String, Team> teams, String teamName) {
        if (!teams.containsKey(teamName)) {
            System.out.printf("Team %s does not exist.%n", teamName);
        } else {
            System.out.printf("%s - %d%n", teamName, Math.round(teams.get(teamName).getRating()));
        }
    }

    private static void removePlayer(Team team, String PlayerToRemove) {
        team.removePlayer(PlayerToRemove);
    }

    private static void createTeam(Map<String, Team> teams, String teamName) {
        Team team = new Team(teamName);
        teams.put(teamName, team);
    }

    private static void addPlayer(Map<String, Team> teams, String[] commandParts, String teamName) {
        String playerName = commandParts[2];
        int endurance = Integer.parseInt(commandParts[3]);
        int sprint = Integer.parseInt(commandParts[4]);
        int dribble = Integer.parseInt(commandParts[5]);
        int passing = Integer.parseInt(commandParts[6]);
        int shooting = Integer.parseInt(commandParts[7]);
        if (!teams.containsKey(teamName)) {
            System.out.printf("Team %s does not exist.%n", teamName);
        } else {
            Player player = new Player(playerName, endurance, sprint, dribble, passing, shooting);
            teams.get(teamName).addPlayer(player);
        }
    }
}
