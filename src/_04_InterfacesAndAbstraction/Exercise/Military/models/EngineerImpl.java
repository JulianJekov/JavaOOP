package _04_InterfacesAndAbstraction.Exercise.Military.models;

import _04_InterfacesAndAbstraction.Exercise.Military.enumerations.Corp;
import _04_InterfacesAndAbstraction.Exercise.Military.interfaces.Engineer;
import _04_InterfacesAndAbstraction.Exercise.Military.interfaces.Repair;

import java.util.*;

public class EngineerImpl extends SpecialisedSoldierImpl implements Engineer {
    private List<Repair> repairs;

    public EngineerImpl(int id, String firstName, String lastName, double salary, Corp corps) {
        super(id, firstName, lastName, salary, corps);

        this.repairs = new ArrayList<>();
    }

    @Override
    public void addRepair(Repair repair) {
        this.repairs.add(repair);
    }

    @Override
    public Collection<Repair> getRepairs() {
        return this.repairs;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Name: %s %s Id: %d Salary: %.2f%n",getFirstName(),getLastName(), getId(),getSalary()));
        sb.append("Corps: ").append(getCorp().toString()).append(System.lineSeparator());
        sb.append("Repairs:");
        for (Repair repair : repairs) {
            sb.append(System.lineSeparator());
            sb.append(String.format("  Part Name: %s Hours Worked: %d",repair.getName(),repair.getHoursWorked()));
        }
        return sb.toString().trim();
    }
}
