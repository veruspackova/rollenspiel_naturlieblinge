package backend.character;

import backend.artifacts.items.Item;
import backend.enums.Race;
import backend.enums.Stat;
import backend.logic.Dice;

import java.util.ArrayList;

// HP, Armour Class and Hit Dice are parameters due to not all monsters being equal

public class Monster extends Character{
    public Monster(String name, int strength, int dexterity, int constitution, int intelligence, int wisdom, int hp, int armourClass, int hitDice, ArrayList<Item> items) {
        super(Race.NONE, name, strength, dexterity, constitution, intelligence, wisdom, items);
        setHitPoints(hp + getStatModifier(Stat.CON));
        setArmourClass(armourClass);
        setAttackDice(new Dice(hitDice));
        super.setHitDiceAvailable(0);
    }
}
