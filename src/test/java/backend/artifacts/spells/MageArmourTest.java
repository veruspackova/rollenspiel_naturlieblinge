package backend.artifacts.spells;

import backend.artifacts.items.Item;
import backend.artifacts.items.magicitems.Amulet;
import backend.character.Fighter;
import backend.enums.Race;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MageArmourTest {

    @Test
    void testCast() {
        Fighter f = new Fighter(Race.HUM, "Fighter", 15, 13, 14, 12, 10, null, null, null);
        int initAC = f.getArmourClass();
        MageArmour mg = new MageArmour();
        mg.cast(f);
        assertTrue(f.getArmourClass() > initAC);
    }
}
