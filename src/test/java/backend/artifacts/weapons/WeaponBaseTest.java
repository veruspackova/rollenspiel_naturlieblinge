package backend.artifacts.weapons;

import backend.artifacts.weapons.melee.WarAxe;
import backend.enums.Stat;
import backend.logic.Dice;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class WeaponBaseTest {

    @Test
    void diceSizeTooLow() {
        assertThrows(IllegalArgumentException.class, () -> new Dice(0));
        assertThrows(IllegalArgumentException.class, () -> new Dice(-1));
    }

}