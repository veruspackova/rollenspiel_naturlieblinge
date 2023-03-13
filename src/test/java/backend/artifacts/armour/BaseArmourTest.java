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
}