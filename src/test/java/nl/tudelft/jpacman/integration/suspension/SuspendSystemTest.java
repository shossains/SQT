package nl.tudelft.jpacman.integration.suspension;

import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.board.Unit;
import nl.tudelft.jpacman.game.Game;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.npc.ghost.Navigation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * An example test class that conducts integration tests.
 */
@SuppressWarnings("checkstyle:MagicNumber")
public class SuspendSystemTest {
    private Launcher launcher;

    /**
     * Start a launcher, which can display the user interface.
     */
    @BeforeEach
    void setUp() {
        launcher = new Launcher();
        launcher.withMapFile("/Scenario4.txt").launch();
    }

    /**
     * Close the user interface.
     */
    @AfterEach
    public void after() {
        launcher.dispose();
    }

    /**
     * Test how the ghosts and player behave after the game stops.
     */
    @Test
    @SuppressWarnings("PMD.JUnitTestContainsTooManyAsserts")
    public void scenarioOne() {
        Game game = getGame();
        Player player = getGame().getPlayers().get(0);
        List<Unit> ghostBefore = game.getLevel().getBoard().squareAt(6, 1).getOccupants();
        Ghost ghost = Navigation.findUnitInBoard(Ghost.class, game.getLevel().getBoard());

        assertEquals(player.getScore(), 0); //player should have 0 points to start with
        assertEquals(1, ghostBefore.size()); //ghost is at start location

        game.stop();

        game.move(player, Direction.EAST);

        assertEquals(player.getScore(), 0); //score should still be 0 since it shouldn't move

        game.start();

        game.getLevel().move(ghost, Direction.WEST); //Move ghost to the left

        List<Unit> newSquare = game.getLevel().getBoard().squareAt(5, 1).getOccupants();
        assertEquals(1, newSquare.size()); //ghost has left start location

        game.stop();
        List<Unit> finalSquare = game.getLevel().getBoard().squareAt(5, 1).getOccupants();
        assertEquals(1, finalSquare.size()); //ghost still at the same location

    }

    /**
     * The simplest test that just starts the
     * game and checks it is indeed in progress.
     */
    @Test
    public void scenarioTwo() {
        launcher.launch();
        getGame().start();
        assertThat(getGame().isInProgress()).isTrue();
        getGame().stop();
        assertThat(getGame().isInProgress()).isFalse();
        getGame().start();
        assertThat(getGame().isInProgress()).isTrue();
    }

    private Game getGame() {
        return launcher.getGame();
    }
}
