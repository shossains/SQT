package nl.tudelft.jpacman.game;

import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.MultiLevelLauncher;
import nl.tudelft.jpacman.board.Direction;
//import nl.tudelft.jpacman.level.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Test class for Game using MultiLevelLauncher.
 * This class extends from GameStateTest.
 */
public class GameStateMultiLauncherTest extends GameStateTest {
    private MultiLevelLauncher launcher;
//    private Player player;
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
//        player = launcher.getGame().getPlayers().get(0);
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
}
