package backend.artifacts.weapons.agiele;

import backend.artifacts.weapons.RangedFinesseWeapon;
import backend.logic.Dice;

public class Dagger extends RangedFinesseWeapon {

    public Dagger() {
        super(new Dice(4),2);
    }
}
