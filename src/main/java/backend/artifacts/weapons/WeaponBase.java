package backend.artifacts.weapons;

import backend.artifacts.ISearchable;
import backend.character.Character;
import backend.enums.Stat;
import backend.logic.Attack;
import backend.logic.Dice;

public abstract class WeaponBase implements Attack, ISearchable {
    private final Dice damageDice;

    public WeaponBase(Dice damageDice) {
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

    @Override
    public void search(Character character) {

    }
}
