package backend.artifacts.items.magicitems;

import backend.artifacts.items.Item;
import backend.character.Character;

public class Cape extends Item {

    boolean wearing = false;

    public Cape(String name, String description) {
        super(name, description);
    }

    @Override
    public void use(Character character) {
//        tbd --> invisibility
    }
}
