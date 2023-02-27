package backend.artifacts.weapons.melee;

import backend.artifacts.weapons.SimpleWeapon;
import backend.logic.Dice;

public class Dagger extends SimpleWeapon {

    public Dagger(Dice damageDice) {
        super(damageDice);
    }

    public Dagger() {
        super(new Dice(4));
    }
}
