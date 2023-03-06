package backend.artifacts.weapons.melee;

import backend.artifacts.weapons.SimpleWeapon;
import backend.logic.Dice;

public class None extends SimpleWeapon {
    public None() {
        super(new Dice(1));
    }
}
