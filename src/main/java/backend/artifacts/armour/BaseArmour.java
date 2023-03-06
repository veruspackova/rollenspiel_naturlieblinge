package backend.artifacts.armour;

import backend.enums.Stat;

public interface BaseArmour {
    int getAcBonus();

    default Stat getArmourBonusStat() {
        return Stat.NONE;
    }

    default int getRequiredStrength() {
        return 0;
    }
}
