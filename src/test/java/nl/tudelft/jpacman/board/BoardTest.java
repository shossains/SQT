package nl.tudelft.jpacman.board;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

//    @ParameterizedTest
//    @CsvSource({
//        "0, 0", "-1, 1", "3, 2", "4,3",
//        "1, -1", "2, 4", "3, 5"
//    })
//    public void TestWithinBorders(int x, int y) {
//        Square[][] sg = new Square[3][3];
//        board = new Board(sg);
//
//        assertEquals(board.withinBorders(x,y), boolean but dunno what);
//
//    }
}
