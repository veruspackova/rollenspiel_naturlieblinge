package backend.artifacts.armour;

public class Chainmail extends BaseArmour {
    @Override
    public int getAcBonus() {
        return 6;
    }

    @Override
    public int getRequiredStrength() {
        return 13;
    }
}
