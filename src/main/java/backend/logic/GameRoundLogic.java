package backend.logic;

import backend.artifacts.ISearchable;
import backend.artifacts.items.Item;
import backend.artifacts.spells.Fireball;
import backend.artifacts.spells.RayOfFrost;
import backend.artifacts.spells.Spell;
import backend.artifacts.weapons.RangedSimpleWeapon;
import backend.artifacts.weapons.WeaponBase;
import backend.character.Character;
import backend.character.Wizard;
import backend.enums.Direction;
import backend.enums.Spells;
import backend.gameBoard.GameBoard;
import backend.gameBoard.RoomField;
import backend.input.InputClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;

public class GameRoundLogic {

    private final BufferedReader inputReader;
    private GameBoard gameBoard;
    private Character character;
    private int movecounter;

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

    public void play() throws InputMismatchException {
        while (true) {
            ArrayList<String> input = getInput();

            String playerAction;
            if (input != null) {
                playerAction = input.get(0);
            } else {
                playerAction = "rest";
            }
            switch (playerAction) {
                case "fight":
                    RoomField fieldToAttack = getFacingPosition();
                    if (input.size() == 2) {
                        int targetDistance = Integer.parseInt(input.get(1));
                        WeaponBase w = character.getSelectedWeapon();

                        if (w instanceof Spell) {
                            System.out.println("Spells must use cast, not fight!");
                            break;
                        }

                        if (w instanceof RangedSimpleWeapon && ((RangedSimpleWeapon) w).getRange() >= targetDistance) {
                            fieldToAttack = getFacingPosition(targetDistance);
                            if (input.get(1) != null && input.get(2) != null && input.get(3) != null
                                    && Integer.parseInt(input.get(1)) > 0 && Integer.parseInt(input.get(2)) > 0) {
                                int cordX = Integer.parseInt(input.get(1));
                                int cordY = Integer.parseInt(input.get(2));
                                RoomField roomField = gameBoard.getRoomFieldByCoordinates(cordX, cordY);
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

                case "cast":
                    if (character instanceof Wizard) {
                        if (input.size() != 2) {
                            System.out.println("please cast again and choose a spell number this time");
                            System.out.println(Arrays.toString(Spells.values()));
                            break;
                        } else {
                            Spells spell = Spells.values()[Integer.parseInt(input.get(1))];
                            Spell chosenSpell = spell.createSpell();

                            ArrayList<Character> targets = getAffectedCharacters(character.getPosition(), character.getDirection(), spell);
                            chosenSpell.castSpell((Wizard) character, targets);
                        }

                    } else {
                        System.out.println("only wizards can cast spells!");
                    }
                    break;

                case "move":
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
                        if (success) {
                            movecounter--;
                            gameBoard.printBoardforPlayer(character);
                        }
                    }
                    break;
                case "a":
                    if (movecounter > 1) {
                        boolean success = move(character, Direction.West);
                        if (success) {
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
                        if (success) {
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
                        if (success) {
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
                default:
                    throw new IllegalStateException("Unexpected value: " + playerAction);
            }
        }

    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    //Move function gets called on input move
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
        if (!moveToTarget(character, target, current)) {
            System.out.println("Invalid move. Something is in the way.");
            return false;
        }
        if (target.getCharacter() != null) {
            //@todo fight
        }
        return true;
    }

    //returns the room that is in the direction of given room
    public RoomField getTargetRoom(RoomField current, Direction direction) {
        RoomField target = null;
        ArrayList<Integer> cords = current.getCoordinates();

        target = switch (direction) {
            case North ->
                    gameBoard.getBoard()[cords.get(0) - 1][cords.get(1)]; //@todo use getRoomFieldByCoordinates once Oleas Branch is merged
            case NorthEast ->
                    gameBoard.getBoard()[cords.get(0) - 1][cords.get(1) + 1]; //@todo use getRoomFieldByCoordinates once Oleas Branch is merged
            case East ->
                    gameBoard.getBoard()[cords.get(0)][cords.get(1) + 1]; //@todo use getRoomFieldByCoordinates once Oleas Branch is merged
            case SouthEast ->
                    gameBoard.getBoard()[cords.get(0) + 1][cords.get(1) + 1]; //@todo use getRoomFieldByCoordinates once Oleas Branch is merged
            case South ->
                    gameBoard.getBoard()[cords.get(0) + 1][cords.get(1)]; //@todo use getRoomFieldByCoordinates once Oleas Branch is merged
            case SouthWest ->
                    gameBoard.getBoard()[cords.get(0) + 1][cords.get(1) - 1]; //@todo use getRoomFieldByCoordinates once Oleas Branch is merged
            case West ->
                    gameBoard.getBoard()[cords.get(0)][cords.get(1) - 1]; //@todo use getRoomFieldByCoordinates once Oleas Branch is merged
            case NorthWest ->
                    gameBoard.getBoard()[cords.get(0) - 1][cords.get(1) - 1]; //@todo use getRoomFieldByCoordinates once Oleas Branch is merged
        };
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

    private ArrayList<String> getInput() {
        InputClass inputClass = new InputClass(inputReader);
        ArrayList<String> input = null;
        try {
            System.out.println("what would you like to do?");
            input = inputClass.read();
        } catch (IOException e) {
            // to be implemented
        }
        return input;
    }

    private RoomField getFacingPosition(int distance) {
        RoomField current = character.getPosition();
        return getNextFieldByDirection(current, character.getDirection(), distance);
    }

    private RoomField getFacingPosition() {
        RoomField current = character.getPosition();
        return getNextFieldByDirection(current, character.getDirection());
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

    public ArrayList<Character> getAffectedCharacters(RoomField currentField, Direction direction, Spells spell) {
        ArrayList<Character> affectedCharacters = new ArrayList<>();
        int o = direction.ordinal();
        switch (spell) {
            case BURNING_HANDS -> {
                Direction leftSide = Direction.values()[o - 1];
                Direction rightSide = Direction.values()[o + 1];
                System.out.println("cone: " + direction + " and from " + leftSide + " to " + rightSide);
                affectedCharacters.add(getNextFieldByDirection(currentField, direction).getCharacter());
                affectedCharacters.add(getNextFieldByDirection(currentField, direction, 2).getCharacter());
                affectedCharacters.add(getNextFieldByDirection(currentField, leftSide, 2).getCharacter());
                affectedCharacters.add(getNextFieldByDirection(currentField, rightSide, 2).getCharacter());
            }
            case RAY_OF_FROST -> {
                RayOfFrost s = (RayOfFrost) spell.createSpell();
                int allowedRange = s.getRange();
                int currentX = character.getPosition().getCoordinates().get(0);
                int currentY = character.getPosition().getCoordinates().get(1);
                System.out.println("you can attack up to " + allowedRange + " fields in any direction");
                System.out.println("how many fields forward and to the right would you like to shoot? (with minus you can hit behind you/left of you)");
                ArrayList<String> inputFieldDistance = getInput();
                int x = Integer.parseInt(inputFieldDistance.get(0));
                int y = Integer.parseInt(inputFieldDistance.get(1));
                if (Math.abs(x) <= allowedRange && Math.abs(y) <= allowedRange) {
                    try {
                        RoomField roomField = getGameBoard().getRoomFieldByCoordinates(currentX + x, currentY + y);
                        affectedCharacters.add(roomField.getCharacter());
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("this field is unreachable, sorry!");
                    }
                } else {
                    throw new InputMismatchException("allowed range is " + allowedRange);
                }
            }
            case SHIELD -> {
                affectedCharacters.add(character);
                for (int i = 0; i < 8; i++) {
                    Direction d = Direction.values()[o + i];
                    affectedCharacters.add(getNextFieldByDirection(currentField, d).getCharacter());
                }
            }
            case MAGE_ARMOUR -> affectedCharacters.add((character));
            case FIREBALL -> {
                Fireball s = (Fireball) spell.createSpell();
                int allowedRange = s.getRange();
                int currentX = character.getPosition().getCoordinates().get(0);
                int currentY = character.getPosition().getCoordinates().get(1);
                System.out.println("you can attack up to " + allowedRange + " fields in any direction");
                System.out.println("how many fields forward and to the right would you like to shoot? (with minus you can hit behind you/left of you)");
                ArrayList<String> inputFieldDistance = getInput();
                int x = Integer.parseInt(inputFieldDistance.get(0));
                int y = Integer.parseInt(inputFieldDistance.get(1));
                if (Math.abs(x) <= allowedRange && Math.abs(y) <= allowedRange) {
                    try {
                        RoomField roomField = getGameBoard().getRoomFieldByCoordinates(currentX + x, currentY + y);
                        for (int i = 0; i < 8; i++) {
                            Direction d = Direction.values()[o + i];
                            affectedCharacters.add(getNextFieldByDirection(roomField, d).getCharacter());
                        }
                        affectedCharacters.add(roomField.getCharacter());
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("this field is unreachable, sorry!");
                    }
                } else {
                    throw new InputMismatchException("allowed range is " + allowedRange);
                }
            }
            case SECOND_LIFE -> {
                affectedCharacters.add(getNextFieldByDirection(currentField, direction).getCharacter());
            }
        }

        return affectedCharacters;
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
}
