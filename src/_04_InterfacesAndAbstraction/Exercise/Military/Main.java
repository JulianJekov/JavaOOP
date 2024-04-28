package _04_InterfacesAndAbstraction.Exercise.Military;

import _04_InterfacesAndAbstraction.Exercise.Military.enumerations.Corp;
import _04_InterfacesAndAbstraction.Exercise.Military.enumerations.State;
import _04_InterfacesAndAbstraction.Exercise.Military.interfaces.Private;
import _04_InterfacesAndAbstraction.Exercise.Military.interfaces.Soldier;
import _04_InterfacesAndAbstraction.Exercise.Military.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Soldier> soldiers = new ArrayList<>();

        String line = scanner.nextLine();

        while (!"End".equals(line)) {
            String[] tokens = line.split("\\s+");
            int id = Integer.parseInt(tokens[1]);
            String firstName = tokens[2];
            String lastName = tokens[3];

            Soldier soldier = null;
            switch (tokens[0]) {
                case "Private":
                    soldier = new PrivateImpl(id, firstName, lastName, Double.parseDouble(tokens[4]));
                    break;
                case "LieutenantGeneral":
                    soldier = getLieutenant(soldiers, tokens, id, firstName, lastName);
                    break;
                case "Engineer": {
                    soldier = getEngineer(tokens, id, firstName, lastName);
                }
                break;
                case "Commando":
                    soldier = getCommando(tokens, id, firstName, lastName);
                    break;
                case "Spy":
                    soldier = new SpyImpl(id, firstName, lastName, tokens[4]);
                    break;

            }
            if (soldier != null) {
                soldiers.add(soldier);
            }
            line = scanner.nextLine();
        }
        for (Soldier soldier : soldiers) {
            System.out.println(soldier);
        }
    }

    private static Soldier getCommando(String[] tokens, int id, String firstName, String lastName) {
        CommandoImpl soldier = null;
        String corp = tokens[5];
        if (Corp.Airforces.toString().equals(corp) || Corp.Marines.toString().equals(corp)) {
            soldier = new CommandoImpl(id, firstName, lastName, Double.parseDouble(tokens[4]), Corp.valueOf(corp));
            if (tokens.length > 6) {
                for (int i = 6; i < tokens.length; i += 2) {
                    String missionName = tokens[i];
                    String missionState = tokens[i + 1];
                    if (State.inProgress.toString().equals(missionState) || State.finished.toString().equals(missionState)) {
                        Mission mission = new Mission(missionName, State.valueOf(missionState));
                        soldier.addMission(mission);
                    }
                }
            }
        }
        return soldier;
    }

    private static Soldier getLieutenant(List<Soldier> soldiers, String[] tokens, int id, String firstName, String lastName) {
        LieutenantGeneralImpl soldier;
        soldier = new LieutenantGeneralImpl(id, firstName, lastName, Double.parseDouble(tokens[4]));
        if (tokens.length > 5) {
            for (int i = 5; i < tokens.length; i++) {
                int currentId = Integer.parseInt(tokens[i]);
                for (Soldier priv : soldiers) {
                    if (priv.getId() == currentId && priv instanceof Private) {
                        soldier.addPrivate((Private) priv);
                    }
                }
            }
        }
        return soldier;
    }

    private static Soldier getEngineer(String[] tokens, int id, String firstName, String lastName) {
        EngineerImpl soldier = null;
        String corp = tokens[5];
        if (Corp.Airforces.toString().equals(corp) || Corp.Marines.toString().equals(corp)) {
            soldier = new EngineerImpl(id, firstName, lastName, Double.parseDouble(tokens[4]), Corp.valueOf(corp));
            if (tokens.length > 6) {
                for (int i = 6; i < tokens.length; i += 2) {
                    String repairName = tokens[i];
                    int workHours = Integer.parseInt(tokens[i + 1]);
                    RepairImpl repair = new RepairImpl(repairName, workHours);
                    soldier.addRepair(repair);
                }
            }
        }
        return soldier;
    }
}
