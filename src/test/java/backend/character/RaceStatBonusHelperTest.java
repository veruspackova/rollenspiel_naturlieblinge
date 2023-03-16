package backend.character;

import backend.enums.Race;
import backend.enums.Stat;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RaceStatBonusHelperTest {

    @org.junit.jupiter.api.Test
    void addStatBonuses_noRace() {
        RaceStatBonusHelper testObject = new RaceStatBonusHelper();
        Character testNoRace = new TestCharacter(Race.NONE, "Monster",
                5, 5, 5, 5, 5);

        testObject.addStatBonuses(testNoRace);

        assertEquals(5, testNoRace.getStat(Stat.STR));
        assertEquals(5, testNoRace.getStat(Stat.DEX));
        assertEquals(5, testNoRace.getStat(Stat.CON));
        assertEquals(5, testNoRace.getStat(Stat.INT));
        assertEquals(5, testNoRace.getStat(Stat.WIS));
    }

    @org.junit.jupiter.api.Test
    void addStatBonuses_human() {
        RaceStatBonusHelper testObject = new RaceStatBonusHelper();
        Character testNoRace = new TestCharacter(Race.HUM, "Human",
                5, 5, 5, 5, 5);

        testObject.addStatBonuses(testNoRace);

        assertEquals(6, testNoRace.getStat(Stat.STR));
        assertEquals(6, testNoRace.getStat(Stat.DEX));
        assertEquals(6, testNoRace.getStat(Stat.CON));
        assertEquals(6, testNoRace.getStat(Stat.INT));
        assertEquals(6, testNoRace.getStat(Stat.WIS));
    }

    @org.junit.jupiter.api.Test
    void addStatBonuses_dwarf() {
        RaceStatBonusHelper testObject = new RaceStatBonusHelper();
        Character testNoRace = new TestCharacter(Race.DWA, "Dwarf",
                5, 5, 5, 5, 5);

        testObject.addStatBonuses(testNoRace);

        assertEquals(7, testNoRace.getStat(Stat.STR));
        assertEquals(5, testNoRace.getStat(Stat.DEX));
        assertEquals(7, testNoRace.getStat(Stat.CON));
        assertEquals(5, testNoRace.getStat(Stat.INT));
        assertEquals(6, testNoRace.getStat(Stat.WIS));
    }


    @org.junit.jupiter.api.Test
    void addStatBonuses_elf() {
        RaceStatBonusHelper testObject = new RaceStatBonusHelper();
        Character testNoRace = new TestCharacter(Race.ELF, "Elf",
                5, 5, 5, 5, 5);

        testObject.addStatBonuses(testNoRace);

        assertEquals(5, testNoRace.getStat(Stat.STR));
        assertEquals(7, testNoRace.getStat(Stat.DEX));
        assertEquals(5, testNoRace.getStat(Stat.CON));
        assertEquals(7, testNoRace.getStat(Stat.INT));
        assertEquals(6, testNoRace.getStat(Stat.WIS));
    }

    @org.junit.jupiter.api.Test
    void addStatBonuses_hobbit() {
        RaceStatBonusHelper testObject = new RaceStatBonusHelper();
        Character testNoRace = new TestCharacter(Race.HOB, "Hobbit",
                5, 5, 5, 5, 5);

        testObject.addStatBonuses(testNoRace);

        assertEquals(5, testNoRace.getStat(Stat.STR));
        assertEquals(7, testNoRace.getStat(Stat.DEX));
        assertEquals(6, testNoRace.getStat(Stat.CON));
        assertEquals(5, testNoRace.getStat(Stat.INT));
        assertEquals(7, testNoRace.getStat(Stat.WIS));
    }

    public static class TestCharacter extends Character {
        public TestCharacter(Race race, String name, int strength, int dexterity, int constitution, int intelligence, int wisdom) {
            super(race, name, strength, dexterity, constitution, intelligence, wisdom, null, null, null);
        }
    }
}