package nl.tudelft.jpacman.npc.ghost;

import com.google.common.collect.Lists;
import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.board.Board;
import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.game.Game;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.LevelFactory;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.level.PlayerFactory;
import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.points.DefaultPointCalculator;
import nl.tudelft.jpacman.points.PointCalculator;
import nl.tudelft.jpacman.sprite.PacManSprites;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * Class to test Clyde behavior.
 */
public class ClydeTest {

    private Boolean temp = true;
    private GhostMapParser ghostMapParser;
    private Player player;

    /**
     * Set up the GhostMap parser.
     */
    @BeforeEach
    void setUp() {
        PacManSprites sprites = new PacManSprites();
        GhostFactory ghostFactory = new GhostFactory(sprites);
        LevelFactory levelFactory = new LevelFactory(
            sprites,
           ghostFactory,
            mock(PointCalculator.class));
        ghostMapParser = new GhostMapParser(levelFactory, new BoardFactory(sprites), ghostFactory);
        PlayerFactory playerFactory = new PlayerFactory(sprites);
        player = playerFactory.createPacMan();
    }

    /**
     * Test the case where Pacman is 8 squares away from Clyde.
     * Clyde should move towards Pacman.
     */
    @Test
    public void TestClydeFarFromPacman(){

        Level level = ghostMapParser.parseMap(Lists.newArrayList("############",
            "#P.      .C#", "############"));
        Board board = level.getBoard();

        level.registerPlayer(player);
        player.setDirection(Direction.WEST);

        Ghost clyde = Navigation.findUnitInBoard(Clyde.class, board);

        Optional direction = clyde.nextAiMove();

        assertEquals(direction, Optional.of(Direction.WEST));


    }

    /**
     * Test the case where Pacman is 1 square away from Clyde.
     * Clyde should move away from Pacman.
     */
    @Test
    public void TestClydeCloseFromPacman(){
        Level level = ghostMapParser.parseMap(Lists.newArrayList("############",
            "#P.C       #", "############"));
        Board board = level.getBoard();

        level.registerPlayer(player);
        player.setDirection(Direction.WEST);

        Ghost clyde = Navigation.findUnitInBoard(Clyde.class, board);

        Optional direction = clyde.nextAiMove();

        assertEquals(direction, Optional.of(Direction.EAST));
    }

    /**
     * Test the case where Pacman isn't on the board.
     * Clyde shouldn't move since his movement is based on Pacman.
     */
    @Test
    public void TestClydeWhenPacmanNull(){
        Level level = ghostMapParser.parseMap(Lists.newArrayList("############",
            "#C         #", "############"));
        Board board = level.getBoard();

        Ghost clyde = Navigation.findUnitInBoard(Clyde.class, board);
        Optional direction = clyde.nextAiMove();

        assertEquals(direction, Optional.empty());
    }

    /**
     * Test the case where there is a null square between Pacman and Clyde.
     * Clyde shouldn't move since there doesn't exist a shortest path between Clyde and Pacman.
     */
    @Test
    public void TestClydeWhenGridSquareNull(){
        Level level = ghostMapParser.parseMap(Lists.newArrayList("############",
            "#P    #   C#", "############"));
        Board board = level.getBoard();

        level.registerPlayer(player);
        player.setDirection(Direction.WEST);

        Ghost clyde = Navigation.findUnitInBoard(Clyde.class, board);

        Optional direction = clyde.nextAiMove();

        assertEquals(direction, Optional.empty());

    }
}
