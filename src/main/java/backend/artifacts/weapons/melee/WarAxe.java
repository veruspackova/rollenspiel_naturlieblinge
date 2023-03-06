package backend.artifacts.weapons.melee;

import backend.artifacts.weapons.SimpleWeapon;
import backend.logic.Dice;

public class WarAxe extends SimpleWeapon {
    public WarAxe() {
        super(new Dice(6));
    }
}
