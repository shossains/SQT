package nl.tudelft.jpacman.level;

import com.google.common.collect.Lists;
import nl.tudelft.jpacman.PacmanConfigurationException;
import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.npc.Ghost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

/**
 * Test class for the class MapParser.
 */
public class MapParserTest {

    /**
     * Mocked LevelFactory, used for creating the MapParser
     * and verifying if methods of this class are used.
     */
    private LevelFactory levelFactory = mock(LevelFactory.class);

    /**
     * Mocked BoardFactory, used for creating the MapParser
     * and verifying if methods of this class are used.
     */
    private BoardFactory boardFactory = mock(BoardFactory.class);

    /**
     * Mocked ghost object.
     */
    private Ghost ghost = mock(Ghost.class);

    /**
     * Mocked list with ghost objects.
     */
    private List<Ghost> ghosts = mock(List.class);

    /**
     * Mocked list with Square objects.
     */
    private List<Square> startingPositions = mock(List.class);

    /**
     * MapParser object.
     */
    private MapParser parser;

    /**
     * 2 x 2 Grid consisting of mocked Square objects.
     */
    private Square[][] grid = {
        { mock(Square.class), mock(Square.class) },
        { mock(Square.class), mock(Square.class) }
    };

    /**
     * Integer used for out of bound testing.
     */
    public static final int OUT_OF_BOUND = 3;

    /**
     * Setup for each test for re-usability purposes.
     */
    @BeforeEach
    void setup() {
        parser = new MapParser(levelFactory, boardFactory);
        when(levelFactory.createGhost()).thenReturn(ghost);
    }

    /**
     * Tests if mocked classes are called in the method makeGhostSquare.
     */
    @Test
    void testMakeGhostSquare() {
        parser.makeGhostSquare(ghosts, ghost);

        verify(boardFactory).createGround();
        verify(ghosts).add(ghost);
        verify(ghost).occupy(boardFactory.createGround());
    }

    /**
     * Test for when empty space ( ) is used in addSquare method.
     */
    @Test
    void testAddSquareGround() {
        parser.addSquare(grid, ghosts, startingPositions, 1, 0, ' ');
        assertEquals(boardFactory.createGround(), grid[1][0]);
        verify(boardFactory, times(2)).createGround();
    }

    /**
     * Test for when wall (#) is used in addSquare method.
     */
    @Test
    void testAddSquareWall() {
        parser.addSquare(grid, ghosts, startingPositions, 0, 0, '#');
        assertEquals(boardFactory.createWall(), grid[0][0]);
        verify(boardFactory, times(2)).createWall();
    }

    /**
     * Test for when wall (.) is used in addSquare method.
     */
    @Test
    void testAddSquarePellet() {
        Pellet pellet = mock(Pellet.class);
        when(levelFactory.createPellet()).thenReturn(pellet);
        parser.addSquare(grid, ghosts, startingPositions, 0, 1, '.');
        assertEquals(boardFactory.createGround(), grid[0][1]);
        verify(levelFactory).createPellet();
    }

    /**
     * Test for when wall (G) is used in addSquare method.
     */
    @Test
    void testAddSquareGhost() {
        parser.addSquare(grid, ghosts, startingPositions, 1, 1, 'G');
        assertNull(grid[1][1]);
        verify(levelFactory, times(1)).createGhost();
    }

    /**
     * Test for when Pacman (P) is used in addSquare method.
     */
    @Test
    void testAddSquarePacMan() {
        parser.addSquare(grid, ghosts, startingPositions, 0, 0, 'P');
        assertEquals(boardFactory.createGround(), grid[0][0]);
        verify(boardFactory, times(2)).createGround();
    }

    /**
     * Test if addSquare method throws an exception when an invalid character is used.
     */
    @Test
    void testAddSquareInvalidChar() {
        assertThrows(PacmanConfigurationException.class, () ->
            parser.addSquare(grid, ghosts, startingPositions, 0, 0, 'X')
        );
    }

    /**
     * Test if addSquare method throws an exception when an incorrect coordinate is accessed.
     */
    @Test
    void testAddSquareInvalidPosition() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () ->
            parser.addSquare(grid, ghosts, startingPositions, 0, OUT_OF_BOUND, 'G')
        );
    }

    /**
     * Test if parseMap throws an exception when the text map is null.
     */
    @Test
    void testParseMapNull() {
        ArrayList<String> text = null;
        assertThrows(PacmanConfigurationException.class,
            () -> parser.parseMap(text));
    }

    /**
     * Test if parseMap throws an exception when the text map is empty ArrayList.
     */
    @Test
    void testParseMapEmptyInput() {
        ArrayList<String> text = Lists.newArrayList();
        assertThrows(PacmanConfigurationException.class,
            () -> parser.parseMap(text));
    }

    /**
     * Test if parseMap throws an exception when the text map is an ArrayList with an empty string.
     */
    @Test
    void testParseMapEmptyLine() {
        ArrayList<String> text = Lists.newArrayList("");
        assertThrows(PacmanConfigurationException.class,
            () -> parser.parseMap(text));
    }

    /**
     * Test if parseMap throws an exception when the text map lines are not of equal width.
     */
    @Test
    void testParseMapInvalidSize() {
        ArrayList<String> text = Lists.newArrayList("##", "###");
        assertThrows(PacmanConfigurationException.class,
            () -> parser.parseMap(text));
    }

    /**
     * Test if parseMap throws an exception when the input file name is empty.
     */
    @Test
    void testParseMapNoFile() {
        String mapName = "";
        assertThrows(PacmanConfigurationException.class, () -> parser.parseMap(mapName));
    }

    /**
     * Test if parseMap throws an exception when the InputStream is empty.
     */
    @Test
    void testParseMapNoInputSource() {
        try {
            InputStream source = MapParser.class.getResourceAsStream("");
            assertThrows(PacmanConfigurationException.class, () -> parser.parseMap(source));
            source.close();
        } catch (Exception e) {
        }

    }


}
