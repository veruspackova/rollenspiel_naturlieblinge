package backend.artifacts.items.magicpotions;

import backend.artifacts.items.Item;
import backend.character.Character;

public class Poison extends Item {

    public Poison(String name, String description) {
        super(name, description);
    }

    public void use(Character character) {
        int newHP = character.getHitPoints() - 6;
        character.setHitPoints(newHP);
    }
}