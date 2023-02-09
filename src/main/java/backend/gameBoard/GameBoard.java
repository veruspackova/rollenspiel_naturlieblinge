package backend.gameBoard;

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
        board = new RoomField[20][20];
        generateLayout();
        for (int x = 0; x < 20; x++){
            for (int y = 0; y < 20; y++){
                System.out.print(board[x][y]);
            }
            System.out.print("\n");
        }

    }
    /**
     * generate Layout
     * (generates a gameboard layout randomly)
     */
    public void generateLayout() {
        //To-Do
        //automaticly generate Gameboard with rooms and hallways
    }
}
