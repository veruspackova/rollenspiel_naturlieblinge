package backend.artifacts.spells;

import backend.logic.Dice;

public class Shield implements Spell {

    final private Dice dice;

    public Shield() { dice = new Dice(8); }
}
