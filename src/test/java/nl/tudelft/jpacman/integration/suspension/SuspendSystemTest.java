package nl.tudelft.jpacman.integration.suspension;

import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.game.Game;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.npc.Ghost;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * An example test class that conducts integration tests.
 */
public class SuspendSystemTest {

    private Launcher launcher;

    /**
     * Start a launcher, which can display the user interface.
     */
    @BeforeEach
    public void before() {
        launcher = new Launcher();

    }

    /**
     * Close the user interface.
     */
    @AfterEach
    public void after() {
        launcher.dispose();
    }

    @Test
    public void testSuspendGame() {

    }

    /**
     * The simplest test that just starts the
     * game and checks it is indeed in progress.
     */
    @Test
    public void testRestartGame() {
        launcher.launch();
        getGame().start();
        assertThat(getGame().isInProgress()).isTrue();
        getGame().stop();
        assertThat(getGame().isInProgress()).isFalse();
        getGame().start();
        assertThat(getGame().isInProgress()).isTrue();
    }
    private Game getGame() {
        return launcher.getGame();
    }
}
