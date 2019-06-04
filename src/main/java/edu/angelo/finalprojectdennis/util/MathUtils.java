package edu.angelo.finalprojectdennis.util;

import java.util.Random;

/**
 * Used to store all of our math utility based functions.
 * Created by Ryan Dennis (rdennis4) on 12/1/2018.
 */
public class MathUtils {

    /**
     * Our random instance.
     */
    private static final Random random = new Random();

    /**
     * Creates a random number from the min to the max.
     * @param minimum The min.
     * @param maximum The max.
     * @return The random number.
     */
    public static int random(int minimum, int maximum) {
        return random.nextInt((maximum - minimum) + 1) + minimum;
    }

}
