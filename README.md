# javaagent-example
Example how to change one method of application runned with javaagent (javassist used)

### How to run
1. Build .jar of **application** module (with `jar` Gradle task)
2. Build .jar of **agent** module (with `shadowJar` Gradle task)
3. Run it like that: `java -javaagent:agent.jar -jar application.jar`

### What happens
Application uses method `getGreetingText()` which returns `"Hello!"` string.  
Agent intercept loading of `Greeter` class and change `getGreetingText()` method body for return `"Ciao!"` string.
