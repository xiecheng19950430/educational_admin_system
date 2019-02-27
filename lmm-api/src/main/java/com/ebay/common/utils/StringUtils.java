package com.ebay.common.utils;


/**
 * Created by ldm on 2017/4/25.
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {
    public static String fillAtStart(String obj, String start) {
        if (obj.startsWith(start))
            return obj;
        return start + obj;
    }

    public static String fillAtEnd(String obj, String end) {
        if (obj.startsWith(end))
            return obj;
        return obj + end;
    }

    public static String fillAtStartAndEnd(String obj, String fix) {
        if (!obj.startsWith(fix))
            obj = fix + obj;
        if (!obj.endsWith(fix))
            obj = obj + fix;
        return obj;
    }
}
