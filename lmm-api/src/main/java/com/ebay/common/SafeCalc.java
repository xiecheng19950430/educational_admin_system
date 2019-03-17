package com.ebay.common;

import java.math.BigDecimal;

/**
 * @author: jf <for1988@126.com>
 * @date: 2016/12/20
 */
public class SafeCalc {

    public static double add(double d1, double d2) {
        BigDecimal b1 = new BigDecimal(Double.toString(d1));
        BigDecimal b2 = new BigDecimal(Double.toString(d2));
        return b1.add(b2).doubleValue();
    }

    public static double multiply(double d1, double d2) {
        BigDecimal b1 = new BigDecimal(Double.toString(d1));
        BigDecimal b2 = new BigDecimal(Double.toString(d2));
        return b1.multiply(b2).doubleValue();
    }

    public static double divide(double d1, double d2) {
        BigDecimal b1 = new BigDecimal(Double.toString(d1));
        BigDecimal b2 = new BigDecimal(Double.toString(d2));
        return b1.divide(b2).doubleValue();
    }

    public static double subtract(double d1, double d2) {
        BigDecimal b1 = new BigDecimal(Double.toString(d1));
        BigDecimal b2 = new BigDecimal(Double.toString(d2));
        return b1.subtract(b2).doubleValue();
    }

    public static double add(double d1, double d2, int scaleLen) {
        return scale(add(d1, d2), scaleLen);
    }

    public static double multiply(double d1, double d2, int scaleLen) {
        return scale(multiply(d1, d2), scaleLen);
    }

    public static double divide(double d1, double d2, int scaleLen) {
        return scale(divide(d1, d2), scaleLen);
    }

    public static double subtract(double d1, double d2, int scaleLen) {
        return scale(subtract(d1, d2), scaleLen);
    }

    public static double scale(double d, int len) {
        BigDecimal bg = new BigDecimal(d);
        return bg.setScale(len, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static int scaleInt(double d, int len) {
        BigDecimal bg = new BigDecimal(d);
        return bg.setScale(len, BigDecimal.ROUND_HALF_UP).intValue();
    }

    public static int toInt(double d) {
        BigDecimal bg = new BigDecimal(d);
        return bg.setScale(0, BigDecimal.ROUND_DOWN).intValue();
    }
}
