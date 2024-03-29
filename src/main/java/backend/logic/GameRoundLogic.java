package backend.logic;

import backend.artifacts.ISearchable;
import backend.artifacts.items.Item;
import backend.artifacts.spells.Fireball;
import backend.artifacts.spells.Spell;
import backend.artifacts.weapons.RangedSimpleWeapon;
import backend.artifacts.weapons.WeaponBase;
import backend.artifacts.weapons.ranged.Bow;
import backend.character.Character;
import backend.character.Monster;
import backend.character.Wizard;
import backend.enums.Direction;
import backend.enums.Spells;
import backend.gameBoard.GameBoard;
import backend.gameBoard.RoomField;
import backend.input.InputClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

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
        while (character.getHitPoints() > 0) {
            ArrayList<String> input = getInput();

            String playerAction;
            if (input != null) {
                playerAction = input.get(0);
            } else {
                playerAction = "rest";
            }
            switch (playerAction) {
                case "f":
                case "fight":
                    RoomField fieldToAttack = getFacingPosition();

                    if (input.size() == 2) {
                        int targetDistance = Integer.parseInt(input.get(1));
                        WeaponBase w = character.getSelectedWeapon();

                        if (w instanceof Spell) {
                            System.out.println("Spells must use cast, not fight!");
                            break;
                        }

                        if (w instanceof Bow && character.getArrowCount() <= 0) {
                            System.out.println("You don't have any arrows left to shoot");
                            return;
                        }

                        if (w instanceof RangedSimpleWeapon && ((RangedSimpleWeapon) w).getRange() >= targetDistance) {
                            fieldToAttack = getFacingPosition(targetDistance);
                        }
                    }

                    if (fieldToAttack != null && fieldToAttack.getCharacter() != null) {
                        Character target = fieldToAttack.getCharacter();
                        FightRound fightRound = new FightRound(character, target);
                        fightRound.singleRound();
                    } else {
                        System.out.println("You hit nothing");
                        return;
                    }
                    break;

                case "c":
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

                case "w":
                    if (movecounter > 1) {
                        boolean success = move(character, Direction.North);
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
                    System.out.println("Weapon list of " + character.getName() + ":");
                    if (character.getWeapons() == null) {
                        System.out.println("You have no weapons available");
                        break;
                    }
                    for (WeaponBase weapon : character.getWeapons()) {
                        System.out.println(weapon);
                    }
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
                default:
                    gameBoard.printLegend();
                    break;
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
    public void move(Character character) {
        RoomField current = character.getPosition();
        RoomField target = getFacingPosition();
        if (!moveToTarget(character, target, current)) {
            System.out.println("Invalid move. Something is in the way.");
        }
    }

    public boolean move(Character character, Direction direction) {
        RoomField current = character.getPosition();
        RoomField target = getNextFieldByDirection(current, direction);
        if (!moveToTarget(character, target, current)) {
            System.out.println("Invalid move. Something is in the way.");
            return false;
        } else {
            character.setDirection(direction);
        }
        return true;
    }

    public void useItem(Character character, String itemName) {
        for (Item item : character.getItems()) {
            if (item.name.equals(itemName)) {
                item.use(character);
            }
            if (character.getHitPoints() <= 0) {
                break;
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
            if (target.getCharacter() != null && target.getCharacter() != character) {
                FightRound fightRound = new FightRound(character, target.getCharacter());
                fightRound.fightToTheDeath();
            }
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
            case BURNING_HANDS -> addAffectedByBurningHands(affectedCharacters, currentField, direction);
            case RAY_OF_FROST, FIREBALL ->
                    addAffectedByRangedWeaponSpell((RangedSimpleWeapon) spell.createSpell(), affectedCharacters, o);
            case SHIELD -> {
                affectedCharacters.add(character);
                for (int i = 0; i < 8; i++) {
                    Direction d = Direction.values()[o + i];
                    affectedCharacters.add(getNextFieldByDirection(currentField, d).getCharacter());
                }
            }
            case MAGE_ARMOUR -> affectedCharacters.add((character));
            case HEAL, SECOND_LIFE -> {
                RoomField field = getNextFieldByDirection(currentField, direction);
                if (field != null) {
                    affectedCharacters.add(field.getCharacter());
                }
            }
        }

        return (ArrayList<Character>) affectedCharacters.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    private void addAffectedByBurningHands(ArrayList<Character> affectedCharacters, RoomField currentField, Direction direction) {
        int o = direction.ordinal();

        Direction leftSide = Direction.values()[Math.floorMod(o - 1, 8)];
        Direction rightSide = Direction.values()[Math.floorMod(o + 1, 8)];
        System.out.println("cone: " + direction + " and from " + leftSide + " to " + rightSide);

        ArrayList<RoomField> fields = new ArrayList<>();
        fields.add(getNextFieldByDirection(currentField, direction));
        fields.add(getNextFieldByDirection(currentField, direction, 2));
        fields.add(getNextFieldByDirection(currentField, leftSide, 2));
        fields.add(getNextFieldByDirection(currentField, rightSide, 2));

        for (RoomField field : fields) {
            if (field != null) {
                affectedCharacters.add(field.getCharacter());
            }
        }
    }

    private void addAffectedByRangedWeaponSpell(RangedSimpleWeapon s, ArrayList<Character> affectedCharacters, int o) {
        int allowedRange = s.getRange();
        int currentX = character.getPosition().getCoordinates().get(0);
        int currentY = character.getPosition().getCoordinates().get(1);
        System.out.println("you can attack up to " + allowedRange + " fields in any direction");
        System.out.println("how many fields forward and to the right would you like to shoot? (with minus you can hit behind you/left of you)");
        ArrayList<String> inputFieldDistance = getInput();
        try {

            int x = Integer.parseInt(inputFieldDistance.get(0));
            int y = Integer.parseInt(inputFieldDistance.get(1));
            if (Math.abs(x) <= allowedRange && Math.abs(y) <= allowedRange) {
                try {
                    RoomField roomField = getGameBoard().getRoomFieldByCoordinates(currentX - x, currentY + y);
                    if (s instanceof Fireball) {
                        for (int i = 0; i < 8; i++) {
                            Direction d = Direction.values()[o + i];
                            RoomField f = getNextFieldByDirection(roomField, d);
                            if (f != null) {
                                affectedCharacters.add(f.getCharacter());
                            }
                        }
                    }
                    affectedCharacters.add(roomField.getCharacter());
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("this field is unreachable, sorry!");
                } catch (NullPointerException e) {
                    System.out.println("this field is not reachable, sorry!");
                }
            } else {
                throw new InputMismatchException("allowed range is " + allowedRange);
            }
        } catch (NumberFormatException e) {
            System.out.println("please only use numbers and spaces!");
        }
    }

    public List<Monster> moveMonsters(List<Monster> monsterList) {
        System.out.println("Now the monsters will move");
        for (Monster monster : monsterList) {
            if (monster != null && monster.getPosition() != null) {
                int rng = (int) (Math.random() * 8);
                Direction dir = Direction.values()[rng];
                move(monster, dir);
            }
        }
        return monsterList;
    }

    public RoomField getNextFieldByDirection(RoomField currentField, Direction direction) {
        return getNextFieldByDirection(currentField, direction, 1);
    }

    public RoomField getNextFieldByDirection(RoomField currentField, Direction direction, int distance) {
        ArrayList<Integer> cords = currentField.getCoordinates();
        RoomField roomField = currentField;

        try {
            switch (direction) {
                case North -> roomField = gameBoard.board[cords.get(0) - distance][cords.get(1)];
                case NorthEast -> roomField = gameBoard.board[cords.get(0) - distance][cords.get(1) + distance];
                case East -> roomField = gameBoard.board[cords.get(0)][cords.get(1) + distance];
                case SouthEast -> roomField = gameBoard.board[cords.get(0) + distance][cords.get(1) + distance];
                case South -> roomField = gameBoard.board[cords.get(0) + distance][cords.get(1)];
                case SouthWest -> roomField = gameBoard.board[cords.get(0) + distance][cords.get(1) - distance];
                case West -> roomField = gameBoard.board[cords.get(0)][cords.get(1) - distance];
                case NorthWest -> roomField = gameBoard.board[cords.get(0) - distance][cords.get(1) - distance];
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Oops, you can't go that far");
        }

        return roomField;
    }
}