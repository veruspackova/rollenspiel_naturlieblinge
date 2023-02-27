package backend.artifacts.items.magicitems;

import backend.artifacts.items.Item;
import backend.character.Character;
import backend.enums.Stat;

public class Amulet implements Item {

    boolean wearing = false;

    public void use(Character character) {
        int ac;

        if (wearing) {
            ac = character.getArmourClass() - 3;
            character.setArmourClass(ac);
        } else {
            ac = character.getArmourClass() + 3;
            character.setArmourClass(ac);
        }
    }
}
