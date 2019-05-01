package nl.tudelft.jpacman.board;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A very simple test class to have
 * for the Board Class.
 *
 * @author Shaan Hossain
 */
public class BoardTest {
    private Board board;

    /**
     * Tests if board is correctly instantiated with a (large enough) square.
     */
    @Test
    public void gridTest() {
        Square[][] sg = new Square[1][1];
        sg[0][0] = new BasicSquare();
        board = new Board(sg);

        assertEquals(board.getHeight(), 1);
        assertEquals(board.getWidth(), 1);
    }
}
