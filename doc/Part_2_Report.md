# Report Part 2

### Exercise 1
Some good weather cases we thought of:
 - Verifying if methods from mocked classes in method makeGhostSquare are used.
 - Test for empty space ( ) in addSquare method.
 - Test for wall (#) in addSquare method.
 - Test for pellet (.) in addSquare method.
 - Test for ghost (G) in addSquare method.
 - Test for pacman (P) in addSquare method.

 
### Exercise 2
Some bad weather cases we thought of:
 - Test if exception is thrown when an invalid char is used in addSquare method.
 - Test if exception is thrown when an invalid position is used in addSquare method.
 - Test if exception is thrown when an text map is null in parseMap method.
 - Test if exception is thrown when an empty ArrayList is used in parseMap method.
 - Test if exception is thrown when an only an empty string inside 
 the ArrayList is used in parseMap method.
 - Test if exception is thrown when text map lines, which are not of equal width,
  are used in parseMap method.
 - Test if exception is thrown when an empty string filename is used in parseMap method.
 - Test if exception is thrown when an inputStream with an empty resource is used in parseMap method.
 
### Exercise 3
With these test together we have achieved `100% Class coverage`
, `57% Method coverage`, `70% Line coverage` 
```java
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
```
### Exercise 4
| Tables        | Are           | Cool  |
| ------------- |:-------------:| -----:|
| col 3 is      | right-aligned | $1600 |
| col 2 is      | centered      |   $12 |
| zebra stripes | are neat      |    $1 |


### Exercise 5
See level.PlayerCollisionTest_deprecated

### Exercise 6
See level.CollisionMapTest, level.PlayerCollisionTest and level.DefaultPlayerInteractionTest

### Exercise 7

### Exercise 8

### Exercise 9

### Exercise 10

### Exercise 11

### Exercise 12

### Exercise 13

### Exercise 14

### Exercise 15

### Exercise 16

### Exercise 17