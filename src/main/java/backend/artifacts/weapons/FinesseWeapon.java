package backend.artifacts.weapons;

import backend.enums.Stat;
import backend.logic.Dice;

public abstract class FinesseWeapon extends SimpleWeapon {

    public FinesseWeapon(Dice damageDice) {
        super(damageDice);
    }

    public Stat getWeaponProficiencyStat() {
        return Stat.DEX;
    }
}
