package spypunk.tetris.model;
 

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class TTest {

    @Test
    public void testLucky() {
        assertEquals(7, T.getLucky());
    }

}