package backend.logic;
/**
 * Dice class
 * <p>
 *     generate a Dice and roll it<br>
 *     Dices have variable sizes
 * </p>
 *
 * @author jonasmalsbenden
 */
public class Dice {
    protected int dSize ;
    /**
     * Default constructor
     * (generates a Dice with size 10)
     */
    public Dice(){
        dSize = 10;
    }
    /**
     * Constructor
     * (generates a Dice with any size)
     */
    public Dice(int DSize){
        if(DSize < 6){
            throw new IllegalArgumentException("Invalide dice size");
        }
        dSize = DSize;
    }
    /**
     * Dice roll
     * (generates a random number between 0 and the Dice size)
     */
    public int roll(){
        int result = (int) (Math.random()*dSize);
        return result;
    }

    public int getDSize() {
        return dSize;
    }
}
