package _04_InterfacesAndAbstraction.Exercise.Military.models;

import _04_InterfacesAndAbstraction.Exercise.Military.enumerations.Corp;
import _04_InterfacesAndAbstraction.Exercise.Military.interfaces.SpecialisedSoldier;

public class SpecialisedSoldierImpl extends PrivateImpl implements SpecialisedSoldier {
    private Corp corps;

    protected SpecialisedSoldierImpl(int id, String firstName, String lastName, double salary, Corp corps) {
        super(id, firstName, lastName, salary);
        this.corps = corps;
    }


    @Override
    public Corp getCorp() {
        return this.corps;
    }
}
