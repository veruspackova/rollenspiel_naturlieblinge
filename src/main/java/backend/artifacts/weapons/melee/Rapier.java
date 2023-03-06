package backend.artifacts.weapons.melee;

import backend.artifacts.weapons.FinesseWeapon;
import backend.logic.Dice;

public class Rapier extends FinesseWeapon {
    public Rapier() {
        super(new Dice(8));
    }
}
