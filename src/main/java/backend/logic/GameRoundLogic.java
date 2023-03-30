package backend.logic;

import backend.artifacts.ISearchable;
import backend.artifacts.armour.BaseArmour;
import backend.artifacts.items.Item;
import backend.artifacts.items.weaponitems.Arrow;
import backend.artifacts.weapons.RangedSimpleWeapon;
import backend.artifacts.weapons.WeaponBase;
import backend.artifacts.weapons.ranged.Bow;
import backend.character.Character;
import backend.character.Monster;
import backend.enums.Direction;
import backend.enums.GameRoundAction;
import backend.gameBoard.GameBoard;
import backend.gameBoard.RoomField;
import backend.input.InputClass;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;

public class GameRoundLogic {

    public GameBoard gameBoard;
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

    private RoomField getFacingPosition(int distance) {
        RoomField current = character.getPosition();
        return getNextFieldByDirection(current, character.getDirection(), distance);
    }

    private RoomField getFacingPosition() {
        RoomField current = character.getPosition();
        return getNextFieldByDirection(current, character.getDirection(), 1);
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

    public boolean move(Character character, Direction direction) {
        RoomField current = character.getPosition();
        RoomField target = getTargetRoom(current, direction);
        if (target.getCharacter() != null) {
            FightRound fightRound = new FightRound(character, target.getCharacter());
            fightRound.fightToTheDeath();
            if(character.getHitPoints() <= 0){
                return false;
            }
        }
        if (moveToTarget(character, target, current) == false) {
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

    //returns the room that is in the direction of given room
    public RoomField getTargetRoom(RoomField current, Direction direction) {
        RoomField target = null;
        ArrayList<Integer> cords = current.getCoordinates();

        switch (direction) {
            case North:
                target = gameBoard.getBoard()[cords.get(0) - 1][cords.get(1)]; //@todo use getRoomFieldByCoordinates once Oleas Branch is merged
                break;
            case NorthEast:
                target = gameBoard.getBoard()[cords.get(0) - 1][cords.get(1) + 1]; //@todo use getRoomFieldByCoordinates once Oleas Branch is merged
                break;
            case East:
                target = gameBoard.getBoard()[cords.get(0)][cords.get(1) + 1]; //@todo use getRoomFieldByCoordinates once Oleas Branch is merged
                break;
            case SouthEast:
                target = gameBoard.getBoard()[cords.get(0) + 1][cords.get(1) + 1]; //@todo use getRoomFieldByCoordinates once Oleas Branch is merged
                break;
            case South:
                target = gameBoard.getBoard()[cords.get(0) + 1][cords.get(1)]; //@todo use getRoomFieldByCoordinates once Oleas Branch is merged
                break;
            case SouthWest:
                target = gameBoard.getBoard()[cords.get(0) + 1][cords.get(1) - 1]; //@todo use getRoomFieldByCoordinates once Oleas Branch is merged
                break;
            case West:
                target = gameBoard.getBoard()[cords.get(0)][cords.get(1) - 1]; //@todo use getRoomFieldByCoordinates once Oleas Branch is merged
                break;
            case NorthWest:
                target = gameBoard.getBoard()[cords.get(0) - 1][cords.get(1) - 1]; //@todo use getRoomFieldByCoordinates once Oleas Branch is merged
                break;
        }
        return target;
    }

    public void useItem(Character character, String itemName) {
        for (Item item : character.getItems()) {
            if (item.name.equals(itemName)) {
                item.use(character);
            }
        }
    }

    public void search(Character character) {
        RoomField field = character.getPosition();
        for (ISearchable item : field.getItemList()) {
            item.pickUpItem(character);
        }
    }

    public RoomField getNextFieldByDirection(RoomField currentField, Direction direction, int distance) {
        ArrayList<Integer> cords = currentField.getCoordinates();

        return switch (direction) {
            case North -> gameBoard.board[cords.get(0) - distance][cords.get(1)];
            case NorthEast -> gameBoard.board[cords.get(0) - distance][cords.get(1) + distance];
            case East -> gameBoard.board[cords.get(0)][cords.get(1) + distance];
            case SouthEast -> gameBoard.board[cords.get(0) + distance][cords.get(1) + distance];
            case South -> gameBoard.board[cords.get(0) + distance][cords.get(1)];
            case SouthWest -> gameBoard.board[cords.get(0) + distance][cords.get(1) - distance];
            case West -> gameBoard.board[cords.get(0)][cords.get(1) - distance];
            case NorthWest -> gameBoard.board[cords.get(0) - distance][cords.get(1) - distance];
        };
    }


    public void play() throws InputMismatchException {
        while (true) {
//            int test;
//            try {
//                test = System.in.read();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
            InputClass inputClass = new InputClass(new BufferedReader(new InputStreamReader(System.in)));
            ArrayList<String> input = null;
            try {
                input = inputClass.read();
            } catch (IOException e) {

            }
            switch (input != null ? input.get(0) : "rest") { //@todo maybe make this better readable
                case "fight":
                    RoomField fieldToAttack = getFacingPosition();
                    if (input.size() == 2) {
                        int targetDistance = Integer.parseInt(input.get(1));
                        WeaponBase w = character.getSelectedWeapon();
                        if (w instanceof Bow)
                        {
                            ArrayList<Item> items =  character.getItems();
                            for (Item i: items)
                            {
                                if (i instanceof Arrow)
                                {
                                    if (((Arrow) i).getAmount() <= 0)
                                    {
                                        System.out.println("You don't have any arrows left to shoot");
                                        return;
                                    }
                                }
                            }
                        }
                        if (w instanceof RangedSimpleWeapon && ((RangedSimpleWeapon) w).getRange() >= targetDistance) {
                            fieldToAttack = getFacingPosition(targetDistance);
                            if (input.get(1) != null && input.get(2) != null && input.get(3) != null
                                    && Integer.parseInt(input.get(1)) > 0 && Integer.parseInt(input.get(2)) > 0) {
                                int cordX = Integer.parseInt(input.get(1));
                                int cordY = Integer.parseInt(input.get(2));
                                RoomField roomField = gameBoard.getBoard()[cordX][cordY]; //@todo use getRoomFieldByCoordinates once Oleas Branch is merged
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
                            }

                            if (fieldToAttack != null && fieldToAttack.getCharacter() != null) {
                                Character target = fieldToAttack.getCharacter();
                                FightRound fightRound = new FightRound(character, target);
                                fightRound.singleRound();
                            } else {
                                System.out.println("Invalid coordinates.");
                                return;
                            }
                        }
                    }
                    break;
                case "w":
                    if (movecounter > 1) {
                        boolean success = move(character);
                        if (success) {
                            movecounter--;
                            gameBoard.printBoardforPlayer(character);
                        }
                    } else {
                        move(character);

                        boolean success = move(character, Direction.North);
                        if (success == true) {
                            movecounter--;
                            gameBoard.printBoardforPlayer(character);
                        }
                    }
                    break;
                case "a":
                    if (movecounter > 1) {
                        boolean success = move(character, Direction.West);
                        if (success == true) {
                            movecounter--;
                            gameBoard.printBoardforPlayer(character);
                        }
                    } else {
                        move(character, Direction.West);
                        return;
                    }
                    break;
                case "s":
                    if (movecounter > 1) {
                        boolean success = move(character, Direction.South);
                        if (success == true) {
                            movecounter--;
                            gameBoard.printBoardforPlayer(character);
                        }
                    } else {
                        move(character, Direction.South);
                        return;
                    }
                    break;
                case "d":
                    if (movecounter > 1) {
                        boolean success = move(character, Direction.East);
                        if (success == true) {
                            movecounter--;
                            gameBoard.printBoardforPlayer(character);
                        }
                    } else {
                        move(character, Direction.East);
                        return;
                    }
                    break;
                case "r":
                    character.rest();
                    break;
                case "e":
                    search(character);
                    break;
                case "use":
                    useItem(character, input.get(1));
                    break;
                case "i":
                    System.out.println("Itemlist of " + character.getName() + ":");
                    if (character.getItems() == null) {
                        System.out.println("There are no items in your pocket");
                        break;
                    }
                    for (Item item : character.getItems()) {
                        System.out.println(item.name);
                    }
                    break;
                case "q":
                    //@todo waiting for items
                    break;
                case "turn":
                    Direction temp = character.getDirection();
                    if (input.size() != 2) {
                        System.out.println("please enter a direction!");
                        break;
                    }

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
                case "help":
                    gameBoard.printLegend();
                    break;
            }
            if(character.getHitPoints() <= 0){
                break;
            }
        }
    }

    public List<Monster> moveMonsters(List<Monster> monsterList){
        for(Monster monster : monsterList){
            if(monster == null){
                monsterList.remove(monster);
            }else{
                int rng = (int) (Math.random()*8);
                Direction dir = Direction.values()[rng];
                move(monster, dir);
            }
        }
        return monsterList;
    }
}
