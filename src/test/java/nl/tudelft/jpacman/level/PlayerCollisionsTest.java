package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.points.PointCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

public class PlayerCollisionsTest {
    private PointCalculator pointCalculator;
    private PlayerCollisions playerCollisions;
    private Player player;
    private Pellet pellet;
    private Ghost ghost;

    @BeforeEach
    void setup() {
        pointCalculator = mock(PointCalculator.class);
        playerCollisions = new PlayerCollisions(pointCalculator);
        player = mock(Player.class);
        pellet = mock(Pellet.class);
        ghost = mock(Ghost.class);
    }

    @Test
    void testPlayerToEmpty() {

    }

    @Test
    void testPlayerToPellet() {

    }

    @Test
    void testPlayerToGhost() {

    }

    @Test
    void testPlayerToWall() {

    }

    @Test
    void testPlayerToLastPellet() {

    }

    @Test
    void testGhostOnPellet() {

    }

    @Test
    void testGhostToPlayer() {

    }

    @Test
    void testGhostOffPellet() {

    }

    @Test
    void testGhostToEmpty() {

    }

}
