package backend.artifacts.armour;

import backend.enums.Stat;

public class Scalemail implements BaseArmour {
    @Override
    public int getAcBonus() {
        return 4;
    }

    @Override
    public Stat getArmourBonusStat() {
        return Stat.DEX;
    }
}
