import backend.artifacts.items.Item;
import backend.artifacts.items.magicpotions.HealingPotion;
import backend.artifacts.items.magicpotions.InvisibilityPotion;
import backend.character.Character;
import backend.character.Fighter;
import backend.character.Monster;
import backend.character.Thief;
import backend.enums.Race;
import backend.gameBoard.GameBoard;
import backend.input.InputClass;
import backend.logic.CharacterCreator;
import backend.logic.GameRoundLogic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static GameBoard gameBoard;
    static InputClass input;
    static List<Character> characterList = new ArrayList<>();
    static List<Monster> monsterList = new ArrayList<>();

    public static void main(String[] args) {
        input = new InputClass(new BufferedReader(new InputStreamReader(System.in)));
        init();
//        testInit();
        gameBoard.printLegend();
        while (true) {
            run();
        }
    }

    public static void init() {
        System.out.println("How many people are playing? (without the game master)");
        boolean successfulInput = false;
        int characterAmount = 0;
        while (!successfulInput) {
            try {
                characterAmount = Integer.parseInt(input.read().get(0));
                if (characterAmount < 1) {
                    throw new IllegalArgumentException();
                }
                successfulInput = true;
            } catch (Exception e) {
                System.out.println("Invalid. Try again -- please type a whole number!");
            }
        }
        System.out.println("How big do you want your game board to be? (Recommended: 20)");
        successfulInput = false;
        while (!successfulInput) {
            try {
                int size = Integer.parseInt(input.read().get(0));
                gameBoard = new GameBoard(size);
                gameBoard.generateMap();
                successfulInput = true;
            } catch (Exception e) {
                System.out.println("Invalid. Try again -- please type a whole number!");
            }
        }
        CharacterCreator creator = new CharacterCreator(input);
        for (int i = 0; i < characterAmount; i++) {
            Character character = creator.create();
            characterList.add(character);
            gameBoard.placeCharacter(character);
        }
        for (int i = 0; i < gameBoard.size / 2; i++) {
            monsterList.add(gameBoard.placeMonsters());
        }
        //@todo add more field customization
        Character testChar = new Fighter(Race.HUM, "test");
        Character testChar2 = new Thief(Race.HUM, "test");
        Character testChar3 = new Monster("test");
    }

    public static void testInit() {
        gameBoard = new GameBoard(20);
        gameBoard.generateMap();
        HealingPotion i1 = new HealingPotion("healingpotion", "healingpotion");
        InvisibilityPotion i2 = new InvisibilityPotion("InvisibilityPotion", "InvisibilityPotion");
        ArrayList<Item> itemArrayList = new ArrayList<>();
        itemArrayList.add(i1);
        itemArrayList.add(i2);
        Character testChar = new Fighter(Race.HUM, "fighter");
        Character testChar2 = new Thief(Race.HUM, "thief");
        Monster testChar3 = new Monster("monster");
        characterList.add(testChar);
        characterList.add(testChar2);
        monsterList.add(testChar3);
        gameBoard.placeCharacter(testChar);
        gameBoard.placeCharacter(testChar2);
        gameBoard.placeCharacter(testChar3);

    }

    public static void run() {
        for (Character character : characterList) {
            if (character.getClass() != Monster.class) {
                System.out.println(character.getName() + " ist am Zug\n");
                gameBoard.printBoardforPlayer(character);
                GameRoundLogic logic = new GameRoundLogic(character, new BufferedReader(new InputStreamReader(System.in)), gameBoard);
                logic.play();
                if (character.getHitPoints() <= 0) {
                    characterList.remove(character);
                }
                System.out.println("_______________________________________________________________________________________________");
                System.out.println("_______________________________________________________________________________________________");
                monsterList = logic.moveMonsters(monsterList);
            }
        }
    }
}
