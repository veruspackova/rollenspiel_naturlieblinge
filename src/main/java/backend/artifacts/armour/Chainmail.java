package backend.artifacts.armour;

public class Chainmail implements BaseArmour {
    @Override
    public int getAcBonus() {
        return 6;
    }

    @Override
    public int getRequiredStrength() {
        return 13;
    }
}
