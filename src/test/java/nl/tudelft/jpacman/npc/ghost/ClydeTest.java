package nl.tudelft.jpacman.npc.ghost;

import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.game.Game;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.LevelFactory;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.level.PlayerFactory;
import nl.tudelft.jpacman.points.DefaultPointCalculator;
import nl.tudelft.jpacman.points.PointCalculator;
import nl.tudelft.jpacman.sprite.PacManSprites;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Class to test Clyde behavior.
 */
public class ClydeTest {

    private Launcher launcher;
    private Boolean temp = true;
    private GhostMapParser ghostMapParser;
    private Game game;

    @BeforeEach
    void setUp(){
        PacManSprites pacManSprites = new PacManSprites();
        BoardFactory boardFactory = new BoardFactory(pacManSprites);
        GhostFactory ghostFactory = new GhostFactory(pacManSprites);
        PointCalculator pointCalculator = new DefaultPointCalculator();

        LevelFactory levelFactory = new LevelFactory(pacManSprites, ghostFactory, pointCalculator);

        ghostMapParser = new GhostMapParser(levelFactory, boardFactory, ghostFactory);

        char[][] map = {{'#','#','#','#','#','#','#','#'}, // deprecated layout - GOTO resources/text.txt
                        {'#',' ',' ',' ',' ',' ',' ','#'},
                        {'#','P','.','.','.','.','G','#'},
                        {'#',' ',' ',' ',' ',' ',' ','#'},
                        {'#','#','#','#','#','#','#','#'}};

        PlayerFactory playerFactory = new PlayerFactory(pacManSprites); //create player
        Player player = playerFactory.createPacMan();

        Direction east = Direction.valueOf("EAST"); // set players direction east
        player.setDirection(east);

        List<Player> playerList = new ArrayList(); // create list of players
        playerList.add(player);

        Level level = ghostMapParser.parseMap(map); // create level
        level.registerPlayer(player);

        game = new Game(pointCalculator) { // create game
            @Override
            public List<Player> getPlayers() {
                return playerList;
            }

            @Override
            public Level getLevel() {
                return level;
            }
        };

        launcher = new Launcher();
        launcher.withMapFile("/test.txt");
        launcher.launch();
    }

    /**
     *
     */
    @Test
    public void TestClydeFarFromPacman(){
        launcher.getGame();
        Game game = launcher.getGame();
        Player player = game.getPlayers().get(0);

        // start cleanly.
        assertThat(game.isInProgress()).isFalse();
        game.start();
        assertThat(game.isInProgress()).isTrue();
        assertThat(player.getScore()).isZero();

        game.move(player, Direction.EAST); //Get points after eating a pellet
        game.move(player, Direction.EAST);
        game.move(player, Direction.EAST);
        game.move(player, Direction.EAST);
        game.move(player, Direction.EAST);
        game.move(player, Direction.EAST);
        assertEquals(player.getScore(),60);
    }

    /**
     *
     */
    @Test
    public void TestClydeCloseFromPacman(){
        assertThat(temp).isEqualTo(true);
    }

    /**
     *
     */
    @Test
    public void TestClydeWhenPacmanNull(){
        assertThat(temp).isEqualTo(true);
    }

    /**
     *
     */
    @Test
    public void TestClydeWhenGridSquarNull(){
        assertThat(temp).isEqualTo(true);
    }
}
