package backend.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DiceTest {

    @Test
    void diceSizeTooLow() {
        assertThrows(IllegalArgumentException.class, () -> new Dice(0));
        assertThrows(IllegalArgumentException.class, () -> new Dice(-1));
    }

    @Test
    void testRoll() {
        Dice dice = new Dice(20);
        assertTrue(dice.roll() > 0);
        assertTrue(dice.roll() < 21);
    }
}