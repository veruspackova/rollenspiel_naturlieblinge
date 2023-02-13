package backend.character;


//-- Hit Points (HP): 8
//-- Armor Class (AC): 10
//-- Attack/Damage: d8


import backend.enums.Race;
import backend.enums.Stat;
import backend.logic.Dice;

public class Thief extends Character
{

    public Thief(Race race, String name, int strength, int dexterity, int constitution, int intelligence, int wisdom) {
        super(race, name, strength, dexterity, constitution, intelligence, wisdom);
        setHitPoints(8 + getStatModifier(Stat.CON));
        setArmourClass(10);
        setAttackDice(new Dice(8));
    }
}
