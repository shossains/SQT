# Report Part 1

### Exercise 6
The class LevelFactory is not well tested.
Line 111 calls an red enemy sprite when no color is called.
This is not tested because the color of the sprite is always defined.
<br/><br/>
CollisionInteractionMap.java is not covered at all.
This implies that this class is never called by executing this test.

### Exercise 7
Yes the move method is covered by the smoke test

The test expected the player to move East but this did not happen.
The failure shows that the expected value is expected to be 10 but was 0.
From this information it is clear that there is no movement.

### Exercise 8
The test gives exactly the same failure.
But now you can not be sure were the error originates from.
The error is too high up the hierarchy to be found immediately.
Therefore the error will be hard to find and resolve

### Exercise 9
The Game class can be seen as container as such.
It creates the point system, fetches the players and level.
This class is also in charge for ending the game.

The Level class creates all units, which includes the player,
enemies and pellet's. It also creates the grid and board that 
the game is played on.

### Exercise 10
**2.** 

Good weather: </br>
Implementing two test cases will make sure Clyde works as expected.
- Test Clyde is close to Pacman and if he is moving away from him.
- Test if Clyde is moving near to Pacman if their distance is greater than 8 squares.

Bad weather: <br/>
- Test Clyde's behaviour if the player isn't on the board.
- Test Clyde's behaviour if there is a null square from the board

 See source code 

### Exercise 11
Good weather: </br>
- Test Inky's behaviour if Inky is behind Blinky.
- Test Inky's behaviour if Inky is in front of Blinky.

Bad weather: <br/>
- Test Inky's behaviour if Blinky isn't on the board.
- Test Inky's behaviour if player isn't on the board.
- Test Inky's behaviour if 

### Exercise 12
see 'Boundary test' word file       
           
### Exercise 13
```java
    @ParameterizedTest
    @CsvSource({
        "0, 0, true", "-1, 1, false", "3, 2, false", "2, 3, true",
        "1, 0, true", "1, -1, false", "2, 4, false", "0, 3, true"
    })
    public void TestWithinBorders(int x, int y, boolean expected) {
        Square[][] grid = {
            { mock(Square.class), mock(Square.class), mock(Square.class),  mock(Square.class) },
            { mock(Square.class), mock(Square.class), mock(Square.class),  mock(Square.class) },
            { mock(Square.class), mock(Square.class), mock(Square.class),  mock(Square.class) }
        };
        board = new Board(grid);

        assertEquals(board.withinBorders(x,y), expected);

    }
```

### Exercise 14

### Exercise 15



           