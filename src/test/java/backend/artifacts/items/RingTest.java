package backend.artifacts.items;

import backend.artifacts.items.magicitems.Amulet;
import backend.artifacts.items.magicitems.Ring;
import backend.artifacts.items.magicpotions.HealingPotion;
import backend.character.Fighter;
import backend.enums.Race;
import backend.enums.Stat;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RingTest {

    @Test
    void testUse() {
        Fighter f = new Fighter(Race.HUM, "Fighter", 15, 13, 14, 12, 10, null, null, null);
        int initInt = f.getStat(Stat.INT);
        Ring ring = new Ring("Test", "Fall");
        ring.setWearing(false);
        ring.use(f);
        assertTrue(f.getStat(Stat.INT) > initInt);
    }
}