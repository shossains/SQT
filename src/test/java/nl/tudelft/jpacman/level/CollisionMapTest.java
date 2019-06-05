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
 * Abstract CollisionMap class used to test PlayerCollision and DefaultPlayerInteractionMap.
 */
public abstract class CollisionMapTest {

    private PointCalculator pointCalculator;
    private CollisionMap collisionMap;
    private Player player;
    private Pellet pellet;
    private Ghost ghost;
    private Unit empty;


    /**
     * Abstract method which instantiates a CollisionMap.
     * Used by child classes to test their respective class.
     *
     * @return collisionMap Abstract CollisionMap created for testing.
     * @param pointCalculator PointCalculator which calculates the points.
     */
    public abstract CollisionMap createCollisionMap(PointCalculator pointCalculator);

    /**
     * Creates all objects before each test.
     */
    @BeforeEach
    void setup() {
        pointCalculator = mock(PointCalculator.class);
        collisionMap = createCollisionMap(pointCalculator);
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
        collisionMap.collide(player, empty);
        verifyNoMoreInteractions(pointCalculator);
    }

    /**
     * Tests case Ghost moves to empty square.
     * Nothing should happen.
     */
    @Test
    void testGhostToEmpty() {
        collisionMap.collide(ghost, empty);
        verifyNoMoreInteractions(pointCalculator);
    }

    /**
     * Tests special case where Pellet moves to empty square.
     * Nothing should happen.
     */
    @Test
    void testPelletToEmpty() {
        collisionMap.collide(pellet, empty);
        verifyNoMoreInteractions(pointCalculator);
    }

    /**
     * Tests case where player moves to square with pellet.
     * The PointCalculator should increase the points and the pellet should disappear.
     */
    @Test
    void testPlayerToPellet() {
        collisionMap.collide(player, pellet);
        verify(pointCalculator).consumedAPellet(player, pellet);
        verify(pellet).leaveSquare();
    }

    /**
     * Tests special case where Pellet moves to square with player.
     * The PointCalculator should increase the points and the pellet should disappear.
     */
    @Test
    void testPelletToPlayer() {
        collisionMap.collide(pellet, player);
        verify(pointCalculator).consumedAPellet(player, pellet);
        verify(pellet).leaveSquare();
    }

    /**
     * Tests case where player moves to square with ghost.
     * Ghosts should kill the player.
     */
    @Test
    void testPlayerToGhost() {
        collisionMap.collide(player, ghost);
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
        collisionMap.collide(ghost, player);
        verify(player).setAlive(false);
        verify(player).setKiller(ghost);
    }

    /**
     * Tests case where ghost moves to square with pellet.
     * Nothing should happen with the PointCalculator.
     */
    @Test
    void testGhostOnPellet() {
        collisionMap.collide(ghost, pellet);
        verifyNoMoreInteractions(pointCalculator);
    }

    /**
     * Tests special case where pellet moves to square with ghost.
     * Nothing should happen with the PointCalculator.
     */
    @Test
    void testPelletToGhost() {
        collisionMap.collide(pellet, ghost);
        verifyNoMoreInteractions(pointCalculator);
    }


    /**
     * Tests special case where pellet moves to square with pellet.
     * Nothing should happen with the PointCalculator.
     */
    @Test
    void testPelletToPellet() {
        Pellet pellet2 = mock(Pellet.class);
        collisionMap.collide(pellet2, pellet);
        verifyNoMoreInteractions(pointCalculator);
    }

    /**
     * Tests special case where player moves to square with player.
     * Nothing should happen with the PointCalculator.
     */
    @Test
    void testPlayerToPlayer() {
        Player player2 = mock(Player.class);
        collisionMap.collide(player2, player);
        verifyNoMoreInteractions(pointCalculator);

    }

    /**
     * Tests case where ghost moves to square with ghost.
     * Nothing should happen with the PointCalculator.
     */
    @Test
    void testGhostToGhost() {
        Ghost ghost2 = mock(Ghost.class);
        collisionMap.collide(ghost2, ghost);
        verifyNoMoreInteractions(pointCalculator);
    }

    /**
     * Tests special case where empty square moves to empty square.
     * Nothing should happen with the PointCalculator.
     */
    @Test
    void testEmptyToEmpty() {
        Unit empty2 = mock(Unit.class);
        collisionMap.collide(empty2, empty);
        verifyNoMoreInteractions(pointCalculator);
    }


}
