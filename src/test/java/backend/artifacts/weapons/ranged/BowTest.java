package backend.artifacts.weapons.ranged;

import backend.enums.Stat;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BowTest {
    @Test
    void getWeaponProficiencyStat() {
        Bow bow = new Bow ();
        assertEquals(Stat.DEX, bow.getWeaponProficiencyStat());
    }

    @Test
    void getRequiredStrength() {
        Bow bow = new Bow();
        assertEquals(0, bow.getRequiredStrength());
    }

    @Test
    void rollDamage() {
        Bow bow = new Bow();
        int roll = bow.rollDamage();

        assertTrue(roll > 0);
        assertTrue(roll < 9);
    }

}