package backend.artifacts.weapons.melee;

import backend.artifacts.weapons.WeaponBase;
import backend.logic.Dice;

public class Club extends WeaponBase {
    public Club() {
        super(new Dice(6));
    }
}
