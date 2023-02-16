package backend.character;

import backend.enums.Race;
import backend.enums.Stat;
import backend.logic.Dice;

// HP, Armour Class and Hit Dice are parameters due to not all monsters being equal

public class Monster extends Character{
    public Monster(Race race, String name, int strength, int dexterity, int constitution, int intelligence, int wisdom, int hp, int armourClass, int hitDice) {
        super(race, name, strength, dexterity, constitution, intelligence, wisdom);
        setHitPoints(hp + getStatModifier(Stat.CON));
        setArmourClass(armourClass);
        setAttackDice(new Dice(hitDice));
        super.setHitDiceAvailable(0);
    }
}
