package org.upstart.r1.util;

public class Math {

    public static int roundUp(int numerator, int denominator) {
        int roundedDown = numerator / denominator;
        int remainder = numerator % denominator;
        if(remainder != 0) {
            roundedDown++;
        }
        return roundedDown;

    }

    public static int roundDown(int numerator, int denominator) {
        return numerator / denominator;
    }
}
