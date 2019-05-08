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

    /**
     * Good weather case
     * Test when Inky is behind Blinky followed by Pacman
     */
    @Test
    public void TestInkyBehindBlinky() {
        Level level = ghostMapParser.parseMap(Lists.newArrayList(
            "##############",
                      "#            #",
                      "#P  B    I   #",
                      "#            #",
                      "##############"));
        Board board = level.getBoard();
        level.registerPlayer(player);

        Ghost inky = Navigation.findUnitInBoard(Inky.class, board);
        Ghost blinky = Navigation.findUnitInBoard(Blinky.class, board);
        Optional direction = inky.nextAiMove();

        assertEquals(direction, Optional.of(Direction.WEST));
    }

    /**
     * Good weather case
     * Test when Inky is in front of Blinky, but behind Pacman
     */
    @Test
    public void TestInkInFrontBlinky() {
        Level level = ghostMapParser.parseMap(Lists.newArrayList(
              "##############",
                        "#            #",
                        "#P  I    B   #",
                        "#            #",
                        "##############"));
        Board board = level.getBoard();
        level.registerPlayer(player);

        Ghost inky = Navigation.findUnitInBoard(Inky.class, board);
        Ghost blinky = Navigation.findUnitInBoard(Blinky.class, board);
        player.setDirection(Direction.WEST);
        Optional direction = inky.nextAiMove();


        assertEquals(direction, Optional.of(Direction.WEST));
    }

    /**
     * Bad weather case
     * Test what happens if there is no blinky
     */
    @Test
    public void TestInkyNoBlinky() {
        Level level = ghostMapParser.parseMap(Lists.newArrayList(
              "##############",
                        "#            #",
                        "#  I    P    #",
                        "#            #",
                        "##############"));
        Board board = level.getBoard();
        level.registerPlayer(player);

        Ghost inky = Navigation.findUnitInBoard(Inky.class, board);
        Ghost blinky = Navigation.findUnitInBoard(Blinky.class, board);
        player.setDirection(Direction.EAST);
        Optional direction = inky.nextAiMove();

        assertEquals(direction, Optional.empty());
    }

    /**
     * Bad weather case
     * Test what happens when Blinky is boxed in
     */
    @Test
    public void TestEncapsulatedBlinky() {
        Level level = ghostMapParser.parseMap(Lists.newArrayList(
              "##############",
                        "#   #        #",
                        "#P #B#   I   #",
                        "#   #        #",
                        "##############"));
        Board board = level.getBoard();
        level.registerPlayer(player);

        Ghost inky = Navigation.findUnitInBoard(Inky.class, board);
        Ghost blinky = Navigation.findUnitInBoard(Blinky.class, board);
        Optional direction = inky.nextAiMove();

        assertEquals(direction, Optional.empty());
    }

    /**
     * Good weather case
     * Test when Pacman is between Inky and Blinky
     */
    @Test
    public void TestPacmanInBetween() {
        Level level = ghostMapParser.parseMap(Lists.newArrayList(
              "##############",
                        "#            #",
                        "# I  P   B   #",
                        "#            #",
                        "##############"));
        Board board = level.getBoard();
        level.registerPlayer(player);

        Ghost inky = Navigation.findUnitInBoard(Inky.class, board);
        Ghost blinky = Navigation.findUnitInBoard(Blinky.class, board);
        Optional direction = inky.nextAiMove();

        assertEquals(direction, Optional.of(Direction.EAST));
    }
    /**
     * Bad weather case
     * Test when there is no Pacman (never registered)
     */
    @Test
    public void TestNoPacman() {
        Level level = ghostMapParser.parseMap(Lists.newArrayList(
            "##############",
            "#            #",
            "# I   P   B  #",
            "#            #",
            "##############"));
        Board board = level.getBoard();

        Ghost inky = Navigation.findUnitInBoard(Inky.class, board);
        Ghost blinky = Navigation.findUnitInBoard(Blinky.class, board);
        Optional direction = inky.nextAiMove();

        assertEquals(direction, Optional.empty());
    }

}
