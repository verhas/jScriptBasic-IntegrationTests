package com.sb4j.extensions;

import com.scriptbasic.spi.BasicArray;
import com.scriptbasic.api.BasicFunction;
import com.scriptbasic.api.ScriptBasicException;
import com.scriptbasic.classification.Math;

/**
 * Implement a function that returns a BasicArray of numbers between a to b.
 */
public class Range {

    @BasicFunction(classification = Math.class)
    public static BasicArray range(int start, int end) throws ScriptBasicException {
        BasicArray array = BasicArray.create();
        Integer[] rangeArray = new Integer[end - start + 1];
        array.setArray(rangeArray);
        for (int i = 0; i < rangeArray.length; i++) {
            array.set(i,i+start);
        }
        return array;
    }

}
