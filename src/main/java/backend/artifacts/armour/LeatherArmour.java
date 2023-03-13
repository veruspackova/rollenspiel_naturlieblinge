package backend.artifacts.armour;

import backend.enums.Stat;

public class LeatherArmour extends BaseArmour {
    @Override
    public int getAcBonus() {
        return 1;
    }

    @Override
    public Stat getArmourBonusStat() {
        return Stat.DEX;
    }
}
