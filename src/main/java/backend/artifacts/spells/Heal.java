package backend.artifacts.spells;

import backend.logic.Dice;
import backend.character.Character;

public class Heal implements Spell {

    final private Dice dice;

    public Heal() { dice = new Dice(8); }

    public void cast(Character target) {
        int hp = target.getHitPoints() + dice.roll();
        target.setHitPoints(hp);
        System.out.println(target.getName() + " has " + target.getHitPoints() + " HP.");
    }
}