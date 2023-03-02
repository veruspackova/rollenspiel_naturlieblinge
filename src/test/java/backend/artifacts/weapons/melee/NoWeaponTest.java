package backend.artifacts.weapons.melee;

import backend.enums.Stat;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NoWeaponTest {
    @Test
    void getWeaponProficiencyStat() {
        NoWeapon noWeapon = new NoWeapon();
        assertEquals(Stat.STR, noWeapon.getWeaponProficiencyStat());
    }

    @Test
    void getRequiredStrength() {
        NoWeapon noWeapon = new NoWeapon();
        assertEquals(0, noWeapon.getRequiredStrength());
    }

    @Test
    void rollDamage() {
        NoWeapon noWeapon = new NoWeapon();
        assertEquals(1, noWeapon.rollDamage());
    }
}