package backend.character;

import backend.artifacts.items.Item;
import backend.enums.Race;
import backend.enums.Stat;
import backend.logic.Dice;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ThiefTest {

    @Test
    void testGetRace() {
        Thief t = new Thief(Race.HOB, "Thief", 10, 16, 13, 14, 10, new ArrayList<Item>());
        assertEquals(Race.HOB, t.getRace());
    }

    @Test
    void testGetName() {
        Thief t = new Thief(Race.HOB, "Thief", 10, 16, 13, 14, 10, new ArrayList<Item>());
        assertEquals("Thief", t.getName());
    }

    @Test
    void testSetHitPoints() {
        Thief t = new Thief(Race.HOB, "Thief", 10, 16, 13, 14, 10, new ArrayList<Item>());
        assertEquals(10, t.getHitPoints());

        t.setHitPoints(0);
        assertEquals(0, t.getHitPoints());
    }

    @Test
    void testSetArmourClass() {
        Thief t = new Thief(Race.HOB, "Thief", 10, 16, 13, 14, 10, new ArrayList<Item>());
        assertEquals(10, t.getArmourClass());

        t.setArmourClass(20);
        assertEquals(20, t.getArmourClass());
    }

    @Test
    void testSetHitDiceAvailable() {
        Thief t = new Thief(Race.HOB, "Thief", 10, 16, 13, 14, 10, new ArrayList<Item>());
        assertEquals(1, t.getHitDiceAvailable());

        t.setHitDiceAvailable(4);
        assertEquals(4, t.getHitDiceAvailable());
    }

    @Test
    void testGetStatModifier() {
        Thief t = new Thief(Race.HOB, "Thief", 10, 16, 13, 14, 10, new ArrayList<Item>());
        assertEquals(4, t.getStatModifier(Stat.DEX));

        t.setDexterity(8);
        assertEquals(-1, t.getStatModifier(Stat.DEX));

        t.setDexterity(10);
        assertEquals(0, t.getStatModifier(Stat.DEX));

        t.setDexterity(12);
        assertEquals(1, t.getStatModifier(Stat.DEX));
    }

    @Test
    void testSetStat() {
        Thief t = new Thief(Race.HOB, "Thief", 10, 16, 13, 14, 10, new ArrayList<Item>());
        assertEquals(18, t.getStat(Stat.DEX));

        t.setStat(Stat.DEX, 10);
        assertEquals(10, t.getStat(Stat.DEX));
    }

    @Test
    void testSetAttackDice() {
        Thief t = new Thief(Race.HOB, "Thief", 10, 16, 13, 14, 10, new ArrayList<Item>());
        assertEquals(8, t.getAttackDice().getDSize());

        t.setAttackDice(new Dice(12));
        assertEquals(12, t.getAttackDice().getDSize());
    }

    @Test
    void testSetHitDice() {
        Thief t = new Thief(Race.HOB, "Thief", 10, 16, 13, 14, 10, new ArrayList<Item>());
        assertEquals(8, t.getHitDice().getDSize());

        t.setHitDice(new Dice(12));
        assertEquals(12, t.getHitDice().getDSize());
    }
}