package backend.logic;

import backend.artifacts.spells.Spell;
import backend.artifacts.weapons.WeaponBase;
import backend.character.Character;

public class AttackSuccess {
    private static Dice d20;

    public AttackSuccess() {
        d20 = new Dice(20);
    }

    public AttackSuccess(Dice dice) {
        d20 = dice;
    }

    public static boolean isAttackSuccessful(Character attacker, Character target) {
        WeaponBase w = attacker.getSelectedWeapon();
        int bonus = attacker.getStatModifier(w.getWeaponProficiencyStat());

        if (w instanceof Spell) {
            bonus += attacker.getProficiencyBonus();
        }

        int attackRoll = d20.roll() + bonus;
        int targetDefence = target.getArmourClass() + target.getArmour().getAcBonus();
        return attackRoll > targetDefence;
    }
}
