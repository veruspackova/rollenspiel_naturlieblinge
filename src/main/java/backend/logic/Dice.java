package backend.logic;

/**
 * Dice class
 * <p>
 * generate a Die and roll it<br>
 * Dice have variable sizes
 * </p>
 *
 * @author jonasmalsbenden
 */
public class Dice {
    protected int dSize;

    /**
     * Default constructor
     * (generates a Dice with size 10)
     */
    public Dice() {
        dSize = 10;
    }

    /**
     * Constructor
     * (generates a Dice with any size)
     */
    public Dice(int DSize) {
        dSize = DSize;
    }

    /**
     * Dice roll
     * (generates a random number between 0 and the Dice size)
     */
    public int roll() {
        return (int) (Math.random() * dSize) + 1;
    }

    public int getDSize() {
        return dSize;
    }
}
