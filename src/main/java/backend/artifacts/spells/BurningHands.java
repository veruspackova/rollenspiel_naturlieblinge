package backend.artifacts.spells;

import backend.artifacts.weapons.WeaponBase;
import backend.character.Character;
import backend.character.Wizard;
import backend.enums.Spells;
import backend.enums.Stat;
import backend.logic.Dice;

import java.util.ArrayList;

public class BurningHands extends WeaponBase implements Spell {

    public BurningHands() {
        super(new Dice(6));
    }

    public void castSpell(Wizard caster, ArrayList<Character> targets) {
        if (caster.getSpellSlotsAvailable(Spells.BURNING_HANDS) > 0) {
            int initialDamage = rollDamage();

            for (Character target : targets) {
                int dmg = initialDamage;
                boolean saveSucceeded = target.getSavingThrowSuccessful(Stat.DEX, caster.getSpellDC());

                if (saveSucceeded) {
                    dmg = Math.floorDiv(initialDamage, 2);
                }

                int newHP = target.getHitPoints() - dmg;
                target.setHitPoints(newHP);
            }

            caster.useSpellSlot(Spells.BURNING_HANDS);
        }
    }

    @Override
    public int rollDamage() {
        return super.rollDamage() + super.rollDamage() + super.rollDamage();
    }
}
