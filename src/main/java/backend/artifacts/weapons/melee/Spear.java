package backend.artifacts.weapons.melee;

import backend.artifacts.weapons.SimpleWeapon;
import backend.enums.Stat;
import backend.logic.Dice;

public class Spear extends SimpleWeapon {

    public Spear() {
        super(new Dice(6));
    }

    @Override
    public Stat getWeaponProficiencyStat() {
        return Stat.NONE;
    }
}
