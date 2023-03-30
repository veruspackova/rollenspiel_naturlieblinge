package backend.artifacts.weapons;

import backend.artifacts.ISearchable;
import backend.character.Character;
import backend.enums.Stat;
import backend.input.InputClass;
import backend.logic.Attack;
import backend.logic.Dice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public abstract class WeaponBase implements Attack, ISearchable {
    private final Dice damageDice;
    private final InputClass inputClass;

    public WeaponBase(Dice damageDice) {
        this.damageDice = damageDice;
        inputClass = new InputClass(new BufferedReader(new InputStreamReader(System.in)));
    }

    public WeaponBase(Dice damageDice, InputClass inputClass)
    {
        this.damageDice = damageDice;
        this.inputClass = inputClass;
    }

    public Stat getWeaponProficiencyStat() {
        return Stat.STR;
    }

    public int getRequiredStrength() {
        return 0;
    }

    @Override
    public int rollDamage() {
        return damageDice.roll();
    }

    @Override
    public void pickUpItem(Character player) {
        player.addWeapon(this);
        ArrayList<String> input;
        boolean equipNewWeapon;

        System.out.println(player.getName() + " found weapon!\n" + this + "\n");
        System.out.println(player.getName() + " currently has " + player.getArmour().getClass().getSimpleName() + " equipped.");

        System.out.println("Do you want to equip this weapon?");
        System.out.println("Would you like " + player.getName() + " to equip " + this.getClass().getSimpleName() + "?");
        try {
            input = inputClass.read();
            String playerSelection = input.get(0).substring(0, 1).toLowerCase();
            equipNewWeapon = playerSelection.equals("y");
        } catch (IOException e) {
            equipNewWeapon = false;
        }

        if (equipNewWeapon) {
            player.setSelectedWeapon(this);
            System.out.println(player.getName() + " has now " + this.getClass().getSimpleName() + " equipped.");
        } else {
            System.out.println(player.getName() + " did not change their weapon");
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "\nMAX DMG: " + damageDice.getDSize() + "\nUses Stat: " + getWeaponProficiencyStat() + "\nRequires Strength: " + getRequiredStrength();
    }
}
