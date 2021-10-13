package org.agent;

import java.lang.instrument.Instrumentation;

public class Agent {

    public static void premain(String agentArgs, Instrumentation instrumentation) {
        System.out.println("[agent] Hello, I Agent");
        instrumentation.addTransformer(new ClassTransformer());
    }
}
