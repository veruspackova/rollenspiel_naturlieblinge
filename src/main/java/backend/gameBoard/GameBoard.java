package backend.gameBoard;

import backend.character.Character;
import backend.character.Fighter;
import backend.enums.Race;
import backend.enums.RoomType;
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
    public RoomField[][] board;
    /**
     * Constructor
     * (generates a Gamboard with a given size)
     */
    public GameBoard(int size){
        if(size < 1){
            throw new IllegalArgumentException("Invalide board size");
        }
        board = new RoomField[size][size];
    }
    /**
     * Default Constructor
     * (generates a Gameboard with a size fo 20)
     */
    public GameBoard(){
        board = new RoomField[10][20];
        generateStandartLayout();
        printBoard();
    }
    /**
     * generate Layout
     * (generates a gameboard layout randomly)
     */
    public void generateLayout(int size) {
        //To-Do
        //automaticly generate Gameboard with rooms and hallways
        for(int x = 0; x < size; x++){
            for(int y = 0; y < size; y++){

            }
        }
    }

    public  void generateStandartLayout(){
        for(int x = 0; x < 6; x++){
            for (int y = 0; y < 4; y++){
                board[x][y] = new RoomField(RoomType.Room);
            }
        }
        board[3][4] = new RoomField(RoomType.Door);
        for (int y = 5; y < 9; y++){
            board[3][y] = new RoomField(RoomType.Hallway);
        }
        Character test = new Fighter(Race.HUM, "jeff", 10, 10,10,10,10);
        board[3][9] = new RoomField(RoomType.Hallway, test);
    }


    /**
     * printBoard
     * (generates a Console output for the map showing teh difference between rooms, hallways doors, walls and characters
     * wall: *
     * room: .
     * door: |
     * hallway: ,
     * character; 0
     * )
     */
    public void printBoard(){
        for(int x = 0; x < board.length; x++){
            for(int y = 0; y < board[0].length; y++){
                if(board[x][y] == null){
                    System.out.print("*");
                }
                else if (board[x][y].getCharacter() != null) {
                    //to-do
                    //unterscheidung monster und spieler
                    System.out.print("0");
                }
                else {
                    switch (board[x][y].getRoomType()){
                        case Door:
                            System.out.print("|");
                            break;
                        case Room:
                            System.out.print(".");
                            break;
                        case Hallway:
                            System.out.print(",");
                            break;
                    }
                }
            }
            System.out.print("\n");
        }
    }
}
