package backend.logic;

import backend.character.Character;
import backend.enums.Direction;
import backend.enums.GameRoundAction;
import backend.gameBoard.GameBoard;
import backend.gameBoard.RoomField;
import backend.input.InputClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Map;

public class GameRoundLogic {

    private GameBoard gameBoard;
    private Character character;

    private int movecounter;
    private BufferedReader inputReader;

    public GameRoundLogic(Character character, BufferedReader inputReader, GameBoard gameBoard)
    {
        this.inputReader = inputReader;
        this.setCharacter(character);
        this.gameBoard = gameBoard;
        this.movecounter = 2;
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

    public void play() throws InputMismatchException
    {
        while (true) {
            InputClass inputClass = new InputClass(new BufferedReader(new InputStreamReader(System.in)));
            ArrayList<String> input = null;
            try {
                input = inputClass.read();
            }catch (IOException e){

            }
            if(input != null){
                if (input.get(0).equals("fight")) {
                    if (input.get(1) != null && input.get(2) != null && input.get(3) != null
                            && Integer.parseInt(input.get(1)) > 0 && Integer.parseInt(input.get(2)) > 0) {
                        int cordX = Integer.parseInt(input.get(1));
                        int cordY = Integer.parseInt(input.get(2));
                        RoomField roomField = gameBoard.board[cordX][cordY];
                        if (roomField.getCharacter() != null) {
                            if (input.get(3) == null) {
                                //@todo fight round with selected weapon none
                            }
                            //@todo weapon selection
                            //@todo when fightround is implemented
                        } else {
                            System.out.println("You hit nothing");
                            return;
                        }
                    } else {
                        System.out.println("Invalid coordinates.");
                        return;
                    }

                } else if (input.get(0).equals("move")) {
                    if (movecounter > 1) {
                        move(character, 1);
                        gameBoard.printBoardforPlayer(character);
                        movecounter--;
                    }else {
                        move(character, 1);
                        return;
                    }

                    //check if move is possible and allowed (movecounter and wall check)
                    //look on gameplan if where i want to goo is a character and then see if i want to fight them
                    // after looking, move there
                } else if (input.get(0).equals("rest")) {
                    character.rest();
                } else if (input.get(0).equals("search")) {
                    //@todo waiting for isearchable
                } else if (input.get(0).equals("use")) {
                    String item = input.get(1);
                    //@todo waiting for items
                } else if (input.get(0).equals("turn")) {
                    Direction temp = character.getDirection();
                    switch (input.get(1)) {
                        case "north" -> temp = Direction.North;
                        case "west" -> temp = Direction.West;
                        case "south" -> temp = Direction.South;
                        case "east" -> temp = Direction.East;
                        case "northeast" -> temp = Direction.NorthEast;
                        case "northwest" -> temp = Direction.NorthWest;
                        case "southeast" -> temp = Direction.SouthEast;
                        case "southwest" -> temp = Direction.SouthWest;
                    }
                    character.turn(temp);
                } else {
                    System.out.println("Invalid action in game round logic.");
                }
            }
        }
    }

    //Move funktion gets called on input move
    public void move(Character character, int distance){
        for (int i = 0; i < distance; i++) {
            RoomField target = null;
            RoomField current = character.getPosition();
            ArrayList<Integer> cords = current.getCoordinates();

            switch (character.getDirection()) {
                case North:
                    target = gameBoard.board[cords.get(0) - 1][cords.get(1)];
                    break;
                case NorthEast:
                    target = gameBoard.board[cords.get(0) - 1][cords.get(1) + 1];
                    break;
                case East:
                    target = gameBoard.board[cords.get(0)][cords.get(1) + 1];
                    break;
                case SouthEast:
                    target = gameBoard.board[cords.get(0) + 1][cords.get(1) + 1];
                    break;
                case South:
                    target = gameBoard.board[cords.get(0) + 1][cords.get(1)];
                    break;
                case SouthWest:
                    target = gameBoard.board[cords.get(0) + 1][cords.get(1) - 1];
                    break;
                case West:
                    target = gameBoard.board[cords.get(0)][cords.get(1) - 1];
                    break;
                case NorthWest:
                    target = gameBoard.board[cords.get(0) - 1][cords.get(1) - 1];
                    break;
            }
            if (moveToTarget(character, target, current) == false) {
                i = distance;
                System.out.println("Invalid move. Something is in the way.");
            }
        }
    }
    //moves the character and updates references
    public boolean moveToTarget(Character character, RoomField target, RoomField current){
        if(target != null){
            current.setCharacter(null);
            target.setCharacter(character);
            character.setPosition(target);
            return true;
        }else return false;
    }
}