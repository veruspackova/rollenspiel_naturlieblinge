package backend.artifacts.armour;

import backend.artifacts.weapons.melee.NoWeapon;
import backend.character.Fighter;
import backend.enums.Race;
import backend.enums.Stat;
import backend.input.InputClass;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BaseArmourTest {

    @Test
    void getAcBonus() {
        BaseArmour armour = new LeatherArmour();
        assertEquals(1, armour.getAcBonus());
    }

    @Test
    void getArmourBonusStat() {
        BaseArmour armour = new LeatherArmour();
        assertEquals(Stat.DEX, armour.getArmourBonusStat());
    }

    @Test
    void getRequiredStrength() {
        BaseArmour armour = new LeatherArmour();
        assertEquals(0, armour.getRequiredStrength());
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