package com.sb4j.extensions;

import com.scriptbasic.api.BasicFunction;
import com.scriptbasic.api.ScriptBasicException;

public class SomeStringMethods {

    @BasicFunction(classification = com.scriptbasic.classification.String.class)
    public static String reverse(String s) throws ScriptBasicException {
        return new StringBuilder(s).reverse().toString();
    }
}
