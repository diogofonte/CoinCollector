package coin_collector;

import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestReader {
    @Test
    public void testReadWalls() throws FileNotFoundException {
        List<Wall> list = new ArrayList<>();
        assertEquals(0,list.size());
        Reader<Wall> reader = new Reader<>('#');
        list = reader.readPositions();
        assertTrue(list.size() > 0);
    }

    @Test
    public void testReadCoins() throws FileNotFoundException {
        List<Coin> list = new ArrayList<>();
        assertEquals(0,list.size());
        Reader<Coin> reader = new Reader<>('.');
        list = reader.readPositions();
        assertTrue(list.size() > 0);
    }

    @Test
    public void testReadFatalGhosts() throws FileNotFoundException {
        List<FatalGhost> list = new ArrayList<>();
        assertEquals(0,list.size());
        Reader<FatalGhost> reader = new Reader<>('F');
        list = reader.readPositions();
        assertTrue(list.size() > 0);
    }

    @Test
    public void testReadJumperGhosts() throws FileNotFoundException {
        List<JumperGhost> list = new ArrayList<>();
        assertEquals(0,list.size());
        Reader<JumperGhost> reader = new Reader<>('#');
        list = reader.readPositions();
        assertTrue(list.size() > 0);
    }
}
