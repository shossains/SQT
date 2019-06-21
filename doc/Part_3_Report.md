# Report Part 3

### Exercise 1
See integration.suspension.SuspendSysemTest.java

### Exercise 2
See integration.suspension.PlayerMovementTest.java

### Exercise 3
Scenario 2.4 and 2.5 are harder to test with the default map because to make sure the player is next
a ghost requires a form of logic to be implemented. These scenarios require the game to be in a
specific state which is hard to achieve compared to the previous scenarios

### Exercise 4
See integration.suspension.PlayerMovementTest.java

### Exercise 5
Since the ghost is an AI and has it's own movement system it is very hard to control the ghost's
movements to get it in the necessary state.

### Exercise 6
See Document files.Part_3_Report.docx

### Exercise 7
See Document files.Part_3_Report.docx

### Exercise 8
See Document files.Part_3_Report.docx

### Exercise 9
See game.GameStateTestOld.java

### Exercise 10

##### Story 5: Multi-level experience
```text
As a player, if I win a level, 
I want to play 3 more levels before ending the game.

Scenario S5.1: Go to next level
 Given I won on (the) previous level(s),
and there are 3 levels or less left;
Start the next level.

Scenario S5.2: End the game
 Given I won on (the) previous level(s),
and there are 0 levels left;
Game has been won and player wins.
```
    
### Exercise 11
See Document files.Part_3_Report.docx


### Exercise 12 
See Document files.Part_3_Report.docx

### Exercise 13
See game.MultiLevelGame.java

### Exercise 14
See MultiLevelLauncher.java

### Exercise 15
We made an abstract class out of the original GameStateTest (test.game.GameStateTest.java) and 
created two childclasses (test.game.GameStateMultiLauncherTest.java and test.game.GameStateLauncherTest.java)

### Exercise 16
See same classes as ex.15

### Exercise 17
See same classes as ex.15

### Exercise 18
Our coverage of the MultiLevelLauncher is 85%. The only branches not covered were the exception
branch from the makeLevels class which is impossible to recreate and the main method which is not
testable.

### Exercise 19
Pro's:
- Our implementation of the MultiLevelLauncher is not hardcoded and versatile. The (number) of levels
The user wants to play is completely up the user itself. If five maps are chosen, all five will be
parsed and all five will be played consecutively.
- The hierarchy used in the implementation such as Game > singleplayer or multiplayer || Ghost >
Inky or Blinky or ect. made this game very easy to understand and expendable.
- The JavaDoc provided by the developers gave good insight on what several methods did (especially
the AI part).

Con's:
- Several checkstyle rules such as ``to many character per line`` are annoying and usually don't
provide improved code quality. A solution would be to increase the number of character per line.
- The usage of PlayerList is ambiguous since there only will be one player. Just use one Player
object instead.
- The implementation of the keypress is not working properly, if the keypress is hold there is a
short delay followed by Pacman moving very rapidly. The optimal solution to this is Pacman to
shorten the delay and slow down the pacman movement afterwards.