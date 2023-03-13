package backend.logic;

import backend.artifacts.weapons.WeaponBase;
import backend.character.Character;
import backend.enums.Stat;

public class FightRound {
    private final Dice d20;
    private final Character first;
    private final Character second;

    public FightRound(Dice d20, Character fighter1, Character fighter2) {
        this.d20 = d20;

        int initiative1 = rollInitiative(fighter1.getStatModifier(Stat.DEX));
        int initiative2 = rollInitiative(fighter2.getStatModifier(Stat.DEX));

        if (Integer.max(initiative1, initiative2) == initiative1) {
            this.first = fighter1;
            this.second = fighter2;
        } else {
            this.first = fighter2;
            this.second = fighter1;
        }
    }

    public FightRound(Character fighter1, Character fighter2) {
        this(new Dice(20), fighter1, fighter2);
    }

    public void fightToTheDeath() {
        while (second.getHitPoints() > 0 && first.getHitPoints() > 0) {
            attack(first, second);
            attack(second, first);
        }
    }

    private void attack(Character attacker, Character target) {
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

//    private Collection<Character> calculateInitiative() {
//        Map<Integer, Character> fightOrderList = new TreeMap<>(Comparator.reverseOrder());
//        ArrayList<Character> characters = new ArrayList<>();
//        characters.add(character1);
//        characters.add(character2);
//
//        for (Character c : characters) {
//            int dex = c.getStatModifier(Stat.DEX);
//            int initiative = rollInitiative(dex);
//            boolean positionFound = false;
//
//            while (!positionFound) {
//                if (!fightOrderList.containsKey(initiative)) {
//                    fightOrderList.put(initiative, c);
//                    positionFound = true;
//                } else {
//                    initiative = rollInitiative(dex);
//                }
//            }
//        }
//
//        return fightOrderList.values();
//    }

    public boolean isAttackSuccessful(Character attacker, Character target) {
        int bonus = attacker.getStatModifier(attacker.getSelectedWeapon().getWeaponProficiencyStat());
        int attackRoll = d20.roll() + bonus;
        int targetDefence = target.getArmourClass() + target.getArmour().getAcBonus();
        return attackRoll > targetDefence;
    }

    private int rollInitiative(int statModifier) {
        return d20.roll() + statModifier;
    }


}
