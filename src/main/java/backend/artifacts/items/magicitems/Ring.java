package backend.artifacts.items.magicitems;

import backend.artifacts.items.Item;
import backend.character.Character;
import backend.enums.Stat;

public class Ring implements Item {

    boolean wearing = false;

    @Override
    public void use(Character character) {
        int intelligence;

        if (wearing) {
            intelligence = character.getStat(Stat.INT) - 3;
            character.setStat(Stat.INT, intelligence);
        } else {
            intelligence = character.getStat(Stat.INT) + 3;
            character.setStat(Stat.INT, intelligence);
        }
    }
}