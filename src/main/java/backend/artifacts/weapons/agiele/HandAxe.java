package backend.artifacts.weapons.agiele;

import backend.artifacts.weapons.RangedSimpleWeapon;
import backend.enums.Stat;
import backend.logic.Dice;

public class HandAxe extends RangedSimpleWeapon {
    public HandAxe() {
        super(new Dice(6),2);
    }

    @Override
    public Stat getWeaponProficiencyStat() {
        return Stat.NONE;
    }
}
