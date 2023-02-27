package backend.artifacts.weapons.melee;

import backend.artifacts.weapons.FinesseWeapon;
import backend.logic.Dice;

public class Dagger extends FinesseWeapon {

    public Dagger() {
        super(new Dice(4));
    }
}
