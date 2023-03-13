package backend.artifacts.weapons.melee;

import backend.artifacts.weapons.WeaponBase;
import backend.logic.Dice;

public class WarAxe extends WeaponBase {
    public WarAxe() {
        super(new Dice(6));
    }
}
