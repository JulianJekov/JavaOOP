package _06_ReflectionAndAnnotation.Exercise.barracksWars.core.commands;

import _06_ReflectionAndAnnotation.Exercise.barracksWars.annotations.Inject;
import _06_ReflectionAndAnnotation.Exercise.barracksWars.interfaces.Repository;
import _06_ReflectionAndAnnotation.Exercise.barracksWars.interfaces.Unit;
import _06_ReflectionAndAnnotation.Exercise.barracksWars.interfaces.UnitFactory;

public class AddCommand extends Command {
    @Inject
    private Repository repository;
    @Inject
    private UnitFactory unitFactory;

    public AddCommand(String[] data) {
        super(data);
    }

    @Override
    public String execute() {
        String unitType = getData()[1];
        Unit unitToAdd = unitFactory.createUnit(unitType);
        repository.addUnit(unitToAdd);
        String output = unitType + " added!";
        return output;
    }
}
