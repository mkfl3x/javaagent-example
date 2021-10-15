package org.agent;

import javassist.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

public class ClassTransformer implements ClassFileTransformer {

    private static final String TARGET_CLASS = "org/application/Greeter";
    private static final String TARGET_METHOD = "getGreetingText";

    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) {
        if (className.equals(TARGET_CLASS)) {
            try {
                ClassPool pool = ClassPool.getDefault();
                CtClass targetClass = pool.makeClass(new ByteArrayInputStream(classfileBuffer));

                CtMethod targetMethod = targetClass.getDeclaredMethod(TARGET_METHOD);
                targetMethod.setBody("return \"Ciao!\";");

                return targetClass.toBytecode();
            } catch (IOException | CannotCompileException | NotFoundException e) {
                e.printStackTrace();
            }
        }
        return classfileBuffer;
    }
}
