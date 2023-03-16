package backend.artifacts.armour;

import backend.artifacts.ISearchable;
import backend.character.Character;
import backend.enums.Stat;
import backend.input.InputClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public abstract class BaseArmour implements ISearchable {
    protected final InputClass inputClass;

    public BaseArmour() {
        inputClass = new InputClass(new BufferedReader(new InputStreamReader(System.in)));
    }

    public BaseArmour(InputClass inputClass) {
        this.inputClass = inputClass;
    }

    public abstract int getAcBonus();

    public Stat getArmourBonusStat() {
        return Stat.NONE;
    }

    public int getRequiredStrength() {
        return 0;
    }

    @Override
    public void pickUpItem(Character player) {
        ArrayList<String> input;
        boolean wearNewArmour;

        System.out.println(player.getName() + " found armour!\n" + this + "\n");
        System.out.println(player.getName() + " is currently wearing " + player.getArmour().getClass().getSimpleName());

        System.out.println("Would you like " + player.getName() + " to put on " + this.getClass().getSimpleName() + "?");
        try {
            input = inputClass.read();
            String playerSelection = input.get(0).substring(0, 1).toLowerCase();
            wearNewArmour = playerSelection.equals("y");
        } catch (IOException e) {
            wearNewArmour = false;
        }

        if (wearNewArmour) {
            player.setArmour(this);
            System.out.println(player.getName() + " is now wearing armour: " + this.getClass().getSimpleName());
        } else {
            System.out.println(player.getName() + " did not change their armour");
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "\nAC: " + getAcBonus() + "\nUses Stat: " + getArmourBonusStat() + "\nRequires Strength: " + getRequiredStrength();
    }
}
