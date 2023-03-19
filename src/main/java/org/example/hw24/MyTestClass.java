package org.example.hw24;

import org.example.hw24.annotation.AfterSuite;
import org.example.hw24.annotation.BeforeSuite;
import org.example.hw24.annotation.Test;

public class MyTestClass {
    @BeforeSuite
    public static void beforeSuiteMethod() {
        System.out.println("Before Suite Method");
    }

    @Test(priority = 5)
    public static void testMethod1() {
        System.out.println("Test Method 1");
    }

    @Test(priority = 3)
    public static void testMethod2() {
        System.out.println("Test Method 2");
    }

    @Test(priority = 7)
    public static void testMethod3() {
        System.out.println("Test Method 3");
    }

    @AfterSuite
    public static void afterSuiteMethod() {
        System.out.println("After Suite Method");
    }
}

