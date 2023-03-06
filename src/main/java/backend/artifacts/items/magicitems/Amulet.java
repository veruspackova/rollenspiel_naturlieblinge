package backend.artifacts.items.magicitems;

import backend.artifacts.items.Item;
import backend.character.Character;

public class Amulet implements Item {

    boolean wearing = false;

    public void use(Character character) {
        int ac;

        if (wearing) {
            ac = character.getArmourClass() - 3;
            character.setArmourClass(ac);
            wearing = false;
        } else {
            ac = character.getArmourClass() + 3;
            character.setArmourClass(ac);
            wearing = true;
        }
    }

    public void setWearing(boolean wearing) {
        this.wearing = wearing;
    }

    public boolean isWearing() {
        return wearing;
    }
}
