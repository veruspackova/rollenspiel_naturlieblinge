package backend.artifacts.weapons.melee;

import backend.logic.TestDie;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DaggerTest {

    @Test
    void testRollDamage() {
        Dagger dagger = new Dagger(new TestDie(4));
        assertEquals(4, dagger.rollDamage());
    }
}