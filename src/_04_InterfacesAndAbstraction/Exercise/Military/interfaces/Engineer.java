package _04_InterfacesAndAbstraction.Exercise.Military.interfaces;

import java.util.Collection;

public interface Engineer {
    void addRepair(Repair repair);
    Collection<Repair> getRepairs();
}
