package backend.character;

import backend.enums.Race;
import backend.enums.Stat;
import backend.logic.Dice;

//@todo werte einpflegen nach antwort im forum

public class Monster extends Character{
    public Monster(Race race, String name, int strength, int dexterity, int constitution, int intelligence, int wisdom) {
        super(race, name, strength, dexterity, constitution, intelligence, wisdom);
        setHitPoints(10 + getStatModifier(Stat.CON));
        setArmourClass(10);
        setAttackDice(new Dice(10));
        super.setHitDiceAvailable(0);
    }
}
