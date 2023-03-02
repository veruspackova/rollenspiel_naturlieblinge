package backend.artifacts.weapons;

import backend.enums.Stat;
import backend.logic.Dice;

public abstract class RangedFinesseWeapon extends FinesseWeapon {
    private int range;

    public RangedFinesseWeapon(Dice damageDice, int range) {
        super(damageDice);
        this.range = range;
    }

    public Stat getWeaponProficiencyStat() {
        return Stat.DEX;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }
}
