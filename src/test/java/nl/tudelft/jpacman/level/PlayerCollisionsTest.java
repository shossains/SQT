package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.points.PointCalculator;

/**
 * Test class for PlayerCollision.
 * This class extends from CollisionMapTest.
 */
public class PlayerCollisionsTest extends CollisionMapTest {

    /**
     * Method to override the abstract method in CollisionMapTest.
     * Instantiates a PlayerCollision instead of a CollisionMap.
     * @param pointCalculator PointCalculator which calculates the points.
     * @return A PlayerCollision object.
     */
    @Override
    public CollisionMap createCollisionMap(PointCalculator pointCalculator) {
        return new PlayerCollisions(pointCalculator);
    }
}
