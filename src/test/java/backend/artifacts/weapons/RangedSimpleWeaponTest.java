package backend.artifacts.weapons;

import backend.logic.Dice;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class RangedSimpleWeaponTest {

  @Test
  void diceSizeTooLow() {
    assertThrows(IllegalArgumentException.class, () -> new Dice(0));
    assertThrows(IllegalArgumentException.class, () -> new Dice(-1));
  }

}