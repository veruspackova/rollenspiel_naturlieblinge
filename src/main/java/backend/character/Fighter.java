package backend.character;

import backend.artifacts.items.Item;
import backend.artifacts.weapons.WeaponBase;
import backend.enums.Race;
import backend.enums.Stat;
import backend.logic.Dice;

import java.util.ArrayList;

//-- Hit Points (HP): 10
//-- Armor Class (AC): 13
//--Attack/Damage: d10

// Beginning items: chain mail, a martial weapon and a shield OR two martial weapons, two handaxes
public class Fighter extends Character {
    public Fighter(Race race, String name, int strength, int dexterity, int constitution, int intelligence, int wisdom, ArrayList<Item> items, WeaponBase selectedWeapon, ArrayList<WeaponBase> weapons) {
        super(race, name, strength, dexterity, constitution, intelligence, wisdom, items, selectedWeapon, weapons);

        setHitPoints(10 + getStatModifier(Stat.CON));
        setHitDice(new Dice(10));
        setHitDiceAvailable(1);

        setArmourClass(13);
        setAttackDice(new Dice(10));
    }
}
