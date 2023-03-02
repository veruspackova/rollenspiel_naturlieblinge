package backend.artifacts.weapons.melee;

import backend.enums.Stat;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NoneTest {
    @Test
    void getWeaponProficiencyStat() {
        None noWeapon = new None();
        assertEquals(Stat.STR, noWeapon.getWeaponProficiencyStat());
    }

    @Test
    void getRequiredStrength() {
        None noWeapon = new None();
        assertEquals(0, noWeapon.getRequiredStrength());
    }

    @Test
    void rollDamage() {
        None noWeapon = new None();
        assertEquals(1, noWeapon.rollDamage());
    }
}