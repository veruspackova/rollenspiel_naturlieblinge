package backend.artifacts.items.weaponitems;

import backend.artifacts.items.Item;
import backend.character.Character;

import java.util.ArrayList;

public class Arrow extends Item {

    private int amount;

    public Arrow(String name, String description, int amount)
    {
        super(name, description);
        this.amount = amount;
    }

    public void use(Character character) {
        System.out.println("You look at your arrow. Nothing else happens.");
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void addArrows(int amount)
    {
        setAmount(getAmount() + amount);
    }

    @Override
    public void search(Character player) {
        ArrayList<Item> items =  player.getItems();
        System.out.println(player.getName() + " found " + amount + " arrows!");//Items array was nach arrows durchsucht wird
        for (Item i: items)
        {
            if (i instanceof Arrow)
            {
                ((Arrow) i).addArrows(this.getAmount());
                return;
            }
        }
        player.addItem(this);
    }
}