import backend.character.Character;
import backend.character.Fighter;
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
        run();
    }

    public static void init(){
        gameBoard = new GameBoard(20);
        gameBoard.generateMap();
        gameBoard.printBoard();
        Character testChar = new Fighter(Race.HUM,"test", 10,10,10,10,10,null);
        characterList.add(testChar);
        gameBoard.placeCharacter(testChar);
        gameBoard.printBoard();
    }

    public static void run(){
        for(Character character: characterList){
            GameRoundLogic logic = new GameRoundLogic(character, new BufferedReader(new InputStreamReader(System.in)), gameBoard);
            InputClass input = new InputClass(new BufferedReader(new InputStreamReader(System.in)));
            try {
                logic.play(input.read());
            }catch (IOException e){

            }
        }
    }
}