package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.board.Unit;
import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.points.PointCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlayerCollisionsTest {
    private PointCalculator pointCalculator;
    private PlayerCollisions playerCollisions;
    private Player player;
    private Pellet pellet;
    private Ghost ghost;
    private Unit empty;

    @BeforeEach
    void setup() {
        pointCalculator = mock(PointCalculator.class);
        playerCollisions = new PlayerCollisions(pointCalculator);
        player = mock(Player.class);
        pellet = mock(Pellet.class);
        empty = mock(Unit.class);
        ghost = mock(Ghost.class);

    }

    @Test
    void testPlayerToEmpty() {
        playerCollisions.collide(player, empty);


    }

    @Test
    void testPlayerToPellet() {
        playerCollisions.playerVersusPellet(player, pellet);
    }

    @Test
    void testPlayerToGhost() {
        playerCollisions.playerVersusGhost(player, ghost);

    }


    @Test
    void testPlayerToLastPellet() {
        playerCollisions.playerVersusPellet(player, pellet);
    }

    @Test
    void testGhostOnPellet() {
        playerCollisions.collide(ghost, pellet);
    }

    @Test
    void testGhostToPlayer() {
        playerCollisions.collide(ghost, player);
    }

    @Test
    void testGhostOffPellet() {
        playerCollisions.collide(ghost, pellet);
        playerCollisions.collide(ghost, empty);
    }

    @Test
    void testGhostToEmpty() {
        playerCollisions.collide(ghost, empty);
    }

}
