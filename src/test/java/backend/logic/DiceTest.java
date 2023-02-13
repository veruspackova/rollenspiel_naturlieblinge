package backend.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiceTest {

    @Test
    void diceSizetoLow() {
        assertThrows(IllegalArgumentException.class,
                () ->{
                    new Dice(5);
                }
        );
    }

    @Test
    void rollTest(){
        Dice dice = new Dice(20);
        assertTrue(-1 < dice.roll());
        assertTrue(dice.roll() < 21);
    }
}