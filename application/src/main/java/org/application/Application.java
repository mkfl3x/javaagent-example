package org.application;

public class Application {

    private static final int NUMBER = 42;

    public static void main(String[] args) {
        System.out.println("[application] Hello, I am Application");
        printNumber(NUMBER);
    }

    private static void printNumber(int number) {
        System.out.println("[application] The number is " + number);
    }
}
