package backend.character;

import backend.artifacts.armour.Chainmail;
import backend.artifacts.items.Item;
import backend.artifacts.weapons.WeaponBase;
import backend.artifacts.weapons.melee.HandAxe;
import backend.artifacts.weapons.melee.NoWeapon;
import backend.artifacts.weapons.melee.Sword;
import backend.artifacts.weapons.melee.WarAxe;
import backend.enums.Race;
import backend.enums.Stat;
import backend.logic.Dice;

import java.util.ArrayList;

//-- Hit Points (HP): 10
//-- Armor Class (AC): 13
//--Attack/Damage: d10

// Beginning items: chain mail, two martial weapons, two handaxes
public class Fighter extends Character {
    public Fighter(Race race, String name, int strength, int dexterity, int constitution, int intelligence, int wisdom, ArrayList<Item> items, WeaponBase selectedWeapon, ArrayList<WeaponBase> weapons) {
        super(race, name, strength, dexterity, constitution, intelligence, wisdom, items, selectedWeapon, weapons);


    }

    public Fighter(Race race, String name) {
        this(race, name, 10, 10, 10, 10, 10);
    }

    public Fighter(Race race, String name, int strength, int dexterity, int constitution, int intelligence, int wisdom) {
        super(race, name, strength, dexterity, constitution, intelligence, wisdom, new ArrayList<>(), new NoWeapon(), new ArrayList<>());
        setHitPoints(10 + getStatModifier(Stat.CON));
        setHitDice(new Dice(10));
        setHitDiceAvailable(1);

        setArmourClass(13);
        setAttackDice(new Dice(10));
        setArmour(new Chainmail());
        WeaponBase warAxe = new WarAxe();
        setSelectedWeapon(warAxe);
        addWeapon(warAxe);
        addWeapon(new Sword());
        addWeapon(new HandAxe());
        addWeapon(new HandAxe());
    }
}
