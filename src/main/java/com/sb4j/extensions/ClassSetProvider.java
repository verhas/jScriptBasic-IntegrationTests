package com.sb4j.extensions;

import java.util.Set;
import java.util.stream.Stream;

public class ClassSetProvider implements com.scriptbasic.spi.ClassSetProvider {

    @Override
    public Set<Class<?>> provide() {
        return Set.of(SomeStringMethods.class,SomeIntegerMethods.class);
    }
}
