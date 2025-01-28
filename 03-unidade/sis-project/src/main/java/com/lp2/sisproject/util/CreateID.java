package com.lp2.sisproject.util;

/**
 * Utility class for generating unique IDs.
 */
public class CreateID {
    /**
     * Generates a unique ID using the current system time in milliseconds.
     *
     * @return a unique ID as a Long value
     */
    public static Long generateID() {
        return System.currentTimeMillis();
    }
}
