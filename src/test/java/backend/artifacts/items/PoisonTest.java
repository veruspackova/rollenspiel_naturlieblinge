package backend.artifacts.items;

import backend.artifacts.items.magicpotions.Poison;
import backend.character.Fighter;
import backend.enums.Race;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PoisonTest {

    @Test
    void testUse() {
        Fighter f = new Fighter(Race.HUM, "Fighter", 15, 13, 14, 12, 10, new ArrayList<Item>());
        int initHP = f.getHitPoints();
        Poison poison = new Poison();
        poison.use(f);
        assertTrue(f.getHitPoints() < initHP);
    }
}