package backend.artifacts.spells;

import backend.logic.Dice;
import backend.character.Character;

public class MageArmour implements Spell {

    final private Dice dice;

    public MageArmour() { dice = new Dice(8); }

    public void cast(Character target) {
        int ac;
        ac = target.getArmourClass() + dice.roll();
        target.setArmourClass(ac);
        System.out.println(target.getName() + " has " + target.getArmourClass() + " AC.");
    }
}
