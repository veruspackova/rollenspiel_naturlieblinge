package backend.artifacts.spells;

import backend.artifacts.weapons.RangedSimpleWeapon;
import backend.artifacts.weapons.WeaponBase;
import backend.character.Character;
import backend.character.Wizard;
import backend.enums.Spells;
import backend.logic.Dice;
import backend.logic.FightRound;

import java.util.ArrayList;

public class Fireball extends RangedSimpleWeapon implements Spell {

    public Fireball() {
        super(new Dice(8), 3);
    }

    // range weapon attacks "all on field" (3x3) d8 damage range +3 fields
    public void castSpell(Wizard caster, ArrayList<Character> targets) {
        if (caster.getSpellSlotsAvailable(Spells.FIREBALL) > 0) {
            WeaponBase initialWeapon = caster.getSelectedWeapon();
            caster.setSelectedWeapon(this);
            for (Character target :
                    targets) {

                FightRound fight = new FightRound(caster, target);
                fight.attack();
            }

            caster.useSpellSlot(Spells.FIREBALL);
            caster.setSelectedWeapon(initialWeapon);
        }
    }
}
