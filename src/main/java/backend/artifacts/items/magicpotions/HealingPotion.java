package backend.artifacts.items.magicpotions;

import backend.artifacts.items.Item;
import backend.logic.Dice;
import backend.character.Character;

public class HealingPotion implements Item {

    private Dice dice;

    public HealingPotion(Dice dice) {
        dice = new Dice(4);
    }

    public void use(Character character) {
        int newHP = character.getHitPoints() + dice.roll() + dice.roll();
        character.setHitPoints(newHP);
        System.out.println(character.getName() + " gained " + character.getHitPoints() + " HP.");
    }
}
