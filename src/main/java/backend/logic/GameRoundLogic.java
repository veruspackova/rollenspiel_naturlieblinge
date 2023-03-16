package backend.logic;

import backend.artifacts.items.Item;
import backend.character.Character;
import backend.enums.Direction;
import backend.gameBoard.GameBoard;
import backend.gameBoard.RoomField;
import backend.input.InputClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class GameRoundLogic {

    private GameBoard gameBoard;
    private Character character;

    private int movecounter;
    private BufferedReader inputReader;

    public GameRoundLogic(Character character, BufferedReader inputReader, GameBoard gameBoard) {
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

    public void play() throws InputMismatchException {
        while (true) {
            InputClass inputClass = new InputClass(new BufferedReader(new InputStreamReader(System.in)));
            ArrayList<String> input = null;
            try {
                input = inputClass.read();
            } catch (IOException e) {

            }
            switch (input != null ? input.get(0) : "rest") {
                case "fight":
                    RoomField fieldToAttack = getFacingPosition();

                    if (input.size() == 3
                            && Integer.parseInt(input.get(1)) > 0
                            && Integer.parseInt(input.get(2)) > 0) {
                        int cordX = Integer.parseInt(input.get(1));
                        int cordY = Integer.parseInt(input.get(2));
                        fieldToAttack = gameBoard.board[cordX][cordY];
                    }

                    if (fieldToAttack.getCharacter() != null) {
                        Character target = fieldToAttack.getCharacter();
                        FightRound fightRound = new FightRound(character, target);
                        fightRound.singleRound();
                    } else {
                        System.out.println("You hit nothing");
                        return;
                    }
                    break;

                case "move":
                    if (movecounter > 1) {
                        boolean success = move(character);
                        if (success) {
                            movecounter--;
                            gameBoard.printBoardforPlayer(character);
                        }
                    } else {
                        move(character);
                        return;
                    }
                    break;

                case "rest":
                    character.rest();
                    break;

                case "search":
                    //@todo waiting for isearchable
                    break;

                case "use":
                    //@todo waiting for items
                    break;

                case "items":
                    System.out.println("Itemlist of " + character.getName() + ":");
                    for (Item item : character.getItems()) {
                        System.out.println(item);
                    }
                    break;

                case "weapons":
                    //@todo waiting for items
                    break;

                case "turn":
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
                    break;
            }
        }

    }

    private RoomField getFacingPosition() {
        RoomField current = character.getPosition();
        return getNextFieldByDirection(current, character.getDirection());
    }

    //Move funktion gets called on input move
    public boolean move(Character character) {
        RoomField current = character.getPosition();
        RoomField target = getFacingPosition();
        if (!moveToTarget(character, target, current)) {
            System.out.println("Invalid move. Something is in the way.");
            return false;
        }
        return true;
    }

    //moves the character and updates references
    public boolean moveToTarget(Character character, RoomField target, RoomField current) {
        if (target != null) {
            current.setCharacter(null);
            target.setCharacter(character);
            character.setPosition(target);
            return true;
        } else return false;
    }

    public RoomField getNextFieldByDirection(RoomField currentField, Direction direction) {
        ArrayList<Integer> cords = currentField.getCoordinates();

        return switch (direction) {
            case North -> gameBoard.board[cords.get(0) - 1][cords.get(1)];
            case NorthEast -> gameBoard.board[cords.get(0) - 1][cords.get(1) + 1];
            case East -> gameBoard.board[cords.get(0)][cords.get(1) + 1];
            case SouthEast -> gameBoard.board[cords.get(0) + 1][cords.get(1) + 1];
            case South -> gameBoard.board[cords.get(0) + 1][cords.get(1)];
            case SouthWest -> gameBoard.board[cords.get(0) + 1][cords.get(1) - 1];
            case West -> gameBoard.board[cords.get(0)][cords.get(1) - 1];
            case NorthWest -> gameBoard.board[cords.get(0) - 1][cords.get(1) - 1];
        };
    }
}
