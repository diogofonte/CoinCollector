package coin_collector;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestJumperGhost {
    @Test
    public void testPosition(){
        Ghost ghost = new JumperGhost(10,10);
        assertEquals(10,ghost.getPosition().getX());
        assertEquals(10,ghost.getPosition().getY());
        Position newP = new Position(20,20);
        ghost.setPosition(newP);
        assertEquals(20,ghost.getPosition().getX());
        assertEquals(20,ghost.getPosition().getY());
    }

    @Test
    public void testMove(){
        Ghost ghost = new JumperGhost(10,10);
        Position newP = ghost.move();
        int x = newP.getX();
        int y = newP.getY();
        assertTrue(x >= 8 && x <= 12 && y >= 8 && y <= 12);
        boolean one = x == 8 || x == 10 || x == 12;
        boolean two = y == 8 || y == 10 || y == 12;
        assertTrue(one && two);
    }

    @Test
    public void testGenerateNumber(){
        Ghost ghost = new JumperGhost(10,10);
        int n = ghost.generateNumber();
        assertTrue(n >= 0 && n <= 7);
    }
}
