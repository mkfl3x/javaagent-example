# javaagent-example
Example how to use java agent ('javassist' used)

### How to run it
1. Build .jar of **application** module (with `jar` Gradle task)
2. Build .jar of **agent** module (with `shadowJar` Gradle task)
3. Run it like that: `java -javaagent:agent.jar -jar application.jar`

### What happens
- Application uses method `getGreetingText()` of `Greeter` class which returns `"Hello!"` string.  
- Agent intercept loading of `Greeter` class and change `getGreetingText()` method body from `return "Hello!"` to `return "Ciao!"`.
