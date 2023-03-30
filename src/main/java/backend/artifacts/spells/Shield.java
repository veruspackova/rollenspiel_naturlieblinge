package backend.artifacts.spells;

import backend.character.Character;
import backend.character.Monster;
import backend.character.Wizard;
import backend.enums.Spells;
import backend.logic.Dice;

import java.util.ArrayList;

public class Shield implements Spell {

    final private Dice dice;

    public Shield() {
        dice = new Dice(8);
    }

    // later: for x turns only?
    // 3 x 3, Wizard in centre
    public void castSpell(Wizard caster, ArrayList<Character> targets) {
        if (caster.getSpellSlotsAvailable(Spells.SHIELD) > 0) {
            int bonusAc = dice.roll();
            for (Character target : targets) {
                if (!(target instanceof Monster)) {
                    target.setArmourClass(target.getArmourClass() + bonusAc);
                }
            }
            caster.useSpellSlot(Spells.SHIELD);
        }
    }
}
