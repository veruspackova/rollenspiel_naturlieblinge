package backend.artifacts.spells;

import backend.logic.Dice;

public class MagicMist implements Spell {

    final private Dice dice;

    public MagicMist() { dice = new Dice(4); }
}
