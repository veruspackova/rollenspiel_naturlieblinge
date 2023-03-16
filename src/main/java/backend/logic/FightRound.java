package backend.logic;

import backend.artifacts.weapons.WeaponBase;
import backend.character.Character;
import backend.enums.Stat;


public class FightRound {
    private final Dice d20;
    private Character target;
    private Character attacker;

    public FightRound(Dice d20, Character fighter1, Character fighter2) {
        this.d20 = d20;

        int initiative1 = rollInitiative(fighter1.getStatModifier(Stat.DEX));
        int initiative2 = rollInitiative(fighter2.getStatModifier(Stat.DEX));

        if (Integer.max(initiative1, initiative2) == initiative1) {
            this.target = fighter1;
            this.attacker = fighter2;
        } else {
            this.target = fighter2;
            this.attacker = fighter1;
        }
    }

    public FightRound(Character fighter1, Character fighter2) {
        this(new Dice(20), fighter1, fighter2);
    }

    public void fightToTheDeath() {
        while (attacker.getHitPoints() > 0 && target.getHitPoints() > 0) {
            attack();
            swapRoles();
        }
    }

    public void singleRound() {
        attack();
        swapRoles();
        attack();
        swapRoles();
    }

    private void attack() {
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

    // should probably be a separate class
    private boolean isAttackSuccessful(Character attacker, Character target) {
        int bonus = attacker.getStatModifier(attacker.getSelectedWeapon().getWeaponProficiencyStat());
        int attackRoll = d20.roll() + bonus;
        int targetDefence = target.getArmourClass() + target.getArmour().getAcBonus();
        return attackRoll > targetDefence;
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