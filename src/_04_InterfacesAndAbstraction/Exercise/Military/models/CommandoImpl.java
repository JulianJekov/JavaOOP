package _04_InterfacesAndAbstraction.Exercise.Military.models;

import _04_InterfacesAndAbstraction.Exercise.Military.enumerations.Corp;


import java.util.*;

public class CommandoImpl extends SpecialisedSoldierImpl implements Commando{
    private List<Mission> missions;

    public CommandoImpl(int id, String firstName, String lastName, double salary, Corp corps) {
        super(id, firstName, lastName, salary, corps);
        this.missions = new ArrayList<>();
    }


    @Override
    public void addMission(Mission mission) {
        this.missions.add(mission);
    }

    @Override
    public Collection<Mission> getMissions() {
        return this.missions;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Name: %s %s Id: %d Salary: %.2f%n",getFirstName(),getLastName(), getId(),getSalary()));
        sb.append("Corps: ").append(getCorp().toString()).append(System.lineSeparator());
        sb.append("Missions:");
        for (Mission mission : missions) {
            sb.append(System.lineSeparator());
            sb.append(String.format("  Code Name: %s State: %s",mission.getCodeName(), mission.getState().toString()));
        }
        return sb.toString().trim();
    }
}
