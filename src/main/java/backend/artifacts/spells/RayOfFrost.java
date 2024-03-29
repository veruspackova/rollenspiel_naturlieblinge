package backend.artifacts.spells;

import backend.artifacts.weapons.RangedSimpleWeapon;
import backend.artifacts.weapons.WeaponBase;
import backend.character.Character;
import backend.character.Wizard;
import backend.enums.Spells;
import backend.enums.Stat;
import backend.logic.Dice;
import backend.logic.FightRound;

import java.util.ArrayList;

public class RayOfFrost extends RangedSimpleWeapon implements Spell {
    public RayOfFrost() {
        super(new Dice(8), 4);
    }

    @Override
    public Stat getWeaponProficiencyStat() {
        return Stat.INT;
    }

    @Override
    public int rollDamage() {
        return super.rollDamage();
    }

    // Make a ranged spell attack against the target. On a hit, it takes 1d8 cold damage
    @Override
    public void castSpell(Wizard caster, ArrayList<Character> targets) {
        if (caster.getSpellSlotsAvailable(Spells.RAY_OF_FROST) > 0) {
            WeaponBase initialWeapon = caster.getSelectedWeapon();
            caster.setSelectedWeapon(this);
            for (Character target :
                    targets) {

                FightRound fight = new FightRound(caster, target);
                fight.attack();
            }

            caster.useSpellSlot(Spells.RAY_OF_FROST);
            caster.setSelectedWeapon(initialWeapon);
        }
    }
}
