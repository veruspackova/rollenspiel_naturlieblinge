package backend.artifacts.weapons.agiele;

import backend.artifacts.weapons.agiele.Spear;
import backend.enums.Stat;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpearTest {
    @Test
    void getWeaponProficiencyStat() {
        Spear spear = new Spear();
        assertEquals(Stat.DEX, spear.getWeaponProficiencyStat());
    }

    @Test
    void getRequiredStrength() {
        Spear spear = new Spear();
        assertEquals(0, spear.getRequiredStrength());
    }

    @Test
    void rollDamage() {
        Spear spear = new Spear();
        int roll = spear.rollDamage();

        assertTrue(roll > 0);
        assertTrue(roll < 7);
    }
}