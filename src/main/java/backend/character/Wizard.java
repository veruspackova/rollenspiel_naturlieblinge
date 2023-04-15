package backend.character;

//-- Hit Points (HP): 8
//-- Armor Class (AC): 10
//-- Attack/Damage: d8

import backend.artifacts.items.Item;
import backend.artifacts.weapons.WeaponBase;
import backend.artifacts.weapons.agiele.Dagger;
import backend.artifacts.weapons.melee.NoWeapon;
import backend.enums.Race;
import backend.enums.Spells;
import backend.enums.Stat;
import backend.logic.Dice;

import java.util.ArrayList;

public class Wizard extends Character {

    private int slotsHeal;
    private int slotsRayOfFrost;
    private int slotsBurningHands;
    private int slotsMageArmour;
    private int slotsShield;
    private int slotsFireball;
    private int slotsSecondLife;

    // Beginning items: dagger
    public Wizard(Race race, String name, int strength, int dexterity, int constitution, int intelligence, int wisdom, ArrayList<Item> items, WeaponBase selectedWeapon, ArrayList<WeaponBase> weapons) {
        super(race, name, strength, dexterity, constitution, intelligence, wisdom, items, selectedWeapon, weapons);
        initialiseSpellSlots();

        setHitPoints(8 + getStatModifier(Stat.CON));
        setHitDiceAvailable(1);
        setHitDice(new Dice(6));

        setArmourClass(10);
        setAttackDice(new Dice(8));
    }

    public Wizard(Race race, String name) {
        this(race, name, 10, 10, 10, 10, 10, new ArrayList<>(), new NoWeapon(), new ArrayList<>());
    }

    public Wizard(Race race, String name, int strength, int dexterity, int constitution, int intelligence, int wisdom) {
        super(race, name, strength, dexterity, constitution, intelligence, wisdom, new ArrayList<>(), new NoWeapon(), new ArrayList<>());
        setHitPoints(8 + getStatModifier(Stat.CON));
        setHitDiceAvailable(1);
        setHitDice(new Dice(6));
        initialiseSpellSlots();

        setArmourClass(10);
        setAttackDice(new Dice(8));

        WeaponBase dagger = new Dagger();
        setSelectedWeapon(dagger);
        addWeapon(dagger);
    }

    public int getSpellDC() {
        return 8 + getStatModifier(Stat.INT) + getProficiencyBonus();
    }

    public void setSelectedWeapon(WeaponBase selectedWeapon) {
        if (getStat(Stat.STR) >= selectedWeapon.getRequiredStrength()) {
            super.selectedWeapon = selectedWeapon;
        }
    }

    public void initialiseSpellSlots() {
        slotsHeal = 2;
        slotsRayOfFrost = 1;
        slotsBurningHands = 1;
        slotsMageArmour = 1;
        slotsShield = 1;
        slotsFireball = 1;
        slotsSecondLife = 1;
    }

    public int getSpellSlotsAvailable(Spells spell) {
        int value = 0;

        switch (spell) {
            case BURNING_HANDS -> value = slotsBurningHands;
            case RAY_OF_FROST -> value = slotsRayOfFrost;
            case HEAL -> value = slotsHeal;
            case MAGE_ARMOUR -> value = slotsMageArmour;
            case SHIELD -> value = slotsShield;
            case FIREBALL -> value = slotsFireball;
            case SECOND_LIFE -> value = slotsSecondLife;
        }

        return value;
    }

    public void useSpellSlot(Spells spell) {

        switch (spell) {
            case BURNING_HANDS -> slotsBurningHands -= 1;
            case RAY_OF_FROST -> slotsRayOfFrost -= 1;
            case HEAL -> slotsHeal -= 1;
            case MAGE_ARMOUR -> slotsMageArmour -= 1;
            case SHIELD -> slotsShield -= 1;
            case FIREBALL -> slotsFireball -= 1;
            case SECOND_LIFE -> slotsSecondLife -= 1;
        }
    }
}
