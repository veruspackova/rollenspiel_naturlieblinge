package backend.artifacts.spells;

import backend.character.Character;
import backend.logic.Dice;

public class SecondLife implements Spell {

    final private Dice dice;

    public SecondLife() { dice = new Dice(10); }

    public void cast(Character target) {
        int hp = target.getHitPoints() + dice.roll();
        target.setHitPoints(hp);
        System.out.println(target.getName() + " has " + target.getHitPoints() + " HP.");
    }
}
