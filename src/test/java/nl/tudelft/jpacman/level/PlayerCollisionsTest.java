package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.board.Unit;
import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.points.PointCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * Test suite for the class PlayerCollisions.
 */
public class PlayerCollisionsTest {
    private PointCalculator pointCalculator;
    private PlayerCollisions playerCollisions;
    private Player player;
    private Pellet pellet;
    private Ghost ghost;
    private Unit empty;

    /**
     * Creates all objects before each test.
     */
    @BeforeEach
    void setup() {
        pointCalculator = mock(PointCalculator.class);
        playerCollisions = new PlayerCollisions(pointCalculator);
        player = mock(Player.class);
        pellet = mock(Pellet.class);
        empty = mock(Unit.class);
        ghost = mock(Ghost.class);

    }

    /**
     * Tests the case player moves to empty square.
     * Nothing should happen.
     */
    @Test
    void testPlayerToEmpty() {
        playerCollisions.collide(player, empty);
        verifyNoMoreInteractions(pointCalculator);
    }

    /**
     * Tests case Ghost moves to empty square.
     * Nothing should happen.
     */
    @Test
    void testGhostToEmpty() {
        playerCollisions.collide(ghost, empty);
        verifyNoMoreInteractions(pointCalculator);
    }

    /**
     * Tests special case where Pellet moves to empty square.
     * Nothing should happen.
     */
    @Test
    void testPelletToEmpty() {
        playerCollisions.collide(pellet, empty);
        verifyNoMoreInteractions(pointCalculator);
    }

    /**
     * Tests case where player moves to square with pellet.
     * The PointCalculator should increase the points and the pellet should disappear.
     */
    @Test
    void testPlayerToPellet() {
        playerCollisions.playerVersusPellet(player, pellet);
        verify(pointCalculator).consumedAPellet(player, pellet);
        verify(pellet).leaveSquare();
    }

    /**
     * Tests special case where Pellet moves to square with player.
     * The PointCalculator should increase the points and the pellet should disappear.
     */
    @Test
    void testPelletToPlayer() {
        playerCollisions.collide(pellet, player);
        verify(pointCalculator).consumedAPellet(player, pellet);
        verify(pellet).leaveSquare();
    }

    /**
     * Tests case where player moves to square with ghost.
     * Ghosts should kill the player.
     */
    @Test
    void testPlayerToGhost() {
        playerCollisions.playerVersusGhost(player, ghost);
        verify(pointCalculator).collidedWithAGhost(player, ghost);
        verify(player).setAlive(false);
        verify(player).setKiller(ghost);
    }

    /**
     * Tests case where ghost moves to square with player.
     * Ghosts should kill the player.
     */
    @Test
    void testGhostToPlayer() {
        playerCollisions.collide(ghost, player);
        verify(player).setAlive(false);
        verify(player).setKiller(ghost);
    }

    /**
     * Tests case where ghost moves to square with pellet.
     * Nothing should happen with the PointCalculator.
     */
    @Test
    void testGhostOnPellet() {
        playerCollisions.collide(ghost, pellet);
        verifyNoMoreInteractions(pointCalculator);
    }

    /**
     * Tests special case where pellet moves to square with ghost.
     * Nothing should happen with the PointCalculator.
     */
    @Test
    void testPelletToGhost() {
        playerCollisions.collide(pellet, ghost);
        verifyNoMoreInteractions(pointCalculator);
    }


    /**
     * Tests special case where pellet moves to square with pellet.
     * Nothing should happen with the PointCalculator.
     */
    @Test
    void testPelletToPellet() {
        Pellet pellet2 = mock(Pellet.class);
        playerCollisions.collide(pellet2, pellet);
        verifyNoMoreInteractions(pointCalculator);
    }

    /**
     * Tests special case where player moves to square with player.
     * Nothing should happen with the PointCalculator.
     */
    @Test
    void testPlayerToPlayer() {
        Player player2 = mock(Player.class);
        playerCollisions.collide(player2, player);
        verifyNoMoreInteractions(pointCalculator);

    }

    /**
     * Tests case where ghost moves to square with ghost.
     * Nothing should happen with the PointCalculator.
     */
    @Test
    void testGhostToGhost() {
        Ghost ghost2 = mock(Ghost.class);
        playerCollisions.collide(ghost2, ghost);
        verifyNoMoreInteractions(pointCalculator);
    }

    /**
     * Tests special case where empty square moves to empty square.
     * Nothing should happen with the PointCalculator.
     */
    @Test
    void testEmptyToEmpty() {
        Unit empty2 = mock(Unit.class);
        playerCollisions.collide(empty2, empty);
        verifyNoMoreInteractions(pointCalculator);
    }

}
