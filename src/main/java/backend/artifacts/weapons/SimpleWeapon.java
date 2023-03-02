package backend.artifacts.weapons;

import backend.enums.Stat;
import backend.logic.Attack;
import backend.logic.Dice;

public abstract class SimpleWeapon implements Attack {
    private final Dice damageDice;

    public SimpleWeapon(Dice damageDice) {
        this.damageDice = damageDice;
    }

    public Stat getWeaponProficiencyStat() {
        return Stat.STR;
    }

    public int getRequiredStrength() {
        return 0;
    }

    @Override
    public int rollDamage() {
        return damageDice.roll();
    }
}
