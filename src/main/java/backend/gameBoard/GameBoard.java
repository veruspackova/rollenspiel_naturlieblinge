package backend.gameBoard;

import backend.artifacts.ISearchable;
import backend.artifacts.armour.Chainmail;
import backend.artifacts.armour.LeatherArmour;
import backend.artifacts.armour.Scalemail;
import backend.artifacts.armour.Shield;
import backend.artifacts.items.Item;
import backend.artifacts.items.magicitems.Amulet;
import backend.artifacts.items.magicitems.Cape;
import backend.artifacts.items.magicitems.Ring;
import backend.artifacts.items.magicpotions.HealingPotion;
import backend.artifacts.items.magicpotions.InvisibilityPotion;
import backend.artifacts.items.magicpotions.Poison;
import backend.artifacts.weapons.melee.*;
import backend.artifacts.weapons.ranged.Bow;
import backend.artifacts.weapons.ranged.Dart;
import backend.character.Character;
import backend.character.Fighter;
import backend.character.Monster;
import backend.enums.Direction;
import backend.enums.Race;
import backend.enums.RoomType;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Gameboard class
 * <p>
 *     generate a Gameboard where rooms ca be placed in<br>
 *     sets the size of the game
 * </p>
 *
 * @author jonasmalsbenden
 */
public class GameBoard {
    private RoomField[][] board;
    public int size;
    /**
     * Constructor
     * (generates a Gamboard with a given size)
     */
    public GameBoard(int size){
        if(size < 1){
            throw new IllegalArgumentException("Invalide board size");
        }
        board = new RoomField[size][size];
        this.size = size;
    }
    /**
     * Default Constructor
     * (generates a Gameboard with a size fo 20)
     */
    public GameBoard(){
        board = new RoomField[20][20];
        this.size = 20;
        generateMap();
        printBoard();
        this.generateMap();
    }
    /**
     * generate Map
     * (generates a gameboard layout randomly)
     */
    public void generateMap(){
        //@todo implement making monster amount and so on variable
        int x = (int) (Math.random()*size);
        int y = (int) (Math.random()*size);
        Direction lastMove = null;
        for (int i = 0; i < (size*size); i++){
            board[x][y] = new RoomField(RoomType.Room, x, y);
            double direction = Math.random()*10;
            if(direction <= 5){
                if(direction < 2 && x > 0 && lastMove != Direction.East){
                    x--;
                    lastMove = Direction.West;
                }else if(x < size-1 && lastMove != Direction.West){
                    x++;
                    lastMove = Direction.East;
                }
            }
            if(direction > 5){
                if(direction < 8 && y > 0 && lastMove != Direction.North){
                    y--;
                    lastMove = Direction.South;
                }else if(y < size-1 && lastMove != Direction.South){
                    y++;
                    lastMove = Direction.North;
                }
            }
        }
        setRooms();
    }

    public void setRooms(){
        for(int x = 0; x < size; x++){
            for(int y = 0; y < size; y++){
                if(board[x][y] != null){
                    if(isHallway(x, y)){
                        double rnd = Math.random();
                        if(rnd >= 0.8){
                            board[x][y] = new RoomField(RoomType.Door, x, y);
                            board[x][y].addItem(placeItems());
                        }
                    }

                }
            }
        }
    }

    public ISearchable placeItems(){
        double rnd = Math.random()*100;
        switch ((int)rnd){
            case 0://Chainmail
                return new Chainmail();
            case 1://LeatherArmour
                return new LeatherArmour();
            case 2://Scalemail
                return new Scalemail();
            case 3://Shield
                return new Shield();
            case 4://Amulet
                return new Amulet("Amulet", "Amulet");
            case 5://Cape
                return new Cape("Cape", "Cape");
            case 6://Ring
                return new Ring("Ring", "Ring");
            case 7://HealingPotion
                return new HealingPotion("Healing Potion", "Healing Potion");
            case 8://InvisibilityPotion
                return new InvisibilityPotion("Invisibility Potion", "Invisibility Potion");
            case 9://Poison
                return new Poison("Poison Potion", "Poison Potion");
            case 10://Club
                return new Club();
            case 11://Dagger
                return new Dagger();
            case 12://Handaxe
                return new HandAxe();
            case 13://Rapier
                return new Rapier();
            case 14://Spear
                return new Spear();
            case 15://Sword
                return new Sword();
            case 16://Waraxe
                return new WarAxe();
            case 17://Bow
                return new Bow();
            case 18://Dart
                return new Dart();
        }
        return null;
    }

    public boolean isHallway(int x, int y){
        int counter = 0;
        if(y <= 0 || board[x][y-1] == null){
            counter++;
        }
        if(x == size-1 || board[x+1][y] == null){
            counter++;
        }
        if(y == size-1 || board[x][y+1] == null){
            counter++;
        }
        if(x <= 0 || board[x-1][y] == null){
            counter++;
        }
        if(counter >= 2){
            return true;
        }
        else return false;
    }

    /**
     * printBoard
     * (generates a Console output for the map showing the difference between rooms, hallways, doors, walls and characters
     * wall: *
     * room: .
     * door: |
     * hallway: ,
     * character: 0
     * current character: @
     * monster: X
     * )
     */
    public void printBoard(){
        for(int x = 0; x < board.length; x++){
            for(int y = 0; y < board[0].length; y++){
                if(board[x][y] == null){
                    System.out.print("*  ");
                }
                else if (board[x][y].getCharacter() != null) {
                    //to-do
                    //unterscheidung monster und spieler
                    System.out.print("@  ");
                }
                else {
                    switch (board[x][y].getRoomType()){
                        case Door:
                            System.out.print("|  ");
                            break;
                        case Room:
                            System.out.print(".  ");
                            break;
                        case Hallway:
                            System.out.print(",  ");
                            break;
                    }
                }
            }
            System.out.print("\n");
        }
        System.out.println("_______________________________________________________________________________________________");
    }

    public void printBoardforPlayer(Character currentCharacter){

        for(int x = 0; x < board.length; x++){
            for(int y = 0; y < board[0].length; y++){
                if(board[x][y] == null){
                    System.out.print("*  ");
                }
                else if (board[x][y].getCharacter() != null) {
                    //to-do
                    Character character = board[x][y].getCharacter();
                    //unterscheidung monster und spieler
                    if(character.getClass() == Monster.class){
                        System.out.print("X  ");
                    }else if(character == currentCharacter){
                        System.out.print("@  ");
                    }else {
                        System.out.print("O  ");
                    }

                }
                else {
                    switch (board[x][y].getRoomType()){
                        case Door:
                            System.out.print("|  ");
                            break;
                        case Room:
                            System.out.print(".  ");
                            break;
                        case Hallway:
                            System.out.print(",  ");
                            break;
                    }
                }
            }
            System.out.print("\n");
        }
        System.out.println("_______________________________________________________________________________________________");
    }

    public void placeCharacter(Character character){
        for(int i = 0; i < size-1; i++){
            int x = (int) (Math.random()*size);
            int y = (int) (Math.random()*size);
            if(board[x][y] != null){
                board[x][y].setCharacter(character);
                character.setPosition(board[x][y]);
                i = size;
            }
        }
    }

    public RoomField[][] getBoard() {
        return board;
    }
}
