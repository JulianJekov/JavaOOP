package _01_WorkingWithAbstraction.Lab.StudentSystem;

import java.util.HashMap;
import java.util.Map;

public class StudentSystem {
    private Map<String, Student> students;

    public StudentSystem() {
        this.students = new HashMap<>();
    }

    public void parseCommand(String[] commandData) {
        String commandName = commandData[0];
        if (commandName.equals("Create")) {
            createStudent(commandData);
        } else if (commandData[0].equals("Show")) {
            showStudent(commandData[1]);
        }
    }

    private void showStudent(String name) {
        if (students.containsKey(name)) {
            Student student = students.get(name);
            StringBuilder output = new StringBuilder(student.toString());
            assessStudent(student, output);
            System.out.println(output);
        }
    }

    private void assessStudent(Student student, StringBuilder output) {
        if (student.getGrade() >= 5.00) {
            output.append(" Excellent student.");
        } else if (student.getGrade() < 5.00 && student.getGrade() >= 3.50) {
            output.append(" Average student.");
        } else {
            output.append(" Very nice person.");
        }
    }

    private void createStudent(String[] commandData) {
        String name = commandData[1];
        int age = Integer.parseInt(commandData[2]);
        double grade = Double.parseDouble(commandData[3]);
        Student student = new Student(name, age, grade);
        students.putIfAbsent(name, student);
    }
}
