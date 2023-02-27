package backend.logic;

public class TestDie extends Dice {
    public TestDie(int DSize) {
        super(DSize);
    }

    @Override
    public int roll() {
        return super.getDSize();
    }
}