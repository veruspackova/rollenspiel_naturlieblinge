package backend.character;


//-- Hit Points (HP): 8
//-- Armor Class (AC): 10
//-- Attack/Damage: d8


import backend.artifacts.items.Item;
import backend.enums.Race;
import backend.enums.Stat;
import backend.logic.Dice;

import java.util.ArrayList;

public class Thief extends Character {

    // Beginning items: rapier, shortsword, leather armor, two daggers, thieves’ tools (add DEX bonus to ability checks for disarming traps or opening locks)
    public Thief(Race race, String name, int strength, int dexterity, int constitution, int intelligence, int wisdom, ArrayList<Item> items) {
        super(race, name, strength, dexterity, constitution, intelligence, wisdom, items);

        setHitPoints(8 + getStatModifier(Stat.CON));
        setHitDiceAvailable(1);
        setHitDice(new Dice(8));

        setArmourClass(10);
        setAttackDice(new Dice(8));
    }
}
