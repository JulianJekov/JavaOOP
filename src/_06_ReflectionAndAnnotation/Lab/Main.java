package _06_ReflectionAndAnnotation.Lab;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {
        /*P01
        Class clazz = Reflection.class;
        // Class clazz2 = Class.forName("ReflectionAndAnnotation.Lab");
        System.out.println(clazz);
        System.out.println(clazz.getSuperclass());
        Class[] interfaces = clazz.getInterfaces();
        Arrays.stream(interfaces).forEach(System.out::println);
        // Reflection reflection = clazz.newInstance(); Deprecated since Java 9
        Reflection reflection = (Reflection) clazz.getDeclaredConstructor().newInstance();
        System.out.println(reflection);
         */


        /* P02
        Class clazz = Reflection.class;
        Method[] methods = clazz.getDeclaredMethods();

        Arrays.stream(methods)
                .filter(method -> method.getName().startsWith("get") && method.getParameterCount() == 0)
                .sorted(Comparator.comparing(Method::getName))
                .forEach(method -> System.out.printf("%s will return class %s%n", method.getName(), method.getReturnType().getName()));
        Arrays.stream(methods)
                .filter(method -> method.getName().startsWith("set") && method.getParameterCount() == 1)
                .sorted(Comparator.comparing(Method::getName))
                .forEach(method ->
                        System.out.printf("%s and will set field of class %s%n",method.getName(), method.getParameterTypes()[0].getName())) ;

        */

        Class clazz = Reflection.class;
        Field[] fields = clazz.getDeclaredFields();
        Arrays.stream(fields)
                .filter(field -> !Modifier.isPrivate(field.getModifiers()))
                .sorted(Comparator.comparing(Field::getName))
                .forEach(field -> System.out.printf("%s must be private!%n",field.getName()));

        Method[] methods = clazz.getDeclaredMethods();
        Arrays.stream(methods)
                .filter(method -> method.getName().startsWith("get") && method.getParameterCount() == 0)
                .filter(method -> !Modifier.isPublic(method.getModifiers()))
                .sorted(Comparator.comparing(Method::getName))
                .forEach(method -> System.out.printf("%s have to be public!%n",method.getName()));

        Arrays.stream(methods)
                .filter(method -> method.getName().startsWith("set") && method.getParameterCount() == 1)
                .filter(method -> !Modifier.isPrivate(method.getModifiers()))
                .sorted(Comparator.comparing(Method::getName))
                .forEach(method -> System.out.printf("%s have to be private!%n",method.getName()));
    }
}
