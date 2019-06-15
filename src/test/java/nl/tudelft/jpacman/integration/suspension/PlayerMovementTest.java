package nl.tudelft.jpacman.integration.suspension;

import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.board.Unit;
import nl.tudelft.jpacman.game.Game;
import nl.tudelft.jpacman.level.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
        launcher.withMapFile("/Scenario2.txt").launch();
    }

    /**
     * Close the user interface.
     */
    @AfterEach
    public void after() {
        launcher.dispose();
    }

    /**
     *Check if pellet disappears and score increases.
     */
    @Test
    public void ScenarioOne() {
        Game game = launcher.getGame();

        Player player = game.getPlayers().get(0);
        List<Unit> pelletStart = game.getLevel().getBoard().squareAt(3, 1).getOccupants(); //get pellet start location

        game.start();
        assertEquals(0,player.getScore()); //start with 0 points
        assertEquals(2,game.getLevel().remainingPellets()); //2 pellets in map
        assertEquals(1,pelletStart.size()); //Pellet square should only contain a pellet nothing else
        assertEquals("class nl.tudelft.jpacman.level.Pellet",pelletStart.get(0).getClass().toString()); //check if the ghost square contains a pellet

        game.move(player,Direction.EAST); //Eat pellet and get pojnts
        List<Unit> pelletEnd = game.getLevel().getBoard().squareAt(3, 1).getOccupants(); //get pellet start location

        assertEquals(10,player.getScore()); //new total should be 10
        assertEquals(1,game.getLevel().remainingPellets()); //1 pellet in map
        assertEquals(1,pelletStart.size()); //Pellet square now only contains a player
        assertEquals("class nl.tudelft.jpacman.level.Player",pelletEnd.get(0).getClass().toString()); //check if the pellet square contains a player now
    }

    @Test
    public void ScenarioTwo() {
        Game game = launcher.getGame();

        Player player = game.getPlayers().get(0);
        game.getLevel().move(player,Direction.WEST);
    }
}
