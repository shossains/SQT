package nl.tudelft.jpacman.game;

import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.level.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for testing the Game states.
 */
public class GameStateTest {
    private Launcher launcher;
    private Player player;
    private boolean progress;


    /**
     * Setting up things for the tests.
     */
    @BeforeEach
    void setUp() {
        launcher = new Launcher();
        launcher.withMapFile("/SneakPathMap_1");
        launcher.launch();
        player = launcher.getGame().getPlayers().get(0);
    }


    /**
     * Cleans up the launcher after each test.
     */
    @AfterEach
    void after() {
        launcher.dispose();
    }

    //Test cases for start button
    /**
     * Tests for when start button has been pressed and the game has started.
     * Expected: Progress should be the same after start has been pressed twice and progress should be true.
     */
    @Test
    void testStartButtonWithGameStarted() {
        launcher.getGame().start();
        progress = launcher.getGame().isInProgress();
        launcher.getGame().start();
        assertEquals(progress, launcher.getGame().isInProgress());
        assertTrue(progress);
    }

    /**
     * Tests for when start button has been pressed and the player has won.
     * Expected:  Progress after game has been lost should stay false after the start button has been pressed again.
     */
    @Test
    void testStartButtonWithGameLost() {
        launcher = new Launcher();
        launcher.withMapFile("/SneakPathMap_2");
        launcher.launch();
        launcher.getGame().levelLost();
        progress = launcher.getGame().isInProgress();
        launcher.getGame().start();
        assertEquals(progress, launcher.getGame().isInProgress());
        assertFalse(progress);
    }

    /**
     * Tests for when start button has been pressed and the player has lost.
     * Expected: Progress after game has been won should stay false after the start button has been pressed again.
     */
    @Test
    void testStartButtonWithGameWon() {
        launcher = new Launcher();
        launcher.withMapFile("/SneakPathMap_2");
        launcher.launch();
        launcher.getGame().levelWon();
        progress = launcher.getGame().isInProgress();
        launcher.getGame().start();
        assertEquals(progress, launcher.getGame().isInProgress());
        assertFalse(progress);
    }

    /**
     * Tests for when start button has been pressed and the game has been suspended.
     * Expected: After game has been suspended, the start button should change the gameprogress from false to true.
     */
    @Test
    void testStartButtonWithGameSuspended() {
        launcher.getGame().start();
        progress = launcher.getGame().isInProgress();
        assertTrue(progress);
        launcher.getGame().stop();
        progress = launcher.getGame().isInProgress();
        launcher.getGame().start();
        assertEquals(!progress, launcher.getGame().isInProgress());
        assertFalse(progress);
    }

    //Test cases for stop button

    /**
     * Tests for when stop button has been pressed and the game has started.
     * Expected: After Gui has been launched the game shouldn't have started yet and stop button should change that.
     */
    @Test
    void testStopButtonWithGameStarted() {
        launcher.launch();
        progress = launcher.getGame().isInProgress();
        launcher.getGame().stop();
        assertEquals(progress, launcher.getGame().isInProgress());
        assertFalse(progress);
    }

    /**
     * Tests for when stop button has been pressed and the player has won.
     * Expected: fter gane has been lost, the progress should stay false and not change after pressing stop button.
     */
    @Test
    void testStopButtonWithGameLost() {
        launcher.getGame().levelLost();
        progress = launcher.getGame().isInProgress();
        launcher.getGame().stop();
        assertEquals(progress, launcher.getGame().isInProgress());
        assertFalse(progress);
    }

    /**
     * Tests for when stop button has been pressed and the player has lost.
     * Expected: After gane has been won, the progress should stay false and not change after pressing stop button.
     */
    @Test
    void testStopButtonWithGameWon() {
        launcher.getGame().levelWon();
        progress = launcher.getGame().isInProgress();
        launcher.getGame().stop();
        assertEquals(progress, launcher.getGame().isInProgress());
        assertFalse(progress);
    }

    /**
     * Tests for when stop button has been pressed and the game has been suspended.
     * Expected: After game has been suspended the progress should go from true to false.
     */
    @Test
    void testStopButtonWithGameSuspended() {
        launcher.getGame().start();
        progress = launcher.getGame().isInProgress();
        launcher.getGame().stop();
        assertEquals(progress, !launcher.getGame().isInProgress());
        assertTrue(progress);
        assertFalse(launcher.getGame().isInProgress());
    }

