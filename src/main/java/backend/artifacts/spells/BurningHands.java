//package backend.artifacts.spells;
//
//import backend.enums.Stat;
//import backend.logic.Attack;
//import backend.logic.Dice;
//import backend.character.Character;
//
//public class BurningHands implements Spell, Attack {
//
//
//    final private Dice dice;
//
//    public BurningHands() { dice = new Dice(6); }
//
//    public void cast(Character target) {
//        int hp = target.getHitPoints() - this.rollDamage();
//        target.setHitPoints(hp);
//    }
//
//    @Override
//    public int rollDamage() {
//        return dice.roll() + dice.roll() + dice.roll();
//    }
//
//    public boolean isAttackSuccessful(int targetAC) {
//        Dice d20 = new Dice(20);
//        return d20.roll() >= targetAC + player.getProficiencyBonus() + player.getStatModifier(Stat.INT);
//    }
//}
