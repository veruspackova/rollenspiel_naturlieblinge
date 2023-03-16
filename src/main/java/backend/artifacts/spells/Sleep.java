package backend.artifacts.spells;

import backend.logic.Dice;

public class Sleep implements Spell {

    final private Dice dice;

    public Sleep() {
        dice = new Dice(4);
    }
}
