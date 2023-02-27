package backend.artifacts.weapons;

import backend.logic.Attack;
import backend.logic.Dice;

public abstract class SimpleWeapon implements Attack {
    private final Dice damageDice;

    public SimpleWeapon(Dice damageDice) {
        this.damageDice = damageDice;
    }

    @Override
    public int rollDamage() {
        return damageDice.roll();
    }
}
