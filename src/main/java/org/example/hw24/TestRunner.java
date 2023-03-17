package org.example.hw24;

import org.example.hw24.annotation.AfterSuite;
import org.example.hw24.annotation.BeforeSuite;
import org.example.hw24.annotation.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TestRunner {
    public static void start(Class<?> clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        List<Method> beforeSuiteMethods = new ArrayList<>();
        List<Method> afterSuiteMethods = new ArrayList<>();
        List<Method> testMethods = new ArrayList<>();

        // Add methods to corresponding lists
        for (Method method : methods) {
            if (method.isAnnotationPresent(BeforeSuite.class)) {
                beforeSuiteMethods.add(method);
            } else if (method.isAnnotationPresent(Test.class)) {
                testMethods.add(method);
            } else if (method.isAnnotationPresent(AfterSuite.class)) {
                afterSuiteMethods.add(method);
            }
        }

        // Check if there is only one BeforeSuite and AfterSuite method
        if (beforeSuiteMethods.size() > 1 || afterSuiteMethods.size() > 1) {
            throw new RuntimeException("There should be only one BeforeSuite and AfterSuite method");
        }

        // Sort test methods by priority
        testMethods.sort(Comparator.comparingInt(m -> m.getAnnotation(Test.class).priority()));

        // Invoke BeforeSuite method
        beforeSuiteMethods.stream()
                .findFirst()
                .ifPresent(TestRunner::invokeMethod);

        // Invoke test methods
        testMethods.forEach(TestRunner::invokeMethod);

        // Invoke AfterSuite method
        afterSuiteMethods.stream()
                .findFirst()
                .ifPresent(TestRunner::invokeMethod);
    }

    private static void invokeMethod(Method method) {
        try {
            method.setAccessible(true);
            method.invoke(null);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
