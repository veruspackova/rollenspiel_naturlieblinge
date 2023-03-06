package backend.artifacts.weapons.melee;

import backend.enums.Stat;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SwordTest {
    @Test
    void getWeaponProficiencyStat() {
        Sword sword = new Sword();
        assertEquals(Stat.STR, sword.getWeaponProficiencyStat());
    }

    @Test
    void getRequiredStrength() {
        Sword sword = new Sword();
        assertEquals(13, sword.getRequiredStrength());
    }

    @Test
    void rollDamage() {
        Sword sword = new Sword();
        int roll = sword.rollDamage();

        assertTrue(roll > 0);
        assertTrue(roll < 9);
    }
}