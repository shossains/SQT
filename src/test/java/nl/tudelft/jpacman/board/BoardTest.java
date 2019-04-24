package nl.tudelft.jpacman.board;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {
    private Board board;

    @Test
    public void gridTest() {
        Square[][] sg = new Square[1][1];
        sg[0][0] = new BasicSquare();
        board = new Board(sg);

        assertEquals(board.getHeight(), 1);
        assertEquals(board.getWidth(), 1);
    }
}
