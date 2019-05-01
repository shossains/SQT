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
