package com.ebay.common.utils;

public class ArrayUtils extends org.apache.commons.lang3.ArrayUtils {

    public static String[] removeAllElement(String[] strings,String element) {
        int index = indexOf(strings, element);
        while (index > -1) {
            strings = index == -1 ? clone(strings) : remove(strings, index);
            index = indexOf(strings, element);
        }
        return strings;
    }
}
