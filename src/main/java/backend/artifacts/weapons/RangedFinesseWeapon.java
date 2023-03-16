package backend.artifacts.weapons;

import backend.enums.Stat;
import backend.logic.Dice;

public abstract class RangedFinesseWeapon extends RangedSimpleWeapon {

    public RangedFinesseWeapon(Dice damageDice, int range) {
        super(damageDice, range);
    }

    public Stat getWeaponProficiencyStat() {
        return Stat.DEX;
    }
}
