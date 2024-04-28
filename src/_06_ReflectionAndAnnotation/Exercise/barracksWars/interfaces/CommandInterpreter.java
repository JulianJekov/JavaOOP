package _06_ReflectionAndAnnotation.Exercise.barracksWars.interfaces;

public interface CommandInterpreter {

	Executable interpretCommand(String[] data, String commandName);
}
