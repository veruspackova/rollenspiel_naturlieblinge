package backend.artifacts.items;

import backend.character.Character;

public interface Item extends ISearchable {

    void use(Character character);
}
