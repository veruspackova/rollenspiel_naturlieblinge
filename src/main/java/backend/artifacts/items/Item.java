package backend.artifacts.items;

import backend.artifacts.ISearchable;
import backend.character.Character;

import java.util.ArrayList;

public interface Item extends ISearchable {

    void use(Character character);

    public default void search(Character player)
    {
        System.out.println(player.getName() + " found item!\n" + this + "\n");
        player.addItem(this);
    }
}
