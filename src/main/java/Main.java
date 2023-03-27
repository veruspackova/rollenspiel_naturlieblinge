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
        init();
        while (true){
            run();
        }
    }

    public static void init(){
        gameBoard = new GameBoard(20);
        gameBoard.generateMap();
        Character testChar = new Fighter(Race.HUM,"test");
        Character testChar2 = new Thief(Race.HUM,"test");
        Character testChar3 = new Monster("test");
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
