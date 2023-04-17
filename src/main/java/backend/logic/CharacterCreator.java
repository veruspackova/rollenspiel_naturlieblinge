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

        return switch (characterClass) {
            case "fighter" -> new Fighter(characterRace, characterName, characterStats.get(Stat.STR), characterStats.get(Stat.DEX), characterStats.get(Stat.CON), characterStats.get(Stat.INT), characterStats.get(Stat.WIS));
            case "thief" -> new Thief(characterRace, characterName, characterStats.get(Stat.STR), characterStats.get(Stat.DEX), characterStats.get(Stat.CON), characterStats.get(Stat.INT), characterStats.get(Stat.WIS));
            case "wizard" -> new Wizard(characterRace, characterName, characterStats.get(Stat.STR), characterStats.get(Stat.DEX), characterStats.get(Stat.CON), characterStats.get(Stat.INT), characterStats.get(Stat.WIS));
            default -> new Fighter(Race.NONE, "Invalid");
        };
    }

    private String getCharacterClass()
    {
        System.out.println("You could be a fighter, thief or wizard...");
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
        System.out.println("You could be a human, dwarf, elf or hobbit...");
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
                    default -> System.out.println("Invalid race. Please enter a valid race (human, dwarf, elf or hobbit)");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please type either human, dwarf, elf or hobbit");
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
        System.out.println("You have 5 stats: strength (str), dexterity (dex), constitution (con), intelligence (int) and wisdom (wis)");
        System.out.println("You can now allocate points to each stat, from largest to smallest amount.");
        System.out.println("Please choose the most important stats first!");

        //15,14,13,12,11
        Map<Stat, Integer> statMap = new HashMap<>();
        for (int i = 15; i > 10; i--) {
            boolean successfulInput = false;
            System.out.println("Where do you want to allocate " + i + " points?");
            while (!successfulInput) {
                try {
                    String characterStat = inputClass.read().get(0);
                    switch (characterStat) {
                        case "str" -> {
                            if (statMap.get(Stat.STR) == null) {
                                statMap.put(Stat.STR, i);
                            } else {
                                i++;
                                System.out.println("You already allocated a value to this stat. Please allocate the points to a different stat.");
                            }
                        }
                        case "dex" -> {
                            if (statMap.get(Stat.DEX) == null) {
                                statMap.put(Stat.DEX, i);
                            } else {
                                i++;
                                System.out.println("You already allocated a value to this stat. Please allocate the points to a different stat.");
                            }
                        }
                        case "con" -> {
                            if (statMap.get(Stat.CON) == null) {
                                statMap.put(Stat.CON, i);
                            } else {
                                i++;
                                System.out.println("You already allocated a value to this stat. Please allocate the points to a different stat.");
                            }
                        }
                        case "int" -> {
                            if (statMap.get(Stat.INT) == null) {
                                statMap.put(Stat.INT, i);
                            } else {
                                i++;
                                System.out.println("You already allocated a value to this stat. Please allocate the points to a different stat.");
                            }
                        }
                        case "wis" -> {
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
