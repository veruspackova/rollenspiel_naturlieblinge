package backend.searchable;

import backend.artifacts.armour.BaseArmour;
import backend.artifacts.items.Item;
import backend.artifacts.items.magicitems.Amulet;
import backend.artifacts.weapons.WeaponBase;
import backend.artifacts.weapons.melee.NoWeapon;
import backend.character.Fighter;
import backend.enums.Race;
import backend.input.InputClass;
import backend.logic.Dice;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ISearchableTest {

    @Test
    void search_selectNewWeapon()
    {
        ArrayList<String> playerInput = new ArrayList<>();
        playerInput.add("yes");
        playerInput.add("please");

        WeaponBase searchedWeapon = new WeaponBase(new Dice(6), new TestInput(playerInput)){};
        NoWeapon selectedWeapon = new NoWeapon();

        Fighter fighter = new Fighter(Race.HOB, "fighter", 15, 12, 14, 8, 10, new ArrayList<>(), selectedWeapon, new ArrayList<>());
        assertEquals(selectedWeapon, fighter.getSelectedWeapon());

        searchedWeapon.search(fighter);
        assertEquals(searchedWeapon, fighter.getSelectedWeapon());
    }

    @Test
    void search_noSelectWeapon()
    {
        ArrayList<String> playerInput = new ArrayList<>();
        playerInput.add("no");
        playerInput.add("thank");
        playerInput.add("you");

        WeaponBase searchedWeapon = new WeaponBase(new Dice(6), new TestInput(playerInput)){};
        NoWeapon selectedWeapon = new NoWeapon();

        Fighter fighter = new Fighter(Race.HOB, "fighter", 15, 12, 14, 8, 10, new ArrayList<>(), selectedWeapon, new ArrayList<>());
        assertEquals(selectedWeapon, fighter.getSelectedWeapon());

        searchedWeapon.search(fighter);
        assertEquals(selectedWeapon, fighter.getSelectedWeapon());
    }

    @Test
    void search_wearNewArmour() {

        ArrayList<String> playerInput = new ArrayList<>();
        playerInput.add("yes");
        playerInput.add("please");

        BaseArmour armour = new BaseArmour(new TestInput(playerInput)) {
            @Override
            public int getAcBonus() {
                return 10;
            }
        };

        Fighter fighter = new Fighter(Race.HOB, "fighter", 15, 12, 14, 8, 10, new ArrayList<>(), new NoWeapon(), new ArrayList<>());
        assertEquals(0, fighter.getArmour().getAcBonus());

        armour.search(fighter);
        assertEquals(10, fighter.getArmour().getAcBonus());
    }

    @Test
    void search_noArmourChange() {

        ArrayList<String> playerInput = new ArrayList<>();
        playerInput.add("no");
        playerInput.add("thank");
        playerInput.add("you");

        BaseArmour armour = new BaseArmour(new TestInput(playerInput)) {
            @Override
            public int getAcBonus() {
                return 10;
            }
        };

        Fighter fighter = new Fighter(Race.HOB, "fighter", 15, 12, 14, 8, 10, new ArrayList<>(), new NoWeapon(), new ArrayList<>());
        assertEquals(0, fighter.getArmour().getAcBonus());

        armour.search(fighter);
        assertEquals(0, fighter.getArmour().getAcBonus());
    }

    @Test
    void item_searchTest()
    {
        Item item = new Amulet();

        Fighter fighter = new Fighter(Race.HOB, "fighter", 15, 12, 14, 8, 10, new ArrayList<>(), new NoWeapon(), new ArrayList<>());
        assertEquals(new ArrayList<Item>(), fighter.getItems());

        item.search(fighter);
        ArrayList<Item> searchedArray = new ArrayList<Item>();
        searchedArray.add(item);
        assertEquals(searchedArray, fighter.getItems());
    }

    private static class TestInput extends InputClass {
        private final ArrayList<String> playerInput;

        public TestInput(ArrayList<String> playerInput) {
            super(new BufferedReader(new InputStreamReader(System.in)));
            this.playerInput = playerInput;
        }

        @Override
        public ArrayList<String> read() throws IOException {
            return new ArrayList<>(playerInput);
        }
    }
}
