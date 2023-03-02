package backend.logic;

import backend.character.Character;
import backend.enums.Direction;
import backend.enums.GameRoundAction;
import backend.gameBoard.GameBoard;
import backend.gameBoard.RoomField;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class GameRoundLogic {

    private GameBoard gameBoard;
    private Character character;

    private int movecounter = 2;
    private BufferedReader inputReader;

    public GameRoundLogic(Character character, BufferedReader inputReader, GameBoard gameBoard)
    {
        this.inputReader = inputReader;
        this.setCharacter(character);
        this.gameBoard = gameBoard;
    }

    public GameBoard getGameBoard() {
        return this.gameBoard;
    }

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public void play2(GameRoundAction action) throws IOException {
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

    public void play(ArrayList<String> input) throws InputMismatchException
    {
        if (input.get(0) == "fight") {
            if (input.get(1) != null && input.get(2) != null && input.get(3) != null
                    && Integer.parseInt(input.get(1)) > 0 && Integer.parseInt(input.get(2)) > 0)
            {
                int cordX = Integer.parseInt(input.get(1));
                int cordY = Integer.parseInt(input.get(2));
                RoomField roomField = gameBoard.board[cordX][cordY];
                if (roomField.getCharacter() != null)
                {
                    if (input.get(3) == null)
                    {
                        //@todo fight round with selected weapon none
                    }
                    //@todo weapon selection
                    //@todo when fightround is implemented

                }
                else
                {
                    System.out.println("You hit nothing");
                }
            }
            else
            {
                throw new InputMismatchException("Invalid coordinates.");
            }

        } else if (input.get(0) == "move") {
            if (movecounter > 0)
            {
                //@todo waiting for position on roomfield
                //@todo check for doors or walls in the way
                //@todo set old fields empty
                //gameBoard.board character.getPosition()
                if (input.get(1) != null && input.get(2) != null
                        && Integer.parseInt(input.get(1)) > 0 && Integer.parseInt(input.get(2)) > 0)
                {
                    int cordX = Integer.parseInt(input.get(1));
                    int cordY = Integer.parseInt(input.get(2));
                    RoomField roomField = gameBoard.board[cordX][cordY];
                    if (roomField != null)
                    {
                        character.setPosition(roomField);
                    }
                }
                else {
                    throw new InputMismatchException("Invalid coordinates");
                }
            }
            else {
                System.out.println("Move not allowed cuz movecounter is 0");
            }


            //check if move is possible and allowed (movecounter and wall check)
            //look on gameplan if where i want to goo is a character and then see if i want to fight them
            // after looking, move there
        } else if (input.get(0) == "rest") {
            character.rest();
        } else if (input.get(0) == "search") {
            //@todo waiting for isearchable
        } else if (input.get(0) == "use") {
            String item = input.get(1);
            //@todo waiting for items
        } else if (input.get(0) == "turn") {
            Direction temp = character.getDirection();
            switch (input.get(1)) {
                case "north" -> temp = Direction.North;
                case "west"  -> temp = Direction.West;
                case "south" -> temp = Direction.South;
                case "east"  -> temp = Direction.East;
            }
            character.turn(temp);
        } else {
            throw new InputMismatchException("Invalid action in game round logic.");
        }
    }
}
