package backend.artifacts.weapons.ranged;

import backend.artifacts.weapons.RangedFinesseWeapon;
import backend.logic.Dice;
import java.util.ArrayList;

    public class Bow extends RangedFinesseWeapon {
    public Bow(){
        super(new Dice(8),6);
    }

        ArrayList<String> arrow = new ArrayList<String>();

        public ArrayList<String> getArrow() {
            return arrow;

        }
    }

