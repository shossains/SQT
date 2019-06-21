package nl.tudelft.jpacman.game;

import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.MultiLevelLauncher;
import nl.tudelft.jpacman.PacmanConfigurationException;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.level.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;



/**
 * Test class for Game using MultiLevelLauncher.
 * This class extends from GameStateTest.
 */
public class GameStateMultiLauncherTest extends GameStateTest {
    private MultiLevelLauncher launcher;
    private Player player;
    private boolean progress;

    /**
     * Method to override the abstract method in GameStateTest.
     * Instantiates a MultiLevelLauncher.
     * @return new MultiLevelLauncher.
     */
    @Override
    public Launcher createLauncher() {
        return new MultiLevelLauncher();
    }

    /**
     * Setting up things for the tests.
     */
    @BeforeEach
    void setUpExtra() {
        launcher = (MultiLevelLauncher) createLauncher();
        String[] maps = {"/testmap1.txt", "/testmap2.txt"};
        launcher.withMapFile(maps);
        launcher.launch();
        player = launcher.getGame().getPlayers().get(0);
    }

    /**
     * Test multilevel functionality.
     */
    @Test
    void testMultiLevel() {
        assertFalse(launcher.getGame().isInProgress());
    }

    @Override
    @Test
    void testLastPelletWithGameWon() {
        launcher.getGame().start();
        progress = launcher.getGame().isInProgress();
        launcher.getGame().move(launcher.getGame().getPlayers().get(0), Direction.WEST);
        assertEquals(progress, launcher.getGame().isInProgress());
        assertTrue(progress);
    }

    /**
     * Setting up things for the tests.
     */
    @Test
    void testFileNotFoundException() {
        launcher = (MultiLevelLauncher) createLauncher();
        String[] maps = {"/testma.txt", "/testm2.txt"};
        launcher.withMapFile(maps);

        assertThrows(PacmanConfigurationException.class,
            () -> launcher.launch(),
            "Could not get resource for: /testma.txt");
    }

    /**
     * Testing the case when LOST and the Last Pellet eaten
     * in the last level.
     */
    @Test
    void TestLostPelletLast() {
        launcher.getGame().levelWon();
        launcher.getGame().levelWon();
        launcher.getGame().levelWon();
        launcher.getGame().start();
        boolean state = launcher.getGame().isInProgress();
        launcher.getGame().levelLost();
        progress = launcher.getGame().isInProgress();
        assertEquals(!state, progress);
        launcher.getGame().move(player, Direction.EAST);
        assertEquals(progress, launcher.getGame().isInProgress());
        assertFalse(progress);

    }

    /**
     * Testing the case when SUSPENDED and the Last Pellet eaten
     * in the last level.
     */
    @Test
    void testSuspendedPelletLast() {
        launcher.getGame().levelWon();
        launcher.getGame().levelWon();
        launcher.getGame().levelWon();
        launcher.getGame().start();
        launcher.getGame().stop();
        progress = launcher.getGame().isInProgress();
        launcher.getGame().move(player, Direction.EAST);
        assertEquals(progress, launcher.getGame().isInProgress());
        assertFalse(progress);

    }


    /**
     * Testing the case when Launched and the Last Pellet eaten
     * in the last level.
     */
    @Test
    void testLaunchedPelletLast() {
        launcher.getGame().levelWon();
        launcher.getGame().levelWon();
        launcher.getGame().levelWon();
        launcher.launch();
        progress = launcher.getGame().isInProgress();
        launcher.getGame().move(player, Direction.EAST);
        assertEquals(progress, launcher.getGame().isInProgress());
        assertFalse(progress);
    }


    /**
     * Testing the case when WON and the Last Pellet eaten
     *  in the last level.
     */
    @Test
    void testWonPelletLast() {
        launcher.getGame().start();
        launcher.getGame().levelWon();
        launcher.getGame().levelWon();
        launcher.getGame().levelWon();
        launcher.getGame().levelWon();
        progress = launcher.getGame().isInProgress();
        launcher.getGame().move(player, Direction.EAST);
        assertEquals(progress, launcher.getGame().isInProgress());
        assertFalse(progress);

    }


    /**
     * Testing the case when LOST and the Last Pellet eaten
     *not in the last level.
     */
    @Test
    void testLostPelletNotLast() {
        launcher.getGame().start();
        launcher.getGame().levelWon();
        launcher.getGame().levelWon();
        launcher.getGame().levelLost();
        progress = launcher.getGame().isInProgress();
        launcher.getGame().move(player, Direction.EAST);
        assertEquals(progress, launcher.getGame().isInProgress());
        assertFalse(progress);
    }

    /**
     * Testing the case when SUSPENDED and the Last Pellet eaten
     * not in the last level.
     */
    @Test
    void testSuspendedPelletNotLast() {
        launcher.getGame().start();
        launcher.getGame().levelWon();
        launcher.getGame().levelWon();
        launcher.getGame().stop();
        progress = launcher.getGame().isInProgress();
        launcher.getGame().move(player, Direction.EAST);
        assertEquals(progress, launcher.getGame().isInProgress());
        assertFalse(progress);
    }

    /**
     * Testing the case when Launched and the Last Pellet eaten
     * not in the last level.
     */
    @Test
    void testLaunchedPelletNotLast() {
        launcher.getGame().start();
        launcher.getGame().levelWon();
        launcher.getGame().levelWon();
        launcher.launch();
        progress = launcher.getGame().isInProgress();
        launcher.getGame().move(player, Direction.EAST);
        assertEquals(progress, launcher.getGame().isInProgress());
        assertFalse(progress);
    }


    /**
     * Testing the case when WON and the Last Pellet eaten
     * not in the last level.
     */
    @Test
    void testWonPelletNotLast() {
        launcher.getGame().start();
        launcher.getGame().levelWon();
        launcher.getGame().levelWon();
        launcher.launch();
        progress = launcher.getGame().isInProgress();
        launcher.getGame().move(player, Direction.EAST);
        assertEquals(progress, launcher.getGame().isInProgress());
        assertFalse(progress);
    }
}
