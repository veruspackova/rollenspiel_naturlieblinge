package backend.artifacts.spells;

import backend.artifacts.weapons.RangedSimpleWeapon;
import backend.artifacts.weapons.WeaponBase;
import backend.character.Character;
import backend.character.Wizard;
import backend.enums.Stat;
import backend.logic.Dice;
import backend.logic.FightRound;

public class RayOfFrost extends RangedSimpleWeapon implements Spell {
    public RayOfFrost() {
        super(new Dice(8), 4);
    }

    @Override
    public Stat getWeaponProficiencyStat() {
        return Stat.INT;
    }

    @Override
    public int rollDamage() {
        return super.rollDamage();
    }

    @Override
    public void cast(Wizard caster, Character target) {
        if (caster.getSlotsRayOfFrost() > 0) {
            WeaponBase initialWeapon = caster.getSelectedWeapon();
            caster.setSelectedWeapon(this);

            FightRound fight = new FightRound(caster, target);
            fight.attack();

            caster.setSlotsRayOfFrost(caster.getSlotsRayOfFrost() - 1);
            caster.setSelectedWeapon(initialWeapon);
        }
    }
}
