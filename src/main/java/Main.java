import backend.artifacts.items.Item;
import backend.artifacts.items.magicpotions.HealingPotion;
import backend.artifacts.items.magicpotions.InvisibilityPotion;
import backend.character.Character;
import backend.character.Fighter;
import backend.character.Monster;
import backend.character.Thief;
import backend.enums.Race;
import backend.gameBoard.GameBoard;
import backend.gameBoard.RoomField;
import backend.input.InputClass;
import backend.logic.GameRoundLogic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static GameBoard gameBoard;
    static List<Character> characterList = new ArrayList<>();
    public static void main(String[] args) {
        testInit();
        while (true){
            run();
        }
    }

    public

    public static void testInit(){
        gameBoard = new GameBoard(20);
        gameBoard.generateMap();
        HealingPotion i1 = new HealingPotion("healingpotion", "healingpotion");
        InvisibilityPotion i2 = new InvisibilityPotion("InvisibilityPotion", "InvisibilityPotion");
        ArrayList<Item> itemArrayList = new ArrayList<>();
        itemArrayList.add(i1);
        itemArrayList.add(i2);
        Character testChar = new Fighter(Race.HUM,"test", 10,10,10,10,10,itemArrayList, null, null);
        Character testChar2 = new Thief(Race.HUM,"test", 10,10,10,10,10,null, null, null);
        Character testChar3 = new Monster("test", 10,10,10,10,10,10, 10, 10, null, null, null);
        characterList.add(testChar);
        characterList.add(testChar2);
        characterList.add(testChar3);
        gameBoard.placeCharacter(testChar);
        gameBoard.placeCharacter(testChar2);
        gameBoard.placeCharacter(testChar3);

    }

    public static void run(){
        for(Character character: characterList){
            if(character.getClass() != Monster.class){
                System.out.println(character.getName() + " ist am Zug\n");
                gameBoard.printBoardforPlayer(character);
                GameRoundLogic logic = new GameRoundLogic(character, new BufferedReader(new InputStreamReader(System.in)), gameBoard);
                logic.play();
                System.out.println("_______________________________________________________________________________________________");
                System.out.println("_______________________________________________________________________________________________");
            }
        }
    }
}
