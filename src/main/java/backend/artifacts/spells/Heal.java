package backend.artifacts.spells;

import backend.character.Character;
import backend.character.Wizard;
import backend.enums.Spells;
import backend.logic.Dice;

import java.util.ArrayList;

public class Heal implements Spell {

    final private Dice dice;

    public Heal() {
        dice = new Dice(8);
    }

    public void castSpell(Wizard caster, ArrayList<Character> targets) {

        if (caster.getSpellSlotsAvailable(Spells.HEAL) > 0) {
            for (Character target : targets) {
                int hp = target.getHitPoints() + dice.roll();
                target.setHitPoints(hp);
                System.out.println(target.getName() + " has " + target.getHitPoints() + " HP.");
            }

            caster.useSpellSlot(Spells.HEAL);
        }
    }
}
