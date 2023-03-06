package backend.artifacts.weapons.ranged;

import backend.enums.Stat;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DartTest {

    @Test
    void getWeaponProficiencyStat() {
        Dart dart = new Dart ();
        assertEquals(Stat.DEX, dart.getWeaponProficiencyStat());
    }

    @Test
    void getRequiredStrength() {
        Dart dart = new Dart();
        assertEquals(0, dart.getRequiredStrength());
    }
    @Test
    void rollDamage() {
        Dart dart = new Dart();
        int roll = dart.rollDamage();

        assertTrue(roll > 0);
        assertTrue(roll < 5);
    }


}