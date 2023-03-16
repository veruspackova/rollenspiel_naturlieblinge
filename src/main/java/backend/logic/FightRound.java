package backend.logic;

import backend.artifacts.weapons.WeaponBase;
import backend.character.Character;
import backend.enums.Stat;

import static backend.logic.AttackSuccess.isAttackSuccessful;


public class FightRound {
    private final Dice d20;
    private Character target;
    private Character attacker;

    public FightRound(Dice d20, Character fighter1, Character fighter2) {
        this.d20 = d20;
        this.target = fighter1;
        this.attacker = fighter2;
    }

    public FightRound(Character fighter1, Character fighter2) {
        this(new Dice(20), fighter1, fighter2);
    }

    private void rollForInitiative() {
        int initiative1 = rollInitiative(attacker.getStatModifier(Stat.DEX));
        int initiative2 = rollInitiative(target.getStatModifier(Stat.DEX));

        if (Integer.max(initiative1, initiative2) == initiative2) {
            Character tmp = attacker;
            this.attacker = target;
            this.target = tmp;
        }

    }

    public void fightToTheDeath() {
        rollForInitiative();

        while (attacker.getHitPoints() > 0 && target.getHitPoints() > 0) {
            attack();
            swapRoles();
        }
    }

    public void singleRound() {
        rollForInitiative();

        attack();
        swapRoles();
        attack();
        swapRoles();
    }

    public void attack() {
        if (isAttackSuccessful(attacker, target)) {
            WeaponBase w = attacker.getSelectedWeapon();
            int extraDamage = attacker.getStatModifier(w.getWeaponProficiencyStat());
            int totalDmg = w.rollDamage() + extraDamage;
            System.out.println(attacker.getName() + " deals " + totalDmg + " damage");

            int newHp = target.getHitPoints() - totalDmg;
            target.setHitPoints(newHp);
        } else {
            System.out.println(attacker.getName() + "'s attack failed");
        }
    }

    private int rollInitiative(int statModifier) {
        return d20.roll() + statModifier;
    }

    private void swapRoles() {
        Character tmp = attacker;
        attacker = target;
        target = tmp;
        System.out.println("roles swapped");
    }
}
