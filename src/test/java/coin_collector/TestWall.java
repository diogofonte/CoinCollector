package coin_collector;

import net.jqwik.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestWall {
    @Provide
    Arbitrary<Integer> notZero() {
        return Arbitraries.integers().filter(n -> n != 0);
    }

    @Property
    public void testPosition(@ForAll("notZero") int x, @ForAll("notZero") int y){
        Wall wall = new Wall(0,0);
        assertEquals(0,wall.getPosition().getX());
        assertEquals(0,wall.getPosition().getY());
        Position newP = new Position(x,y);
        wall.setPosition(newP);
        assertEquals(x,wall.getPosition().getX());
        assertEquals(y,wall.getPosition().getY());
    }
}
