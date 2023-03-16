package backend.artifacts.weapons;

import backend.logic.Dice;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class RangedFinesseWeaponTest {

  @Test
  void diceSizeTooLow() {
  assertThrows(IllegalArgumentException.class, () -> new Dice(0));
  assertThrows(IllegalArgumentException.class, () -> new Dice(-1));
  }

}