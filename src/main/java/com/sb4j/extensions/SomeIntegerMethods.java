package com.sb4j.extensions;

import com.scriptbasic.api.BasicFunction;
import com.scriptbasic.api.ScriptBasicException;

public class SomeIntegerMethods {

    @BasicFunction(classification = com.scriptbasic.classification.Math.class)
    public static long inc(long l) throws ScriptBasicException {
        return l+1;
    }
}
