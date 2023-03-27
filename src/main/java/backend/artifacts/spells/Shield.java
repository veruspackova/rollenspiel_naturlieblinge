package backend.artifacts.spells;

import backend.character.Character;
import backend.character.Monster;
import backend.character.Wizard;
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
        int bonusAc = dice.roll();
        for (Character target : targets) {
            if (!(target instanceof Monster)) {
                target.setArmourClass(target.getArmourClass() + bonusAc);
            }
        }
    }
}
