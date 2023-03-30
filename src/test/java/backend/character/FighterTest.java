package backend.character;

import backend.artifacts.items.Item;
import backend.artifacts.weapons.WeaponBase;
import backend.artifacts.weapons.melee.NoWeapon;
import backend.enums.Race;
import backend.logic.Dice;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class FighterTest {

    @Test
    void testGetRace() {
        Fighter f = new Fighter(Race.HUM, "Fighter", 15, 13, 14, 12, 10);
        assertEquals(Race.HUM, f.getRace());
    }

    @Test
    void testGetName() {
        Fighter f = new Fighter(Race.HUM, "Fighter", 15, 13, 14, 12, 10);
        assertEquals("Fighter", f.getName());
    }

    @Test
    void testGetHitPoints() {
        Fighter f = new Fighter(Race.HUM, "Fighter", 15, 13, 14, 12, 10);
        assertEquals(12, f.getHitPoints());
    }

    @Test
    void testGetArmourClass() {
        Fighter f = new Fighter(Race.HUM, "Fighter", 15, 13, 14, 12, 10);
        assertEquals(13, f.getArmourClass());
    }

    @Test
    void testGetAttackDice() {
        Fighter f = new Fighter(Race.HUM, "Fighter", 15, 13, 14, 12, 10);
        assertEquals(new Dice(10).getDSize(), f.getAttackDice().getDSize());
    }
}