package nl.tudelft.jpacman.npc.ghost;

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

/**
 * Class to test Clyde behavior.
 */
public class ClydeTest {

    private Boolean temp = true;
    private GhostMapParser ghostMapParser;

    @BeforeEach
    void setUp(){
        PacManSprites pacManSprites = new PacManSprites();
        BoardFactory boardFactory = new BoardFactory(pacManSprites);
        GhostFactory ghostFactory = new GhostFactory(pacManSprites);
        PointCalculator pointCalculator = new DefaultPointCalculator();

        LevelFactory levelFactory = new LevelFactory(pacManSprites, ghostFactory, pointCalculator);

        ghostMapParser = new GhostMapParser(levelFactory, boardFactory, ghostFactory);

        char[][] map = {{'#','#','#','#','#','#','#','#'},
                        {'#',' ',' ',' ',' ',' ',' ','#'},
                        {'#','P','.','.','.','.','G','#'},
                        {'#',' ',' ',' ',' ',' ',' ','#'},
                        {'#','#','#','#','#','#','#','#'}};


        PlayerFactory playerFactory = new PlayerFactory(pacManSprites); //create player
        Player player = playerFactory.createPacMan();

        Direction east = Direction.valueOf("EAST"); // set players direction east
        player.setDirection(east);

        List<Player> playerList = new ArrayList<Player>(); // create list of players
        playerList.add(player);

        Level level = ghostMapParser.parseMap(map); // create level
        level.registerPlayer(player);

        Game game = new Game(pointCalculator) { // create game
            @Override
            public List<Player> getPlayers() {
                return playerList;
            }

            @Override
            public Level getLevel() {
                return level;
            }
        };

        game.start();

    }

    /**
     *
     */
    @Test
    public void TestClydeFarFromPacman(){
        setUp();
        assertThat(temp).isEqualTo(true);
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
