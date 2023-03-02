package backend.artifacts.weapons.melee;

import backend.artifacts.weapons.SimpleWeapon;
import backend.logic.Dice;

public class Sword extends SimpleWeapon {
    public Sword() {
        super(new Dice(8));
    }

    @Override
    public int getRequiredStrength() {
        return 13;
    }
}
