package backend.character;

//-- Hit Points (HP): 8
//-- Armor Class (AC): 10
//-- Attack/Damage: d8

import backend.artifacts.items.Item;
import backend.enums.Race;
import backend.enums.Stat;
import backend.logic.Dice;

import java.util.ArrayList;

public class Wizard extends Character {

    private int slotsHeal;

    // Beginning items: dagger
    public Wizard(Race race, String name, int strength, int dexterity, int constitution, int intelligence, int wisdom, ArrayList<Item> items) {
        super(race, name, strength, dexterity, constitution, intelligence, wisdom, items);
        initialiseSpellSlots();

        setHitPoints(8 + getStatModifier(Stat.CON));
        setHitDiceAvailable(1);
        setHitDice(new Dice(6));

        setArmourClass(10);
        setAttackDice(new Dice(8));
    }

    public void initialiseSpellSlots() {
        slotsHeal = 2;
    }

    /**
     * 1d8 + your spellcasting ability modifier
     *
     * @param target Character to be healed
     */
    public void castHeal(Character target) {
        if (slotsHeal > 0) {
            Dice d8 = new Dice(8);

            int healAmount = d8.roll() + getStatModifier(Stat.INT);
            target.setHitPoints(target.getHitPoints() + healAmount);

            slotsHeal -= 1;
        }
    }

    public int getSlotsHeal() {
        return slotsHeal;
    }

    public void setSlotsHeal(int slotsHeal) {
        this.slotsHeal = slotsHeal;
    }
}
