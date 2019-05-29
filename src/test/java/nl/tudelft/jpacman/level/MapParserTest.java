package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.npc.Ghost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MapParserTest {

    @Mock
    private LevelFactory levelFactory = mock(LevelFactory.class);
    private BoardFactory boardFactory = mock(BoardFactory.class);

    private Ghost ghost = mock(Ghost.class);
    private List<Ghost> ghosts = mock(List.class);
    private List<Square> startingPositions = mock(List.class);

    private MapParser parser;
    private Square[][] grid = {
        { mock(Square.class), mock(Square.class) },
        { mock(Square.class), mock(Square.class) }
    };

    @BeforeEach
    void setup() {
        parser = new MapParser(levelFactory, boardFactory);

    }

    /**
     * Tests if mocked classes are called in the method makeGhostSquare.
     */
    @Test
    public void testMakeGhostSquare() {
        parser.makeGhostSquare(ghosts, ghost);

        verify(boardFactory).createGround();
        verify(ghosts).add(ghost);
        verify(ghost).occupy(boardFactory.createGround());
    }

    /**
     * Test for when empty space ( ) is used in addSquare method.
     */
    @Test
    public void testAddSquareGround() {
        parser.addSquare(grid, ghosts, startingPositions, 1, 0, ' ');
        assertEquals(boardFactory.createGround(), grid[1][0]);
        verify(boardFactory).createGround();
        verify(boardFactory, times(2)).createGround();
    }

    /**
     * Test for when wall (#) is used in addSquare method.
     */
    @Test
    public void testAddSquareWall() {
        parser.addSquare(grid, ghosts, startingPositions, 0, 0, '#');
        assertEquals(boardFactory.createWall(), grid[0][0]);
        verify(boardFactory).createWall();
        verify(boardFactory, times(2)).createWall();
    }

    /**
     * Test for when wall (.) is used in addSquare method.
     */
    @Test
    public void testAddSquarePellet() {
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
    public  void testAddSquareGhost() {
        when(levelFactory.createGhost()).thenReturn(mock(Ghost.class));
        parser.addSquare(grid, ghosts, startingPositions, 1, 1, 'G');
        assertNull(grid[1][1]);
        verify(levelFactory).createGhost();
    }

    /**
     * Test for when Pacman (P) is used in addSquare method.
     */
    @Test
    public void testAddSquarePacMan() {
        parser.addSquare(grid, ghosts, startingPositions, 0, 0, 'P');
        assertEquals(boardFactory.createGround(), grid[0][0]);
        verify(boardFactory).createGround();
        verify(boardFactory, times(2)).createGround();
    }


}
