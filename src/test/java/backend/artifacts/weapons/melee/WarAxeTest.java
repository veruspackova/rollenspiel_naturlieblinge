package backend.artifacts.weapons.melee;

import backend.enums.Stat;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarAxeTest {
    @Test
    void getWeaponProficiencyStat() {
        WarAxe axe = new WarAxe();
        assertEquals(Stat.STR, axe.getWeaponProficiencyStat());
    }

    @Test
    void getRequiredStrength() {
        WarAxe axe = new WarAxe();
        assertEquals(0, axe.getRequiredStrength());
    }

    @Test
    void rollDamage() {
        WarAxe axe = new WarAxe();
        int roll = axe.rollDamage();
        assertTrue(roll > 0);
        assertTrue(roll < 7);
    }
}