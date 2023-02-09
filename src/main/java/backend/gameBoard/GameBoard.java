package backend.gameBoard;

import backend.enums.RoomType;

public class GameBoard {
    public RoomField[][] board;

    public GameBoard(int size){
        board = new RoomField[size][size];
    }


    public GameBoard(){
        board = new RoomField[20][20];
        autofill();
        for (int x = 0; x < 20; x++){
            for (int y = 0; y < 20; y++){
                System.out.print(board[x][y]);
            }
            System.out.print("\n");
        }

    }
    public void autofill() {
        //To-Do
        //automaticly generate Gameboard with rooms and hallways
    }
}
