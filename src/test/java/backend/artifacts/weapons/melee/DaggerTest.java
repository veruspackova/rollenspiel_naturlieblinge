package backend.artifacts.weapons.melee;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DaggerTest {

    @Test
    void testRollDamage() {
        Dagger dagger = new Dagger();
        assertTrue(dagger.rollDamage() > 0);
        assertTrue(dagger.rollDamage() < 5);
    }
}