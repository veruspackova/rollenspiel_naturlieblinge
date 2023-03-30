package backend.artifacts.spells;

import backend.character.Character;
import backend.character.Wizard;
import backend.enums.Spells;
import backend.logic.Dice;

import java.util.ArrayList;

public class MageArmour implements Spell {

    final private Dice dice;

    public MageArmour() {
        dice = new Dice(8);
    }

    public void cast(Wizard caster, ArrayList<Character> targets) {
        if (caster.getSpellSlotsAvailable(Spells.MAGE_ARMOUR) > 0) {

            for (Character target :
                    targets) {

                int ac = target.getArmourClass() + dice.roll();
                target.setArmourClass(ac);
                System.out.println(target.getName() + " has " + target.getArmourClass() + " AC.");
            }

            caster.useSpellSlot(Spells.MAGE_ARMOUR);
        }
    }
}
