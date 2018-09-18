package com.capgemini.hungergames.util;

public class FloatUtil {
    private static final float DEFAULT_ACCURACY = 0.00000001F;

    public static boolean isEqual(float num1, float num2) {
        return isEqual(num1, num2, DEFAULT_ACCURACY);
    }

    public static boolean isEqual(float num1, float num2, float accuracy) {
        return Math.abs(num1 - num2) < accuracy;
    }
}
