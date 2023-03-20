package backend.artifacts.items;

import backend.artifacts.items.magicitems.Amulet;
import backend.artifacts.items.magicpotions.HealingPotion;
import backend.character.Fighter;
import backend.enums.Race;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AmuletTest {

    @Test
    void testUse() {
        Fighter f = new Fighter(Race.HUM, "Fighter", 15, 13, 14, 12, 10, null, null, null);
        int initAC = f.getArmourClass();
        Amulet amulet = new Amulet("Test", "Fall");
        amulet.setWearing(false);
        amulet.use(f);
        assertTrue(f.getArmourClass() > initAC);
    }
}