package backend.artifacts.weapons.agiele;

import backend.artifacts.weapons.RangedSimpleWeapon;
import backend.artifacts.weapons.WeaponBase;
import backend.enums.Stat;
import backend.logic.Dice;

public class Spear extends RangedSimpleWeapon {

    public Spear() {
        super(new Dice(6),2);
    }

    @Override
    public Stat getWeaponProficiencyStat() {
        return Stat.NONE;
    }
}
