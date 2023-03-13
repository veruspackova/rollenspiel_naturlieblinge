package backend.artifacts.weapons;

import backend.logic.Dice;

public abstract class RangedSimpleWeapon extends WeaponBase {
    private int range;

    public RangedSimpleWeapon(Dice damageDice, int range) {
        super(damageDice);
        this.range = range;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }
}
