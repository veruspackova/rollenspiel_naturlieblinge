package backend.character;

import backend.artifacts.items.Item;
import backend.artifacts.weapons.WeaponBase;
import backend.artifacts.weapons.melee.NoWeapon;
import backend.logic.Dice;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MonsterTest {

    @Test
    void testGetName() {
        Monster m = new Monster("Monster", 15, 13, 14, 12, 10, 10, 10, 10, new ArrayList<Item>(), new NoWeapon(),new ArrayList<WeaponBase>());
        assertEquals("Monster", m.getName());
    }

    @Test
    void testGetHitPoints() {
        Monster m = new Monster("Monster", 15, 13, 14, 12, 10, 10, 10, 10, new ArrayList<Item>(), new NoWeapon(),new ArrayList<WeaponBase>());
        assertEquals(12, m.getHitPoints());
    }

    @Test
    void testGetArmourClass() {
        Monster m = new Monster("Monster", 15, 13, 14, 12, 10, 10, 10, 10, new ArrayList<Item>(), new NoWeapon(),new ArrayList<WeaponBase>());
        assertEquals(10, m.getArmourClass());
    }

    @Test
    void testGetAttackDice() {
        Monster m = new Monster("Monster", 15, 13, 14, 12, 10, 10, 10, 10, new ArrayList<Item>(), new NoWeapon(),new ArrayList<WeaponBase>());
        assertEquals(new Dice(10).getDSize(), m.getAttackDice().getDSize());
    }
}
