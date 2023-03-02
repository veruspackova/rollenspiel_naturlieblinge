package backend.artifacts.weapons.melee;

import backend.artifacts.weapons.SimpleWeapon;
import backend.logic.Dice;

public class NoWeapon extends SimpleWeapon {
    public NoWeapon() {
        super(new Dice(1));
    }
}
