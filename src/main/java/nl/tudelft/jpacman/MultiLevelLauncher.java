package nl.tudelft.jpacman;

import nl.tudelft.jpacman.game.GameFactory;
import nl.tudelft.jpacman.game.MultiLevelGame;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.points.PointCalculator;
import nl.tudelft.jpacman.points.PointCalculatorLoader;
import nl.tudelft.jpacman.ui.PacManUI;
import nl.tudelft.jpacman.ui.PacManUiBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Creates and launches the JPacMan UI.
 *
 */
public class MultiLevelLauncher extends Launcher {

    private final int amountlvl = 4;
    private MultiLevelGame multiGame;
    private static String[] mapStrings = {"/board.txt", "/boardTest.txt", "/boardTest.txt", "/board.txt"};


    @Override
    public MultiLevelGame getGame() {
        return multiGame;
    }

    @Override
    public MultiLevelGame makeGame() {
        GameFactory gf = getGameFactory();
        List<Level> levels = makeLevels();
        multiGame = gf.createMultiLevelGame(levels, loadPointCalculator());
        return multiGame;
    }

    private PointCalculator loadPointCalculator() {
        return new PointCalculatorLoader().load();
    }

    /**
     * Creates List containing multiple levels.
     *
     * @return List of levels.
     */
    public List<Level> makeLevels() {
        try {
            List<Level> levels = new ArrayList<>();
            for (int i = 0; i < amountlvl; i++) {
                Level level = getMapParser().parseMap(mapStrings[i]);
                levels.add(level);
            }
            return levels;
        } catch (IOException e) {
            throw new PacmanConfigurationException(
                "Unable to create levels, name = " + getLevelMap(), e);
        }
    }

    public static void main(String[] args) {
        new MultiLevelLauncher().launch();
    }


}
