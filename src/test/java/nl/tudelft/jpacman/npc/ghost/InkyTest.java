package nl.tudelft.jpacman.npc.ghost;

import com.google.common.collect.Lists;
import nl.tudelft.jpacman.board.Board;
import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.LevelFactory;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.level.PlayerFactory;
import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.points.PointCalculator;
import nl.tudelft.jpacman.sprite.PacManSprites;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class InkyTest {
    private GhostMapParser ghostMapParser;
    private Player player;

    /**
     * Set up the GhostMap parser.
     */
    @BeforeEach
    void setUp() {
        PacManSprites sprites = new PacManSprites();
        GhostFactory ghostFactory = new GhostFactory(sprites);
        LevelFactory levelFactory = new LevelFactory(
            sprites,
            ghostFactory,
            mock(PointCalculator.class));
        ghostMapParser = new GhostMapParser(levelFactory, new BoardFactory(sprites), ghostFactory);
        PlayerFactory playerFactory = new PlayerFactory(sprites);
        player = playerFactory.createPacMan();
    }

    @Test
    public void TestInkyBehindBlinky() {
        Level level = ghostMapParser.parseMap(Lists.newArrayList(
            "##############",
                      "#            #",
                      "#P  B    I   #",
                      "#            #",
                      "##############"));
        Board board = level.getBoard();

        Ghost inky = Navigation.findUnitInBoard(Inky.class, board);
        Ghost blinky = Navigation.findUnitInBoard(Blinky.class, board);
        Optional direction = inky.nextAiMove();

        assertEquals(direction, Optional.of(Direction.EAST));
    }

    @Test
    public void TestInkInFrontBlinky() {
        Level level = ghostMapParser.parseMap(Lists.newArrayList(
              "##############",
                        "#            #",
                        "#P  I    B   #",
                        "#            #",
                        "##############"));
        Board board = level.getBoard();

        Ghost inky = Navigation.findUnitInBoard(Inky.class, board);
        Ghost blinky = Navigation.findUnitInBoard(Blinky.class, board);
        Optional direction = inky.nextAiMove();


        assertEquals(direction, Optional.of(Direction.EAST));
    }

    @Test
    public void TestInkyNoBlinky() {
        Level level = ghostMapParser.parseMap(Lists.newArrayList(
              "##############",
                        "#            #",
                        "#P       I   #",
                        "#            #",
                        "##############"));
        Board board = level.getBoard();

        Ghost inky = Navigation.findUnitInBoard(Inky.class, board);
        Ghost blinky = Navigation.findUnitInBoard(Blinky.class, board);
        Optional direction = inky.nextAiMove();

    }

    @Test
    public void TestInky4() {
        Level level = ghostMapParser.parseMap(Lists.newArrayList(
              "##############",
                        "#            #",
                        "#P  B    I   #",
                        "#            #",
                        "##############"));
        Board board = level.getBoard();

        Ghost inky = Navigation.findUnitInBoard(Inky.class, board);
        Ghost blinky = Navigation.findUnitInBoard(Blinky.class, board);
        Optional direction = inky.nextAiMove();


    }

    @Test
    public void TestInky5() {
        Level level = ghostMapParser.parseMap(Lists.newArrayList(
              "##############",
                        "#            #",
                        "#P  B    I   #",
                        "#            #",
                        "##############"));
        Board board = level.getBoard();

        Ghost inky = Navigation.findUnitInBoard(Inky.class, board);
        Ghost blinky = Navigation.findUnitInBoard(Blinky.class, board);
        Optional direction = inky.nextAiMove();


    }


}
