package backend.character;

import backend.enums.Race;
import backend.enums.Spells;
import backend.enums.Stat;
import backend.logic.Dice;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WizardTest {

    @org.junit.jupiter.api.Test
    void testGetRace() {
        Wizard w = new Wizard(Race.ELF, "Wizard", 10, 15, 12, 15, 10);
        assertEquals(Race.ELF, w.getRace());
    }

    @org.junit.jupiter.api.Test
    void testGetName() {
        Wizard w = new Wizard(Race.ELF, "Wizard", 10, 15, 12, 15, 10);
        assertEquals("Wizard", w.getName());
    }

    @org.junit.jupiter.api.Test
    void testGetHitPoints() {
        Wizard w = new Wizard(Race.ELF, "Wizard", 10, 15, 12, 15, 10);
        assertEquals(9, w.getHitPoints());

        Wizard w2 = new Wizard(Race.ELF, "Wizard", 10, 15, 10, 15, 10);
        assertEquals(8, w2.getHitPoints());
    }

    @org.junit.jupiter.api.Test
    void testSetHitPoints() {
        Wizard w = new Wizard(Race.ELF, "Wizard", 10, 15, 12, 15, 10);
        assertEquals(9, w.getHitPoints());

        w.setHitPoints(12);
        assertEquals(12, w.getHitPoints());
    }

    @org.junit.jupiter.api.Test
    void testGetArmourClass() {
        Wizard w = new Wizard(Race.ELF, "Wizard", 10, 15, 12, 15, 10);
        assertEquals(10, w.getArmourClass());
    }

    @org.junit.jupiter.api.Test
    void testSetArmourClass() {
        Wizard w = new Wizard(Race.ELF, "Wizard", 10, 15, 12, 15, 10);
        assertEquals(10, w.getArmourClass());

        w.setArmourClass(20);
        assertEquals(20, w.getArmourClass());
    }

    @org.junit.jupiter.api.Test
    void testGetHitDiceAvailable() {
        Wizard w = new Wizard(Race.ELF, "Wizard", 10, 15, 12, 15, 10);
        assertEquals(1, w.getHitDiceAvailable());
    }

    @org.junit.jupiter.api.Test
    void testSetHitDiceAvailable() {
        Wizard w = new Wizard(Race.ELF, "Wizard", 10, 15, 12, 15, 10);
        assertEquals(1, w.getHitDiceAvailable());

        w.setHitDiceAvailable(4);
        assertEquals(4, w.getHitDiceAvailable());
    }

    @org.junit.jupiter.api.Test
    void testGetStat() {
        Wizard w = new Wizard(Race.ELF, "Wizard", 10, 15, 12, 15, 10);
        assertEquals(12, w.getStat(Stat.CON));
    }

    @org.junit.jupiter.api.Test
    void testGetStatModifier() {
        Wizard w = new Wizard(Race.ELF, "Wizard", 10, 12, 14, 16, 8);
        assertEquals(0, w.getStatModifier(Stat.STR));
        assertEquals(2, w.getStatModifier(Stat.DEX));
        assertEquals(2, w.getStatModifier(Stat.CON));
        assertEquals(4, w.getStatModifier(Stat.INT));
        assertEquals(-1, w.getStatModifier(Stat.WIS));
        assertEquals(0, w.getStatModifier(Stat.NONE));
    }

    @org.junit.jupiter.api.Test
    void testGetAttackDice() {
        Wizard w = new Wizard(Race.ELF, "Wizard", 10, 15, 12, 15, 10);
        assertEquals(8, w.getAttackDice().getDSize());
    }

    @org.junit.jupiter.api.Test
    void testSetAttackDice() {
        Wizard w = new Wizard(Race.ELF, "Wizard", 10, 15, 12, 15, 10);
        w.setAttackDice(new Dice(12));
        assertEquals(12, w.getAttackDice().getDSize());
    }

    @org.junit.jupiter.api.Test
    void testGetHitDice() {
        Wizard w = new Wizard(Race.ELF, "Wizard", 10, 15, 12, 15, 10);
        assertEquals(6, w.getHitDice().getDSize());
    }

    @org.junit.jupiter.api.Test
    void testSetHitDice() {
        Wizard w = new Wizard(Race.ELF, "Wizard", 10, 15, 12, 15, 10);
        w.setHitDice(new Dice(12));
        assertEquals(12, w.getHitDice().getDSize());
    }

    @org.junit.jupiter.api.Test
    void initialiseSpellSlots() {
        Wizard w = new Wizard(Race.ELF, "Wizard", 10, 15, 12, 15, 10);
        assertEquals(2, w.getSpellSlotsAvailable(Spells.HEAL));
        assertEquals(1, w.getSpellSlotsAvailable(Spells.FIREBALL));
        assertEquals(1, w.getSpellSlotsAvailable(Spells.RAY_OF_FROST));
    }
}
