package _06_ReflectionAndAnnotation.Exercise.barracksWars;

import _06_ReflectionAndAnnotation.Exercise.barracksWars.core.commands.CommandInterpreterImpl;
import _06_ReflectionAndAnnotation.Exercise.barracksWars.interfaces.CommandInterpreter;
import _06_ReflectionAndAnnotation.Exercise.barracksWars.interfaces.Repository;
import _06_ReflectionAndAnnotation.Exercise.barracksWars.interfaces.Runnable;
import _06_ReflectionAndAnnotation.Exercise.barracksWars.interfaces.UnitFactory;
import _06_ReflectionAndAnnotation.Exercise.barracksWars.core.Engine;
import _06_ReflectionAndAnnotation.Exercise.barracksWars.core.factories.UnitFactoryImpl;
import _06_ReflectionAndAnnotation.Exercise.barracksWars.data.UnitRepository;

public class Main {

    public static void main(String[] args) {
        Repository repository = new UnitRepository();
        UnitFactory unitFactory = new UnitFactoryImpl();
        CommandInterpreter commandInterpreter = new CommandInterpreterImpl(repository, unitFactory);
        Runnable engine = new Engine(commandInterpreter);
        engine.run();
    }
}
