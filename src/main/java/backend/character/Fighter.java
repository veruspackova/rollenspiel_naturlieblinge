package backend.character;

import backend.enums.Race;
import backend.enums.Stat;
import backend.logic.Dice;

//-- Hit Points (HP): 10
//-- Armor Class (AC): 13
//--Attack/Damage: d10

public class Fighter extends Character {
    public Fighter(Race race, String name, int strength, int dexterity, int constitution, int intelligence, int wisdom) {
        super(race, name, strength, dexterity, constitution, intelligence, wisdom);
        setHitPoints(10 + getStatModifier(Stat.CON));
        setArmourClass(13);
        setAttackDice(new Dice(10));
    }
}
