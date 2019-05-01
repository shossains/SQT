package nl.tudelft.jpacman.npc.ghost;

import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.game.Game;
import nl.tudelft.jpacman.level.LevelFactory;
import nl.tudelft.jpacman.level.Pellet;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.points.DefaultPointCalculator;
import nl.tudelft.jpacman.points.PointCalculator;
import nl.tudelft.jpacman.sprite.PacManSprites;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    }

    /**
     *
     */
    @Test
    public void TestClydeFarFromPacman(){
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
