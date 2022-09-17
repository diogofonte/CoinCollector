package coin_collector;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestFatalGhost {
    @Test
    public void testPosition(){
        Ghost ghost = new FatalGhost(10,10);
        assertEquals(10,ghost.getPosition().getX());
        assertEquals(10,ghost.getPosition().getY());
        Position newP = new Position(20,20);
        ghost.setPosition(newP);
        assertEquals(20,ghost.getPosition().getX());
        assertEquals(20,ghost.getPosition().getY());
    }

    @Test
    public void testMove(){
        Ghost ghost = new FatalGhost(10,10);
        Position newP = ghost.move();
        int x = newP.getX();
        int y = newP.getY();
        assertTrue(x >= 9 && x <= 11 && y >= 9 && y <= 11);
    }

    @Test
    public void testGenerateNumber(){
        Ghost ghost = new FatalGhost(10,10);
        int n = ghost.generateNumber();
        assertTrue(n >= 0 && n <= 7);
    }
}
