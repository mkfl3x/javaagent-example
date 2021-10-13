package org.agent;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

public class ClassTransformer implements ClassFileTransformer {

    private static final String TARGET_CLASS = "org/application/Application";

    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) {
        if(className.equals(TARGET_CLASS))
            System.out.println("[agent] I got Application class");
        return new byte[0];
    }
}

