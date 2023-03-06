package backend.artifacts.weapons.ranged;

import backend.artifacts.weapons.RangedFinesseWeapon;
import backend.logic.Dice;

public class Bow extends RangedFinesseWeapon {
    public Bow(){
        super(new Dice(8),6);
    }
}

