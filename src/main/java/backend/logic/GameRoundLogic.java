package backend.logic;

import backend.character.Character;
import backend.enums.Direction;
import backend.enums.GameRoundAction;
import backend.gameBoard.GameBoard;

import java.io.BufferedReader;
import java.io.IOException;

public class GameRoundLogic {

    private static GameBoard gameBoard;
    private Character character;

    private int movecounter = 2;
    private BufferedReader inputReader;

    public GameRoundLogic(Character character, BufferedReader inputReader)
    {
        this.inputReader = inputReader;
        this.setCharacter(character);
    }

    public static GameBoard getGameBoard() {
        return gameBoard;
    }

    public static void setGameBoard(GameBoard gameBoard) {
        GameRoundLogic.gameBoard = gameBoard;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public void play(GameRoundAction action) throws IOException {
        if(action == GameRoundAction.fight)
        {
            //@todo fight
        }
        else if (action == GameRoundAction.move)
        {
            //check if move is possible and allowed (movecounter and wall check)
            //look on gameplan if where i want to goo is a character and then see if i want to fight them
            // after looking, move there
        }
        else if (action == GameRoundAction.rest)
        {
            //@todo when character can rest
        }
        else if (action == GameRoundAction.search)
        {
            //@todo when character can search
        }
        else if (action == GameRoundAction.use)
        {
            //@todo implement when items are usable
        }
        else if (action == GameRoundAction.turn)
        {
            String tempString = inputReader.readLine();
            tempString = tempString.toLowerCase();
            Direction temp = character.getDirection();
            switch (tempString) {
                case "north" -> temp = Direction.North;
                case "west"  -> temp = Direction.West;
                case "south" -> temp = Direction.South;
                case "east"  -> temp = Direction.East;
            }
            character.turn(temp);
        }
        else
        {
            throw new RuntimeException("Invalid action in game round logic.");
        }
    }
}
