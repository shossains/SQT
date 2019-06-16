package nl.tudelft.jpacman.game;

import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.level.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameStateTest {
    private Launcher launcher;
    private Player player;


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
     * Tests for when start button has been pressed and the GUI has been launched.
     * Expected: Progress should be the same after start has been pressed twice and progress should be true.
     */
    @Test
    void testStartButtonWithGUILaunched() {
        launcher.getGame().start();
        boolean progress = launcher.getGame().isInProgress();
        launcher.getGame().start();
        assertEquals(progress, launcher.getGame().isInProgress());
        assertTrue(progress);
    }

    /**
     * Tests for when start button has been pressed and the game has started.
     * Expected:
     */
    @Test
    void testStartButtonWithGameStarted() {

    }

    /**
     * Tests for when start button has been pressed and the player has won.
     */
    @Test
    void testStartButtonWithGameLost() {

    }

    /**
     * Tests for when start button has been pressed and the player has lost.
     */
    @Test
    void testStartButtonWithGameWon() {

    }

    /**
     * Tests for when start button has been pressed and the game has been suspended.
     */
    @Test
    void testStartButtonWithGameSuspended() {

    }

    //Test cases for stop button

    /**
     * Tests for when stop button has been pressed and the GUI has been launched
     */
    @Test
    void testStopButtonWithGUILaunched() {

    }

    /**
     * Tests for when stop button has been pressed and the game has started.
     */
    @Test
    void testStopButtonWithGameStarted() {

    }

    /**
     * Tests for when stop button has been pressed and the player has won.
     */
    @Test
    void testStopButtonWithGameLost() {

    }

    /**
     * Tests for when stop button has been pressed and the player has lost.
     */
    @Test
    void testStopButtonWithGameWon() {

    }

    /**
     * Tests for when stop button has been pressed and the game has been suspended.
     */
    @Test
    void testStopButtonWithGameSuspended() {

    }

    //Test cases for last pellet eaten.

    /**
     * Tests for when last pellet has been consumed and the GUI has been launched
     */
    @Test
    void testLastPelletWithGUILaunched() {

    }

    /**
     * Tests for when last pellet has been consumed and the game has started.
     */
    @Test
    void testLastPelletWithGameStarted() {

    }

    /**
     * Tests for when last pellet has been consumed and the player has won.
     */
    @Test
    void testLastPelletWithGameLost() {

    }

    /**
     * Tests for when last pellet has been consumed and the player has lost.
     */
    @Test
    void testLastPelletWithGameWon() {

    }

    /**
     * Tests for when last pellet has been consumed and the game has been suspended.
     */
    @Test
    void testLastPelletWithGameSuspended() {

    }

    //Test cases for when eaten by ghost.

    /**
     * Tests for when ghost has eaten Pacman and the GUI has been launched
     */
    @Test
    void testEatenByGhostWithGUILaunched() {

    }

    /**
     * Tests for when ghost has eaten Pacman and the game has started.
     */
    @Test
    void testEatenByGhostWithGameStarted() {

    }

    /**
     * Tests for when ghost has eaten Pacman and the player has won.
     */
    @Test
    void testEatenByGhostWithGameLost() {

    }

    /**
     * Tests for when ghost has eaten Pacman and the player has lost.
     */
    @Test
    void testEatenByGhostWithGameWon() {

    }

    /**
     * Tests for when ghost has eaten Pacman and the game has been suspended.
     */
    @Test
    void testEatenByGhostWithGameSuspended() {

    }
}
