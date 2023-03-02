package backend.logic;

/**
 * Dice class
 * <p>
 * generate a Die and roll it<br>
 * Dice have variable sizes
 * </p>
 */
public class Dice {
    protected int dSize;

    /**
     * Default constructor
     * (generates a Dice-Object with size 10)
     */
    public Dice() {
        dSize = 10;
    }

    /**
     * Constructor
     * (generates a Dice-Object with the given size)
     */
    public Dice(int DSize) {
        if (DSize < 1) {
            throw new IllegalArgumentException("Invalid dice size");
        }
        dSize = DSize;
    }

    /**
     * Dice roll
     * (generates a random number between 1 and the Dice size)
     */
    public int roll() {
        return (int) (Math.random() * dSize) + 1;
    }

    public int getDSize() {
        return dSize;
    }
}
