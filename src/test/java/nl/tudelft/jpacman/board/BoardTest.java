package nl.tudelft.jpacman.board;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

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

    /**
     * Boundary testing of a 3 x 4  grid.
     *
     * @param x Row position within the square.
     * @param y Collumn position within the square.
     * @param expected Expected boolean result from the method.
     */
    @ParameterizedTest
    @CsvSource({
        "0, 0, true", "-1, 1, false", "3, 2, false", "2, 3, true",
        "1, 0, true", "1, -1, false", "2, 4, false", "0, 3, true"
    })
    public void withinBordersTest(int x, int y, boolean expected) {
        Square[][] grid = {
            { mock(Square.class), mock(Square.class), mock(Square.class),  mock(Square.class) },
            { mock(Square.class), mock(Square.class), mock(Square.class),  mock(Square.class) },
            { mock(Square.class), mock(Square.class), mock(Square.class),  mock(Square.class) }
        };
        board = new Board(grid);

        assertEquals(board.withinBorders(x, y), expected);
    }
}
