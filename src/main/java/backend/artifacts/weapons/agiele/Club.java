package backend.artifacts.weapons.agiele;

import backend.artifacts.weapons.RangedSimpleWeapon;
import backend.logic.Dice;

public class Club extends RangedSimpleWeapon {

    public Club() {
        super(new Dice(6),1);
    }
}
