package nl.tudelft.jpacman.level;

import com.google.common.collect.Lists;
import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.npc.Ghost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MapParserTest {

    @Mock
    private LevelFactory levelFactory = mock(LevelFactory.class);
    private BoardFactory boardFactory = mock(BoardFactory.class);
    private Ghost ghost = mock(Ghost.class);


    private List<Ghost> ghosts = new ArrayList<>();
    private List<Square> startingPositions = new ArrayList<>();

    private MapParser parser;
    private ArrayList<String> list;
    private Square[][] grid = {
        { mock(Square.class), mock(Square.class) },
        { mock(Square.class), mock(Square.class) }
    };

    @BeforeEach
    void setup() {
        parser = new MapParser(levelFactory, boardFactory);
        list = Lists.newArrayList("############",
            "#P        C#", "############");
    }

    @Test
    public void testAddSquarePacMan() {
        parser.addSquare(grid, ghosts, startingPositions, 0, 0, 'P');
        assertEquals(boardFactory.createGround(), grid[0][0]);
    }

    @Test
    public void testAddSquareGround() {
        parser.addSquare(grid, ghosts, startingPositions, 0, 0, ' ');
        assertEquals(boardFactory.createGround(), grid[0][0]);
    }

    @Test
    public void testAddSquareWall() {
        parser.addSquare(grid, ghosts, startingPositions, 0, 0, '#');
        assertEquals(boardFactory.createWall(), grid[0][0]);
    }

//    @Test
//    public void testAddSquarePellet() {
//        parser.addSquare(grid, ghosts, squares, 0, 0, '.');
//        assertEquals(null, grid[0][0]);
//    }
//
//    @Test
//    public  void testAddSquareGhost() {
//        parser.addSquare(grid, ghosts, squares, 0, 0, 'G');
//        assertEquals(null, grid[0][0]);
//    }






}
