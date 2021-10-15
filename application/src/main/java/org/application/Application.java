package org.application;

class Greeter {

    public String getGreetingText() {
        return "Hello!";
    }
}

public class Application {

    public static void main(String[] args) {
        System.out.println(new Greeter().getGreetingText() + " I am application");
    }
}
