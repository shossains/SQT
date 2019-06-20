package nl.tudelft.jpacman.game;

import nl.tudelft.jpacman.Launcher;

/**
 * Test class for Game using Launcher.
 * This class extends from GameStateTest.
 */
public class GameStateLauncherTest extends GameStateTest {

    /**
     * Method to override the abstract method in GameStateTest.
     * Instantiates a Launcher.
     * @return new Launcher.
     */
    @Override
    public Launcher createLauncher() {
        return new Launcher();
    }
}
