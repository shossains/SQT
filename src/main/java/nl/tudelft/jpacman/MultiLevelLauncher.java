package nl.tudelft.jpacman;

import nl.tudelft.jpacman.game.Game;
import nl.tudelft.jpacman.game.MultiLevelGame;

/**
 * Creates and launches the JPacMan UI multilevel version.
 */
public class MultiLevelLauncher extends Launcher {

    private MultiLevelGame multiGame;

    @Override
    public MultiLevelGame getGame() {
        return multiGame;
    }

    @Override
    public MultiLevelGame makeGame() {
        return multiGame;
    }
}
