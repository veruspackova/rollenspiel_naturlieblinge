package backend.artifacts.items;

import backend.artifacts.ISearchable;
import backend.character.Character;

import java.util.ArrayList;

public abstract class Item implements ISearchable {

    private final String name;
    private final String description;

    public Item(String name, String description)
    {
        this.name = name;
        this.description = description;
    }
    public abstract void use(Character character);

    public void search(Character player)
    {
        System.out.println(player.getName() + " found item!\n" + this + "\n");
        player.addItem(this);
    }
}
