package backend.artifacts.weapons.agiele;

import backend.artifacts.weapons.agiele.Club;
import backend.enums.Stat;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ClubTest {
    @Test
    void getWeaponProficiencyStat() {
        Club club = new Club();
        assertEquals(Stat.STR, club.getWeaponProficiencyStat());
    }

    @Test
    void getRequiredStrength() {
        Club club = new Club();
        assertEquals(0, club.getRequiredStrength());
    }

    @Test
    void rollDamage() {
        Club club = new Club();
        int roll = club.rollDamage();

        assertTrue(roll > 0);
        assertTrue(roll < 7);
    }
}