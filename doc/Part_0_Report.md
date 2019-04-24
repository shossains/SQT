# Report 0

### Exercise 1
```java
    /**
     * Do we get the correct delta when moving south?
     */
    @Test
    void testSouth() {
        Direction south = Direction.valueOf("SOUTH");
        assertThat(south.getDeltaY()).isEqualTo(1);
    }

    /**
     * Do we get the correct delta when moving west?
     */
    @Test
    void testWest() {
        Direction west = Direction.valueOf("WEST");
        assertThat(west.getDeltaX()).isEqualTo(-1);
    }

    /**
     * Do we get the correct delta when moving east?
     */
    @Test
    void testEast() {
        Direction east = Direction.valueOf("EAST");
        assertThat(east.getDeltaX()).isEqualTo(1);
    }
```
### Exercise 2
```java
    /**
     * Resets the unit under test.
     */
    @BeforeEach
    void setUp() {
        unit = new BasicUnit();
        square = new BasicSquare();
    }

    /**
     * Asserts that a unit has no square to start with.
     */
    @Test
    void noStartSquare() {
        Assertions.assertFalse(unit.hasSquare());
    }

    /**
     * Tests that the unit indeed has the target square as its base after
     * occupation.
     */
    @Test
    void testOccupy() {
       unit.occupy(square);
       assertThat(unit.getSquare()).isEqualTo(square);
    }

    /**
     * Test that the unit indeed has the target square as its base after
     * double occupation.
     */
    @Test
    void testReoccupy() {
        unit.occupy(square);
        Assertions.assertTrue(unit.hasSquare());
        unit.leaveSquare();
        Assertions.assertFalse(unit.hasSquare());
        unit.occupy(square);
        assertThat(unit.getSquare()).isEqualTo(square);
    }
```
