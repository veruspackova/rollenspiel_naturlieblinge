package backend.artifacts.weapons.melee;

import backend.artifacts.weapons.WeaponBase;
import backend.logic.Dice;

public class NoWeapon extends WeaponBase {
    public NoWeapon() {
        super(new Dice(1));
    }

}
