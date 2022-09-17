package coin_collector;

import net.jqwik.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPosition {
    @Property
    public void testGetters(@ForAll int x, @ForAll int y){
        Position position = new Position(x,y);
        assertEquals(x,position.getX());
        assertEquals(y,position.getY());
    }

    @Provide
    Arbitrary<Integer> notZero() {
        return Arbitraries.integers().filter(n -> n != 0);
    }

    @Property
    public void testSetters(@ForAll("notZero") int x, @ForAll("notZero") int y){
        Position position = new Position(0,0);
        position.setX(x);
        position.setY(y);
        assertEquals(x,position.getX());
        assertEquals(y,position.getY());
    }

    @Property
    public void testEquals(@ForAll int x, @ForAll int y){
        Position p1 = new Position(x,y);
        Position p2 = new Position(x,y);
        assertTrue(p1.equals(p2));
    }
}
