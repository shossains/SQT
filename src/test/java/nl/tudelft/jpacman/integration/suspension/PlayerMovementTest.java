package nl.tudelft.jpacman.integration.suspension;

import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.game.Game;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.LevelFactory;
import nl.tudelft.jpacman.level.Pellet;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.level.PlayerFactory;
import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.npc.ghost.GhostFactory;
import nl.tudelft.jpacman.npc.ghost.GhostMapParser;
import nl.tudelft.jpacman.points.DefaultPointCalculator;
import nl.tudelft.jpacman.points.PointCalculator;
import nl.tudelft.jpacman.sprite.PacManSprites;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * An example test class that conducts integration tests.
 */
public class PlayerMovementTest {

    private Launcher launcher;

    @BeforeEach
    void setUp() {
        launcher = new Launcher();
        launcher.withMapFile("/map.txt").launch();
    }

    /**
     *
     */
    @Test
    public void ScenarioOne() throws InterruptedException {
        Game game = launcher.getGame();
        Player player = game.getPlayers().get(0);
        Thread.sleep(2000);

        // start cleanly.
        assertThat(game.isInProgress()).isFalse();
        game.start();

         //eat pellet and earn points
        assertEquals(player.getScore(),10);

    }

}
