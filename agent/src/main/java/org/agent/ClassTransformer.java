package org.agent;

import javassist.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

public class ClassTransformer implements ClassFileTransformer {

    private static final String TARGET_CLASS = "org/application/Application";
    private static final String TARGET_FIELD = "NUMBER";

    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) {
        if (className.equals(TARGET_CLASS)) {
            System.out.println("[agent] I got Application class");
            try {
                // create class pool and add target class
                ClassPool pool = ClassPool.getDefault();
                CtClass targetClass = pool.makeClass(new ByteArrayInputStream(classfileBuffer));

                // get target field and it's properties
                CtField targetField = targetClass.getField(TARGET_FIELD);
                String fieldModifiers = Modifier.toString(targetField.getModifiers());
                int fieldValue = Integer.parseInt(targetField.getConstantValue().toString());

                // remove target field for creating new one with updated value
                targetClass.removeField(targetField);

                // create updated field
                CtField updatedField = CtField.make(
                        String.format("%s int %s = %s;", fieldModifiers, TARGET_FIELD, fieldValue / 2),
                        targetClass
                );
                targetClass.addField(updatedField);

                // return updated class
                return targetClass.toBytecode();
            } catch (IOException | CannotCompileException | NotFoundException e) {
                e.printStackTrace();
            }
        }
        return classfileBuffer;
    }
}
