package nl.tudelft.jpacman.integration.suspension;

import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.board.Unit;
import nl.tudelft.jpacman.game.Game;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.npc.Ghost;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * An example test class that conducts integration tests.
 */
public class SuspendSystemTest {
    private Launcher launcher;

    /**
     * Start a launcher, which can display the user interface.
     */
    @BeforeEach
    void setUp() {
        launcher = new Launcher();
        launcher.withMapFile("/map.txt").launch();
    }

    /**
     * Close the user interface.
     */
    @AfterEach
    public void after() {
        launcher.dispose();
    }

    /**
     * Test how the ghosts and player behave after the game stops
     * @throws InterruptedException
     */
    @Test //TODO Check for player SETDIRECTION behavior after 'game.stop'
    public void testMovingGhost() throws InterruptedException {
        Game game = getGame();
        List<Unit> ghostBefore = game.getLevel().getBoard().squareAt(6, 1).getOccupants(); //get ghostS start location
        assertEquals(ghostBefore.get(0).getClass().toString(),"class nl.tudelft.jpacman.npc.ghost.Blinky"); //check if the square contains a ghost

        game.stop();
        Thread.sleep(250);

        List<Unit> ghostAfter = game.getLevel().getBoard().squareAt(6, 1).getOccupants(); //get ghost start location
        assertEquals(ghostAfter.get(0).getClass().toString(), "class nl.tudelft.jpacman.npc.ghost.Blinky"); //ghost should still be there

        game.start();
        Thread.sleep(160); //let the ghost move

        List<Unit> emptySquare = game.getLevel().getBoard().squareAt(6, 1).getOccupants(); //get ghostSquare
        assertEquals(0,emptySquare.size()); //ghost has left start location

        game.stop();

        List<Unit> ghostNewLocation = game.getLevel().getBoard().squareAt(5, 1).getOccupants(); //get new ghost location
        assertEquals(ghostNewLocation.get(0).getClass().toString(), "class nl.tudelft.jpacman.npc.ghost.Blinky"); //ghost should be on it's new location
    }

    /**
     * The simplest test that just starts the
     * game and checks it is indeed in progress.
     */
    @Test
    public void testRestartGame() {
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
