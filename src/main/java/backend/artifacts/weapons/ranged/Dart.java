package backend.artifacts.weapons.ranged;

import backend.artifacts.weapons.RangedFinesseWeapon;
import backend.logic.Dice;

    public class Dart extends RangedFinesseWeapon {
    public Dart(){
        super(new Dice (4),2);
    }
}
