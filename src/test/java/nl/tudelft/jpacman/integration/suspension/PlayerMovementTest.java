package nl.tudelft.jpacman.integration.suspension;

import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.board.Unit;
import nl.tudelft.jpacman.game.Game;
import nl.tudelft.jpacman.level.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * An example test class that conducts integration tests.
 */
@SuppressWarnings("checkstyle:MagicNumber")
public class PlayerMovementTest {
    private Launcher launcher;

    /**
     * Start a launcher, which can display the user interface.
     */
    @BeforeEach
    void setUp() {
        launcher = new Launcher();
        launcher.withMapFile("/Scenario2.txt").launch();
    }

    /**
     * Close the user interface.
     */
    @AfterEach
    public void after() {
        launcher.dispose();
    }

    /**
     *Check if pellet disappears and score increases.
     */
    @SuppressWarnings("PMD.JUnitTestContainsTooManyAsserts")
    @Test
    public void scenarioOne() {
        Game game = launcher.getGame();

        Player player = game.getPlayers().get(0);
        List<Unit> pelletStart = game.getLevel().getBoard().squareAt(3, 1).getOccupants();

        game.start();
        assertEquals(0, player.getScore()); //start with 0 points
        assertEquals(2, game.getLevel().remainingPellets()); //2 pellets in map
        assertEquals(1, pelletStart.size()); //Pellet square should only contain a pellet
        assertEquals("class nl.tudelft.jpacman.level.Pellet",
            pelletStart.get(0).getClass().toString());

        game.move(player, Direction.EAST); //Eat pellet and get pojnts
        List<Unit> pelletEnd = game.getLevel().getBoard().squareAt(3, 1).getOccupants();

        assertEquals(10, player.getScore()); //new total should be 10
        assertEquals(1, game.getLevel().remainingPellets()); //1 pellet in map
        assertEquals(1, pelletStart.size()); //Pellet square now only contains a player
        assertEquals("class nl.tudelft.jpacman.level.Player",
            pelletEnd.get(0).getClass().toString());
    }

    /**
     * Check if player can move to empty square.
     */
    @Test
    public void scenarioTwo() {
        Game game = launcher.getGame();
        game.start();

        Player player = game.getPlayers().get(0);
        List<Unit> emptySquare = game.getLevel().getBoard().squareAt(1, 1).getOccupants();

        assertEquals(0, player.getScore()); //start with 0 points
        assertEquals(0, emptySquare.size()); //Square should be empty

        game.getLevel().move(player, Direction.WEST); // move to the left

        List<Unit> filledSquare = game.getLevel().getBoard().squareAt(1, 1).getOccupants();

        assertEquals(0, player.getScore()); //no points should be added
        assertEquals("class nl.tudelft.jpacman.level.Player",
            filledSquare.get(0).getClass().toString());
    }

    /**
     * Check if player can move through walls.
     */
    @Test
    public void scenarioThree() {
        Game game = launcher.getGame();
        game.start();

        Player player = game.getPlayers().get(0);
        Square wallSquare = game.getLevel().getBoard().squareAt(2, 0); //get wall square
        List<Unit> playerSquare = game.getLevel().getBoard().squareAt(2, 1).getOccupants();

        assertEquals(0, wallSquare.getOccupants().size()); //wall should not have any occupants
        assertEquals("class nl.tudelft.jpacman.board.BoardFactory$Wall",
            wallSquare.getClass().toString());
        assertEquals("class nl.tudelft.jpacman.level.Player",
            playerSquare.get(0).getClass().toString());

        game.getLevel().move(player, Direction.NORTH); //move towards a wall

        Square wallSquare2 = game.getLevel().getBoard().squareAt(2, 0); //get wall square
        List<Unit> playerSquare2 = game.getLevel().getBoard().squareAt(2, 1).getOccupants();

        assertEquals(0, wallSquare2.getOccupants().size()); //still no occupants at wall
        assertEquals("class nl.tudelft.jpacman.level.Player",
            playerSquare2.get(0).getClass().toString());
    }
}
