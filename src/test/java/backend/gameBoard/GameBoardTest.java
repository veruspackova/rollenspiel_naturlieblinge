package backend.gameBoard;

import backend.logic.Dice;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {
    @Test
    void boardSizeToLow(){
        assertThrows(IllegalArgumentException.class,
                () ->{
                    GameBoard board = new GameBoard(0);
                }
        );
    }

}