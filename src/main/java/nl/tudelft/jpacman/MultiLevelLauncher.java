package nl.tudelft.jpacman;

import nl.tudelft.jpacman.game.GameFactory;
import nl.tudelft.jpacman.game.MultiLevelGame;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.points.PointCalculator;
import nl.tudelft.jpacman.points.PointCalculatorLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Creates and launches the JPacMan UI.
 *
 */
public class MultiLevelLauncher extends Launcher {

    private MultiLevelGame multiGame;
    private static String[] mapStrings = {"/board.txt", "/level2.txt",
        "/level3.txt", "/level4.txt"};
    private String[] levelMap = mapStrings;

    /**
     * @return The game object this launcher will start when {@link #launch()}
     *         is called.
     */
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
            for (String mapString : levelMap) {
                Level level = getMapParser().parseMap(mapString);
                levels.add(level);
            }
            return levels;
        } catch (IOException e) {
            throw new PacmanConfigurationException(
                "Unable to create levels, name = " + getLevelMap(), e);
        }
    }

    /**
     * Set the name(s) of the file(s) containing all level maps.
     *
     * @param fileNames
     *            Maps to be used.
     * @return Levels corresponding to the given map.
     */
    public Launcher withMapFile(String[] fileNames) {
        levelMap = fileNames.clone();
        return this;
    }

    /**
     * Main execution method for the Launcher.
     *
     * @param args
     *            The command line arguments - which are ignored.
     * @throws IOException
     *             When a resource could not be read.
     */
    public static void main(String[] args) throws IOException {
        new MultiLevelLauncher().launch();
    }

}
