package backend.logic;

import backend.enums.Race;
import backend.enums.Stat;
import backend.input.InputClass;
import backend.character.Character;
import backend.character.*;

import java.util.HashMap;
import java.util.Map;

public class CharacterCreator {

    final InputClass inputClass;

    public CharacterCreator(InputClass input)
    {
        this.inputClass = input;
    }

    public Character create()
    {
        System.out.println("Character creation");
        String characterClass = this.getCharacterClass();
        String characterName = this.getCharacterName();
        Race characterRace = this.getCharacterRace();
        Map<Stat, Integer> characterStats = this.getCharacterStats();
        Character character = switch (characterClass) {
            case "fighter" -> new Fighter(characterRace, characterName, characterStats.get(Stat.STR), characterStats.get(Stat.DEX), characterStats.get(Stat.CON), characterStats.get(Stat.INT), characterStats.get(Stat.WIS));
            case "thief" -> new Thief(characterRace, characterName, characterStats.get(Stat.STR), characterStats.get(Stat.DEX), characterStats.get(Stat.CON), characterStats.get(Stat.INT), characterStats.get(Stat.WIS));
            case "wizard" -> new Wizard(characterRace, characterName, characterStats.get(Stat.STR), characterStats.get(Stat.DEX), characterStats.get(Stat.CON), characterStats.get(Stat.INT), characterStats.get(Stat.WIS));
            default -> new Fighter(Race.NONE, "Invalid");
        };

        return character;
    }

    private String getCharacterClass()
    {
        System.out.println("What type of character do you want to create?");
        String characterClass = "";
        while (true) {
            try {
                characterClass = inputClass.read().get(0);
            } catch (Exception e) {
                System.out.println("Invalid input.");
            }
            switch (characterClass) {
                case "fighter", "thief", "wizard" -> {
                    return characterClass;
                }
                default -> System.out.println("This is not an available class.");

            }
        }
    }

    private Race getCharacterRace()
    {
        boolean successfulInput = false;
        System.out.println("What race do you want your character to be?");
        Race characterRace = Race.NONE;
        while (!successfulInput) {
            try {
                String raceString = inputClass.read().get(0);
                switch (raceString) {
                    case "human" -> {
                        characterRace = Race.HUM;
                        successfulInput = true;
                    }
                    case "dwarf" -> {
                        characterRace = Race.DWA;
                        successfulInput = true;
                    }
                    case "elf" -> {
                        characterRace = Race.ELF;
                        successfulInput = true;
                    }
                    case "hobbit" -> {
                        characterRace = Race.HOB;
                        successfulInput = true;
                    }
                    default -> System.out.println("Invalid race. Please enter a valid race");
                }
            } catch (Exception e) {
                System.out.println("Invalid input.");
            }
        }
        return characterRace;
    }

    private String getCharacterName()
    {
        boolean successfulInput = false;
        System.out.println("What do you want to name your character?");
        String characterName = "";
        while (!successfulInput) {
            try {
                characterName = inputClass.read().get(0);
                successfulInput = true;
            } catch (Exception e) {
                System.out.println("Invalid input.");
            }
        }
        return characterName;
    }

    private Map<Stat, Integer> getCharacterStats()
    {
        //15,14,13,12,11
        Map<Stat, Integer> statMap = new HashMap<>();
        for (int i = 15; i > 10; i--) {
            boolean successfulInput = false;
            System.out.println("Where do you want to allocate " + i + " points?");
            while (!successfulInput) {
                try {
                    String characterStat = inputClass.read().get(0);
                    switch (characterStat) {
                        case "strength" -> {
                            if (statMap.get(Stat.STR) == null) {
                                statMap.put(Stat.STR, i);
                            } else {
                                i++;
                                System.out.println("You already allocated a value to this stat. Please allocate the points to a different stat.");
                            }
                        }
                        case "dexterity" -> {
                            if (statMap.get(Stat.DEX) == null) {
                                statMap.put(Stat.DEX, i);
                            } else {
                                i++;
                                System.out.println("You already allocated a value to this stat. Please allocate the points to a different stat.");
                            }
                        }
                        case "constitution" -> {
                            if (statMap.get(Stat.CON) == null) {
                                statMap.put(Stat.CON, i);
                            } else {
                                i++;
                                System.out.println("You already allocated a value to this stat. Please allocate the points to a different stat.");
                            }
                        }
                        case "intelligence" -> {
                            if (statMap.get(Stat.INT) == null) {
                                statMap.put(Stat.INT, i);
                            } else {
                                i++;
                                System.out.println("You already allocated a value to this stat. Please allocate the points to a different stat.");
                            }
                        }
                        case "wisdom" -> {
                            if (statMap.get(Stat.WIS) == null) {
                                statMap.put(Stat.WIS, i);
                            } else {
                                i++;
                                System.out.println("You already allocated a value to this stat. Please allocate the points to a different stat.");
                            }
                        }
                        default -> {
                            i++;
                            System.out.println("Please allocate the points.");
                        }
                    }
                    successfulInput = true;
                } catch (Exception e) {
                    System.out.println("Invalid input.");
                }
            }
        }
        return statMap;
    }
}
