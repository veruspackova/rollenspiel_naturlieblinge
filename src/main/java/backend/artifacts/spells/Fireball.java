package backend.artifacts.spells;

import backend.character.Character;
import backend.logic.Dice;

public class Fireball implements Spell {

    final private Dice dice;

    public Fireball() { dice = new Dice(8); }

    public void cast(Character target) {

//        System.out.println(target.getName());
    }
}
