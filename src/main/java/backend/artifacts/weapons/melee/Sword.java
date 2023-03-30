package backend.artifacts.weapons.melee;

import backend.artifacts.weapons.WeaponBase;
import backend.logic.Dice;

public class Sword extends WeaponBase {
    public Sword() {
        super(new Dice(8));
    }

    @Override
    public int getRequiredStrength() {
        return 13;
    }
}