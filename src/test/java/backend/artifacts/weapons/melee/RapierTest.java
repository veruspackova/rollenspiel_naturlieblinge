package backend.artifacts.weapons.melee;

import backend.enums.Stat;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RapierTest {
    @Test
    void getWeaponProficiencyStat() {
        Rapier rapier = new Rapier();
        assertEquals(Stat.DEX, rapier.getWeaponProficiencyStat());
    }

    @Test
    void getRequiredStrength() {
        Rapier rapier = new Rapier();
        assertEquals(0, rapier.getRequiredStrength());
    }

    @Test
    void rollDamage() {
        Rapier rapier = new Rapier();
        int roll = rapier.rollDamage();

        assertTrue(roll > 0);
        assertTrue(roll < 7);
    }
}