package com.lp2.sisproject.util;

@SuppressWarnings("BooleanMethodIsAlwaysInverted")
public class NumberCheck {
    public static boolean isInt(String str) {
        try {
            Integer _ = Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isDouble(String str) {
        try {
            Double _ = Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
