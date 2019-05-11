package com.evan.javaaaaaaaaa.jvm.dynamic_type;

import java.lang.reflect.Constructor;

public class ConstructorScope {

    class NoScope{}

    public class PublicScope{}

    private class PrivateScope{}

    protected class ProtectedScope{}

    public String getDefaultConstructorScope(Class cls){
        int modifiers = -1;
        try {
            Constructor constructor = cls.getDeclaredConstructor(ConstructorScope.class);
            /**
             * modifier代表的意思，-> Modifier.java
             */
            modifiers = constructor.getModifiers();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return modifiers + "";
    }

    public static void main(String[] args) {
        ConstructorScope constructorScope = new ConstructorScope();
        System.out.println("NoScope" + constructorScope.getDefaultConstructorScope(NoScope.class));
        System.out.println("PublicScope" + constructorScope.getDefaultConstructorScope(PublicScope.class));
        System.out.println("PrivateScope" + constructorScope.getDefaultConstructorScope(PrivateScope.class));
        System.out.println("ProtectedScope" + constructorScope.getDefaultConstructorScope(ProtectedScope.class));
    }
}
