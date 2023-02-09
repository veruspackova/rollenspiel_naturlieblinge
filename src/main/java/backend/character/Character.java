package backend.character;

import backend.enums.Race;
import backend.enums.Stat;

public class Character {
    private Race race;
    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public int getStat(Stat stat) {
        int value = 0;

        switch (stat) {
            case STR -> value = strength;
            case DEX -> value = dexterity;
            case CON -> value = constitution;
            case INT -> value = intelligence;
            case WIS -> value = wisdom;
        }

        return value;
    }

    public void setStat(Stat stat, int value) {
        switch (stat) {
            case STR -> setStrength(value);
            case DEX -> setDexterity(value);
            case CON -> setConstitution(value);
            case INT -> setIntelligence(value);
            case WIS -> setWisdom(value);
        }
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }
}
