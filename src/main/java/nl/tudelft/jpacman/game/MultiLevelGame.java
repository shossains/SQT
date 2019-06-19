package nl.tudelft.jpacman.game;

import com.google.common.collect.ImmutableList;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.points.PointCalculator;

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

    /**
     *
     * @param player  The player.
     * @param levels List of levels.
     * @param pointCalculator The way to calculate points upon collisions.
     */
    protected MultiLevelGame(Player player, List<Level> levels, PointCalculator pointCalculator) {
        super(pointCalculator);
        this.player = player;
        this.levels = levels;
    }

    @Override
    public Level getLevel() {
        return levels.get(0);
    }

    @Override
    public List<Player> getPlayers() {
        return ImmutableList.of(player);
    }

    @Override
    public void levelWon() {
        if (levels.isEmpty()) {
            System.out.println("MultiLevelGame.java line 50");
            stop();
        } else {
            System.out.println("MultilevelGame.java line 53 \n");
            getLevel().start();
        }
    }
}
