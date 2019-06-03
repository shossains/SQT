package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.points.PointCalculator;

/**
 * Test class for DefaultPlayerInteractionMap.
 * This class extends from CollisionMapTest.
 */
public class DefaultPlayerInteractionMapTest extends CollisionMapTest{

    /**
     * Method to override the abstract method in CollisionMapTest.
     * Instantiates a DefaultPlayerInteractionMap instead of a CollisionMap.
     * @param pointCalculator PointCalculator which calculates the points.
     * @return A DefaultPlayerInteractionMap object.
     */
    @Override
    public CollisionMap createCollisionMap(PointCalculator pointCalculator) {
        return new DefaultPlayerInteractionMap(pointCalculator);
    }
}
