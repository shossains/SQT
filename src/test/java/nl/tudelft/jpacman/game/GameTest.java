package nl.tudelft.jpacman.game;

import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.points.PointCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

/**
 * Class to test the game.
 */
public class GameTest {
    private PointCalculator pc;
    private Player player;
    private Level level;
    private Game game;

    /**
     * Setting up the mocks for the tests.
     */
    @BeforeEach
    void setup() {
        pc = mock(PointCalculator.class);
        player = mock(Player.class);
        level = mock(Level.class);
        game = new SinglePlayerGame(player, level, pc);
        when(level.isAnyPlayerAlive()).thenReturn(true);
        when(level.remainingPellets()).thenReturn(1);
    }

    /**
     * Test to see if starting and stopping the game works.
     */
    @Test
    void testStartStop() {
        assertThat(game.isInProgress()).isFalse();
        game.start();
        assertThat(game.isInProgress()).isTrue();
        verify(level, times(1)).start();
        game.stop();
        assertThat(game.isInProgress()).isFalse();
    }

    /**
     * Test to see if the game not starts again after already being started.
     */
    @Test
    void testStartAndStartAgain() {
        assertThat(game.isInProgress()).isFalse();
        game.start();
        assertThat(game.isInProgress()).isTrue();
        verify(level, times(1)).start();
        game.start();
        assertThat(game.isInProgress()).isTrue();
        verify(level, times(1)).start();
    }

    /**
     * Test to see if the game isn't in progress if there are no players.
     */
    @Test
    void testStartNoPlayer() {
        when(level.isAnyPlayerAlive()).thenReturn(false);
        game.start();
        assertFalse(game.isInProgress());
    }

    /**
     * Test to see if the game isn't in progress if there are no pellets.
     */
    @Test
    void testStartNoPellet() {
        when(level.remainingPellets()).thenReturn(0);
        game.start();
        assertFalse(game.isInProgress());
    }

}
