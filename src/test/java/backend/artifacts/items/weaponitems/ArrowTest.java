package backend.artifacts.items.weaponitems;

import backend.artifacts.items.Item;
import backend.artifacts.weapons.WeaponBase;
import backend.artifacts.weapons.melee.NoWeapon;
import backend.character.Fighter;
import backend.enums.Race;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArrowTest {

    @Test
    void searchTest() {
        ArrayList<Item> items = new ArrayList<Item>();
        items.add(new Arrow("a", "arrow", 20));
        Fighter f = new Fighter(Race.NONE, "f", 10, 10, 10, 10, 10, items, new NoWeapon(), new ArrayList<WeaponBase>());
        ArrayList<Item> itemsSearch = f.getItems();
        for (Item i : itemsSearch) {
            if (i instanceof Arrow) {
                assertEquals(20, ((Arrow) i).getAmount());
            }
        }
        Arrow arrowSearch = new Arrow("as", "arrowSearch", 10);
        arrowSearch.pickUpItem(f);
        itemsSearch = f.getItems();
        for (Item i : itemsSearch) {
            if (i instanceof Arrow) {
                assertEquals(30, ((Arrow) i).getAmount());
            }
        }
    }

}