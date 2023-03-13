package backend.artifacts.weapons.melee;

import backend.artifacts.weapons.WeaponBase;
import backend.enums.Stat;
import backend.logic.Dice;

public class Spear extends WeaponBase {

    public Spear() {
        super(new Dice(6));
    }

    @Override
    public Stat getWeaponProficiencyStat() {
        return Stat.NONE;
    }
}
