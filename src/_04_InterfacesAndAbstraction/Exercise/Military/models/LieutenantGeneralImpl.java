package _04_InterfacesAndAbstraction.Exercise.Military.models;

import _04_InterfacesAndAbstraction.Exercise.Military.interfaces.LieutenantGeneral;
import _04_InterfacesAndAbstraction.Exercise.Military.interfaces.Private;
import _04_InterfacesAndAbstraction.Exercise.Military.interfaces.Soldier;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LieutenantGeneralImpl extends PrivateImpl implements LieutenantGeneral {
    private List<Private> privates;

    public LieutenantGeneralImpl(int id, String firstName, String lastName, double salary) {
        super(id, firstName, lastName, salary);
        this.privates = new ArrayList<>();
    }

    public List<Private> getPrivates() {
        return privates;
    }

    @Override
    public void addPrivate(Private priv) {
        this.privates.add(priv);
       // privates.stream().sorted(Comparator.comparingInt(Soldier::getId).reversed());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Name: %s %s Id: %d Salary: %.2f%n",getFirstName(),getLastName(), getId(),getSalary()));
        sb.append("Privates:");
        privates.stream().sorted(Comparator.comparingInt(Soldier::getId).reversed()).forEach(s->{
            sb.append(System.lineSeparator());
            sb.append("  ");
            sb.append(String.format("Name: %s %s Id: %d Salary: %.2f"
                    ,s.getFirstName(),s.getLastName(),s.getId(),s.getSalary()));
        });
        return sb.toString().trim();
    }
}
