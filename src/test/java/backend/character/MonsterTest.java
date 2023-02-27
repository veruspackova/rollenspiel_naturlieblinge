package backend.character;

import backend.enums.Race;
import backend.logic.Dice;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MonsterTest {

    @Test
    void testGetName() {
        Monster m = new Monster("Monster", 15, 13, 14, 12, 10, 10, 10, 10);
        assertEquals("Monster", m.getName());
    }

    @Test
    void testGetHitPoints() {
        Monster m = new Monster("Monster", 15, 13, 14, 12, 10, 10, 10, 10);
        assertEquals(12, m.getHitPoints());
    }

    @Test
    void testGetArmourClass() {
        Monster m = new Monster("Monster", 15, 13, 14, 12, 10, 10, 10, 10);
        assertEquals(10, m.getArmourClass());
    }

    @Test
    void testGetAttackDice() {
        Monster m = new Monster("Monster", 15, 13, 14, 12, 10, 10, 10, 10);
        assertEquals(new Dice(10).getDSize(), m.getAttackDice().getDSize());
    }
}
