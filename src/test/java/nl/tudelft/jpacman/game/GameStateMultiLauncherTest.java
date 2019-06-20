package nl.tudelft.jpacman.game;

import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.MultiLevelLauncher;

/**
 * Test class for Game using MultiLevelLauncher.
 * This class extends from GameStateTest.
 */
public class GameStateMultiLauncherTest extends GameStateTest {

    /**
     * Method to override the abstract method in GameStateTest.
     * Instantiates a MultiLevelLauncher.
     * @return new MultiLevelLauncher.
     */
    @Override
    public Launcher createLauncher() {
        return new MultiLevelLauncher();
    }
}
