package backend.artifacts.weapons.agiele;

import backend.artifacts.weapons.agiele.Dagger;
import backend.enums.Stat;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DaggerTest {

    @Test
    void testRollDamage() {
        Dagger dagger = new Dagger();
        assertTrue(dagger.rollDamage() > 0);
        assertTrue(dagger.rollDamage() < 5);
    }

    @Test
    void getWeaponProficiencyStat() {
        Dagger dagger = new Dagger();
        assertEquals(Stat.DEX, dagger.getWeaponProficiencyStat());
    }

    @Test
    void getRequiredStrength() {
        Dagger dagger = new Dagger();
        assertEquals(0, dagger.getRequiredStrength());
    }
}