package nl.tudelft.jpacman.game;

import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.points.PointCalculator;

import java.util.List;

public class MultiLevelGame extends Game {
    private MultiLevelGame multiGame;

    /**
     * Creates a new game.
     *
     * @param pointCalculator The way to calculate points upon collisions.
     */
    protected MultiLevelGame(PointCalculator pointCalculator) {
        super(pointCalculator);
    }

    @Override
    public List<Player> getPlayers() {
        return null;
    }

    @Override
    public Level getLevel() {
        return null;
    }

    @Override
    public MultiLevelGame getGame() {
        return multiGame;
    }
}
