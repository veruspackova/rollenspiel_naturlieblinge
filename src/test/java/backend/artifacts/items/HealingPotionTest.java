package backend.artifacts.items;

import backend.artifacts.items.magicpotions.HealingPotion;
import backend.character.Fighter;
import backend.enums.Race;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HealingPotionTest {

    @Test
    void testUse() {
        Fighter f = new Fighter(Race.HUM, "Fighter", 15, 13, 14, 12, 10, new ArrayList<Item>());
        int initHP = f.getHitPoints();
        HealingPotion healingPotion = new HealingPotion();
        healingPotion.use(f);
        assertTrue(f.getHitPoints() > initHP);
    }
}
