package backend.artifacts.weapons.melee;

import backend.artifacts.weapons.SimpleWeapon;
import backend.logic.Dice;

public class Club extends SimpleWeapon {
    public Club() {
        super(new Dice(6));
    }
}