    //Test cases for last pellet eaten.

    /**
     * Tests for when last pellet has been consumed and the game has started.
     * Expected: After GUI has been launched, game shouldn't have started and eating the last pellet shouldn't affect that.
     */
    @Test
    void testLastPelletWithGameStarted() {
        launcher.launch();
        progress = launcher.getGame().isInProgress();
        launcher.getGame().move(player, Direction.WEST);
        assertEquals(progress, launcher.getGame().isInProgress());
        assertFalse(progress);
    }

    /**
     * Tests for when last pellet has been consumed and the player has won.
     * Expected: After the player lost, eating the last pellet shouldn't affect the progress.
     */
    @Test
    void testLastPelletWithGameLost() {
        launcher.getGame().levelLost();
        progress = launcher.getGame().isInProgress();
        launcher.getGame().move(launcher.getGame().getPlayers().get(0), Direction.WEST);
        assertEquals(progress, launcher.getGame().isInProgress());
        assertFalse(progress);
    }

    /**
     * Tests for when last pellet has been consumed and the player has lost.
     * Expected: After eating the last pellet, the progress should change from true to false.
     */
    @Test
    void testLastPelletWithGameWon() {
       launcher.getGame().start();
       progress = launcher.getGame().isInProgress();
       launcher.getGame().move(launcher.getGame().getPlayers().get(0), Direction.WEST);
       assertEquals(progress, !launcher.getGame().isInProgress());
       assertTrue(progress);
    }

    /**
     * Tests for when last pellet has been consumed and the game has been suspended.
     * Expected: After game has been suspended, eating the last pellet shouldn't affect the progress.
     */
    @Test
    void testLastPelletWithGameSuspended() {
        launcher.getGame().stop();
        progress = launcher.getGame().isInProgress();
        launcher.getGame().move(player, Direction.WEST);
        assertEquals(progress, launcher.getGame().isInProgress());
        assertFalse(progress);
    }

    //Test cases for when eaten by ghost.

    /**
     * Tests for when ghost has eaten Pacman and the game has started.
     * Expected: After GUI has been launched, game should't have started and being eaten by a ghost shouldn't affect that.
     */
    @Test
    void testEatenByGhostWithGameStarted() {
        launcher.launch();
        progress = launcher.getGame().isInProgress();
        launcher.getGame().move(player, Direction.EAST);
        launcher.getGame().move(player, Direction.EAST);
        assertEquals(progress, launcher.getGame().isInProgress());
        assertFalse(progress);
    }

    /**
     * Tests for when ghost has eaten Pacman and the player has lost.
     * Expected: After game has been lost, eaten by a ghost shouldn't affect the progress.
     */
    @Test
    void testEatenByGhostWithGameLost() {
        launcher.getGame().levelLost();
        progress =  launcher.getGame().isInProgress();
        launcher.getGame().move(player, Direction.EAST);
        launcher.getGame().move(player, Direction.EAST);
        assertEquals(progress, launcher.getGame().isInProgress());
        assertFalse(progress);
    }

    /**
     * Tests for when ghost has eaten Pacman and the player has won.
     * Expected: After game has been won, eaten by a ghost shouldn't affect the progress.
     */
    @Test
    void testEatenByGhostWithGameWon() {
        launcher.getGame().levelWon();
        progress = launcher.getGame().isInProgress();
        launcher.getGame().move(player, Direction.EAST);
        launcher.getGame().move(player, Direction.EAST);
        assertEquals(progress, launcher.getGame().isInProgress());
        assertFalse(progress);
    }

    /**
     * Tests for when ghost has eaten Pacman and the game has been suspended.
     * Expected: After game has been suspended, eaten by a ghost shouldn't affect the progress.
     */
    @Test
    void testEatenByGhostWithGameSuspended() {
        launcher.getGame().stop();
        progress = launcher.getGame().isInProgress();
        launcher.getGame().move(player, Direction.EAST);
        launcher.getGame().move(player, Direction.EAST);
        assertEquals(progress, launcher.getGame().isInProgress());
        assertFalse(progress);
    }
}
