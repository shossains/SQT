package nl.tudelft.jpacman.game;

import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.points.PointCalculator;

import java.util.List;

/**
 *
 */
public class MultiLevelGame extends Game {

    /**
     * The player of this game.
     */
    private Player player;

    /**
     * The level of this game.
     */
    private Level level;

    /**
     *
     * @param pointCalculator The way to calculate points upon collisions.
     */
    protected MultiLevelGame(PointCalculator pointCalculator) {
        super(pointCalculator);
    }

    @Override
    public Level getLevel() {
        return level;
    }

    @Override
    public List<Player> getPlayers() {
        return null;
    }
}
