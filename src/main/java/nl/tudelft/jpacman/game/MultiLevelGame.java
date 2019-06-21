package nl.tudelft.jpacman.game;

import com.google.common.collect.ImmutableList;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.points.PointCalculator;


import javax.swing.JOptionPane;
import java.util.List;

/**
 * A game with one player and multiple levels.
 */
public class MultiLevelGame extends Game {

    /**
     * The player of this game.
     */
    private Player player;

    /**
     * List of levels of this game.
     */
    private List<Level> levels;
    private int currentLevel = 0;

    /**
     * Creates a new game.
     *
     * @param player  The player.
     * @param levels List of levels.
     * @param pointCalculator The way to calculate points upon collisions.
     */
    protected MultiLevelGame(Player player, List<Level> levels, PointCalculator pointCalculator) {
        super(pointCalculator);
        assert player != null;
        assert levels != null;

        this.player = player;
        this.levels = levels;
        this.levels.get(currentLevel).registerPlayer(player);
    }

    @Override
    public Level getLevel() {
        return levels.get(currentLevel);
    }

    @Override
    public List<Player> getPlayers() {
        return ImmutableList.of(player);
    }

    /**
     * If level is won, go to next level if there is one.
     */
    public void increaseLevel() {
        if (currentLevel < levels.size() - 1) {
            currentLevel++;
            levels.get(currentLevel).registerPlayer(player);
        } else {
            JOptionPane.showMessageDialog(null, "You've won");
            stop();
        }

    }

    @Override
    public void levelWon() {
        stop();
        increaseLevel();
    }
}
