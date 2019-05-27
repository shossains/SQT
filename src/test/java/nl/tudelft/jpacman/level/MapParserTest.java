package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.board.BoardFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

public class MapParserTest {
    private final LevelFactory levelFactory = mock(LevelFactory.class);
    private final BoardFactory boardFactory = mock(BoardFactory.class);
    private MapParser parser;

    @BeforeEach
    void setup() {
        parser = new MapParser(levelFactory, boardFactory);

    }


}
