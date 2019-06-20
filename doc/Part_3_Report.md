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


### Exercise 12 
 
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

### Exercise 19
