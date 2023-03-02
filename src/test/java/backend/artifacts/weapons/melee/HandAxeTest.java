package backend.artifacts.weapons.melee;

import backend.enums.Stat;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HandAxeTest {

    @Test
    void getWeaponProficiencyStat() {
        HandAxe axe = new HandAxe();
        assertEquals(Stat.NONE, axe.getWeaponProficiencyStat());
    }

    @Test
    void getRequiredStrength() {
        HandAxe axe = new HandAxe();
        assertEquals(0, axe.getRequiredStrength());
    }

    @Test
    void rollDamage() {
        HandAxe axe = new HandAxe();
        int roll = axe.rollDamage();

        assertTrue(roll > 0);
        assertTrue(roll < 5);
    }
}