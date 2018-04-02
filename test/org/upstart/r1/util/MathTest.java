package org.upstart.r1.util;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.upstart.r1.util.Math.roundDown;
import static org.upstart.r1.util.Math.roundUp;

public class MathTest {

    @Test
    public void testRoundUp() {
        assertEquals(roundUp(4,2),2);
        assertEquals(roundUp(5,2),3);
        assertEquals(roundUp(6,2),3);

        assertEquals(roundUp(11, 4), 3);
        assertEquals(roundUp(12, 4), 3);
        assertEquals(roundUp(13, 4), 4);
    }

    @Test
    public void testRoundDown() {
        assertEquals(roundDown(4, 2), 2);
        assertEquals(roundDown(5, 2), 2);
        assertEquals(roundDown(6, 2), 3);

        assertEquals(roundDown(11, 4), 2);
        assertEquals(roundDown(12, 4), 3);
        assertEquals(roundDown(13, 4), 3);
    }
}
