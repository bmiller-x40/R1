package org.upstart.r1.util;

import static java.lang.Math.random;

public class Random {

    public static int rand(int n) {
        return (int) (random() * n);
    }

    public static int rand(int min, int max) {
        return (int) (random() * (max-min)) + min;
    }
    
    public static int randDice(int numDice, int sides) {
        int total = 0;
        
        for(int i=0;i<numDice;i++) {
            total += rand(sides) + 1;
        }
        
        return total;
    }
    
    public static int randPercent() {
        return rand(100) + 1;
    }
    
    public static int openEnded() {
        int roll = randPercent();

        if(roll < 95) {
            return roll;
        }
        
        return openEnded() + roll;
    }
}
