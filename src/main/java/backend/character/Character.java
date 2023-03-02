package backend.character;

import backend.enums.Race;
import backend.enums.Stat;
import backend.gameBoard.RoomField;
import backend.logic.Dice;

import static java.lang.Math.floor;

public abstract class Character {
    private Race race;
    private String name;
    private RoomField position;
//    private Direction direction; --> to be implemented, +getters,setters
    private int hitPoints;
    private int armourClass;
    private Dice attackDice;
    private Dice hitDice;
    private int hitDiceAvailable;
    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;

    public Character(
            Race race,
            String name,
            int strength,
            int dexterity,
            int constitution,
            int intelligence,
            int wisdom
    ) {
        setRace(race);
        setName(name);
        setStrength(strength);
        setDexterity(dexterity);
        setConstitution(constitution);
        setIntelligence(intelligence);
        setWisdom(wisdom);

        RaceStatBonusHelper bonusHelper = new RaceStatBonusHelper();
        bonusHelper.addStatBonuses(this);
    }

    public Race getRace() {
        return race;
    }

    protected void setRace(Race race) {
        this.race = race;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoomField getPosition() {
        return position;
    }

    public void setPosition(RoomField position) {
        this.position = position;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getArmourClass() {
        return armourClass;
    }

    public void setArmourClass(int armourClass) {
        this.armourClass = armourClass;
    }

    public int getHitDiceAvailable() {
        return hitDiceAvailable;
    }

    public void setHitDiceAvailable(int hitDiceAvailable) {
        this.hitDiceAvailable = hitDiceAvailable;
    }

    public int getStat(Stat stat) {
        int value = 0;

        switch (stat) {
            case STR -> value = strength;
            case DEX -> value = dexterity;
            case CON -> value = constitution;
            case INT -> value = intelligence;
            case WIS -> value = wisdom;
            case NONE -> value = 10;
        }

        return value;
    }

    // to determine an ability modifier, subtract 10 from the ability score
    // and then divide the result by 2 (round down)
    public int getStatModifier(Stat stat) {
        return (int) floor((getStat(stat) - 10) / 2.0);
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

    public Dice getAttackDice() {
        return attackDice;
    }

    public void setAttackDice(Dice attack) {
        this.attackDice = attack;
    }

    public Dice getHitDice() {
        return hitDice;
    }

    public void setHitDice(Dice hitDice) {
        this.hitDice = hitDice;
    }
}
