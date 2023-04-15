package backend.character;

import backend.artifacts.ISearchable;
import backend.artifacts.armour.BaseArmour;
import backend.artifacts.armour.NoArmour;
import backend.artifacts.items.Item;
import backend.artifacts.items.weaponitems.Arrow;
import backend.artifacts.spells.Spell;
import backend.artifacts.weapons.WeaponBase;
import backend.artifacts.weapons.melee.NoWeapon;
import backend.enums.Direction;
import backend.enums.Race;
import backend.enums.Stat;
import backend.gameBoard.RoomField;
import backend.logic.Dice;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.floor;

public abstract class Character {
    protected WeaponBase selectedWeapon;
    private Race race;
    private String name;
    private RoomField position;
    private Direction direction;
    private int hitPoints;
    private int armourClass;
    private Dice attackDice;
    private Dice hitDice;
    private int hitDiceAvailable;
    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private ArrayList<Item> items;
    private ArrayList<WeaponBase> weapons;
    private BaseArmour armour;

    public Character(
            Race race,
            String name,
            int strength,
            int dexterity,
            int constitution,
            int intelligence,
            int wisdom,
            ArrayList<Item> items,
            WeaponBase selectedWeapon,
            ArrayList<WeaponBase> weapons
    ) {
        setRace(race);
        setName(name);
        setStrength(strength);
        setDexterity(dexterity);
        setConstitution(constitution);
        setIntelligence(intelligence);
        setWisdom(wisdom);
        setItems(items);
        setSelectedWeapon(selectedWeapon);
        setWeapons(weapons);

        setDirection(Direction.North);
        RaceStatBonusHelper bonusHelper = new RaceStatBonusHelper();
        bonusHelper.addStatBonuses(this);
        setDirection(Direction.North);
        setArmour(new NoArmour());
    }

    public Race getRace() {
        return race;
    }

    protected void setRace(Race race) {
        this.race = race;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoomField getPosition() {
        return position;
    }

    public void setPosition(RoomField position) {
        this.position = position;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        if (hitPoints > 0) {
            this.hitPoints = hitPoints;
        } else {
            this.hitPoints = 0;
            characterDies();
        }
    }

    private void characterDies() {
        List<ISearchable> itemList = getPosition().getItemList();

        itemList.addAll(getItems());
        setItems(new ArrayList<>());

        itemList.addAll(getWeapons());
        setWeapons(new ArrayList<>());

        itemList.add(getSelectedWeapon());
        setSelectedWeapon(new NoWeapon());

        itemList.add(getArmour());
        setArmour(new NoArmour());

        getPosition().setItemList(itemList);
        getPosition().setCharacter(null);
        setPosition(null);

        System.out.println(getName() + " died :(");
    }

    public int getArmourClass() {
        return armourClass;
    }

    public void setArmourClass(int armourClass) {
        this.armourClass = armourClass;
    }

    public int getHitDiceAvailable() {
        return hitDiceAvailable;
    }

    public void setHitDiceAvailable(int hitDiceAvailable) {
        this.hitDiceAvailable = hitDiceAvailable;
    }

    /**
     * The table that appears in your class description shows your <b>proficiency bonus</b>,
     * which is +2 for a 1st-level character.
     * Your proficiency bonus applies to many of the numbers you’ll be recording on your character sheet:
     * • Attack rolls using weapons you’re proficient with
     * • Attack rolls with spells you cast
     * • Ability checks using skills you’re proficient in
     * • Ability checks using tools you’re proficient with
     * • Saving throws you’re proficient in
     * • Saving throw DCs for spells you cast (explained in each spellcasting class)
     */
    public int getProficiencyBonus() {
        return 2;
    }

    public boolean getSavingThrowSuccessful(Stat stat, int difficultyClass) {
        Dice d20 = new Dice(20);
        int savingThrow = d20.roll() + getStatModifier(stat);

        return savingThrow >= difficultyClass;
    }

    public int getStat(Stat stat) {
        int value = 0;

        switch (stat) {
            case STR -> value = strength;
            case DEX -> value = dexterity;
            case CON -> value = constitution;
            case INT -> value = intelligence;
            case WIS -> value = wisdom;
            case NONE -> value = 10;
        }

        return value;
    }

    // to determine an ability modifier, subtract 10 from the ability score
    // and then divide the result by 2 (round down)
    public int getStatModifier(Stat stat) {
        return (int) floor((getStat(stat) - 10) / 2.0);
    }

    public void setStat(Stat stat, int value) {
        switch (stat) {
            case STR -> setStrength(value);
            case DEX -> setDexterity(value);
            case CON -> setConstitution(value);
            case INT -> setIntelligence(value);
            case WIS -> setWisdom(value);
        }
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    public Dice getAttackDice() {
        return attackDice;
    }

    public void setAttackDice(Dice attack) {
        this.attackDice = attack;
    }

    public Dice getHitDice() {
        return hitDice;
    }

    public void setHitDice(Dice hitDice) {
        this.hitDice = hitDice;
    }

    public ArrayList<Item> getItems() {
        return this.items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public ArrayList<WeaponBase> getWeapons() {
        return this.weapons;
    }

    public void setWeapons(ArrayList<WeaponBase> weapons) {
        this.weapons = weapons;
    }

    public void addWeapon(WeaponBase weapon) {
        this.weapons.add(weapon);
    }

    public void removeWeapon(WeaponBase weapon) {
        this.weapons.remove(weapon);
    }

    public WeaponBase getSelectedWeapon() {
        return this.selectedWeapon;
    }

    public void setSelectedWeapon(WeaponBase selectedWeapon) {
        if (!(selectedWeapon instanceof Spell) && getStat(Stat.STR) >= selectedWeapon.getRequiredStrength()) {
            this.selectedWeapon = selectedWeapon;
        }
    }

    public void rest() {
        //@todo change after rollins answer
        setHitPoints(hitPoints + 5);
    }

    public void turn(Direction direction) {
        this.setDirection(direction);
    }

    public BaseArmour getArmour() {
        return armour;
    }

    public void setArmour(BaseArmour armour) {
        this.armour = armour;
    }

    public int getArrowCount() {
        int amount = 0;

        for (Item i : getItems()) {
            if (i instanceof Arrow) {
                amount = ((Arrow) i).getAmount();
                break;
            }
        }

        return amount;
    }

    /*public ISearchable search()
    {
        return RoomField.search();
    }*/
}
