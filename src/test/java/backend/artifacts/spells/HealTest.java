package backend.artifacts.spells;

import backend.character.Fighter;
import backend.enums.Race;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HealTest {

    @Test
    void testCast() {
        Fighter f = new Fighter(Race.HUM, "Fighter", 15, 13, 14, 12, 10, null, null, null);
        int initHP = f.getHitPoints();
        Heal h = new Heal();
        h.cast(f);
        assertTrue(f.getHitPoints() > initHP);
    }
}
