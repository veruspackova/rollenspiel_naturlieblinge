package backend.artifacts.spells;

import backend.character.Character;
import backend.character.Wizard;
import backend.enums.Spells;
import backend.logic.Dice;

import java.util.ArrayList;

public class SecondLife implements Spell {

    final private Dice dice;

    public SecondLife() {
        dice = new Dice(10);
    }

    public void cast(Wizard caster, ArrayList<Character> targets) {
        if (caster.getSpellSlotsAvailable(Spells.SECOND_LIFE) > 0) {

            for (Character target :
                    targets) {

                int hp = target.getHitPoints() + dice.roll();
                target.setHitPoints(hp);
                System.out.println(target.getName() + " has " + target.getHitPoints() + " HP.");
            }

            caster.useSpellSlot(Spells.SECOND_LIFE);
        }
    }
}
