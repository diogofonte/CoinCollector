package coin_collector;

import net.jqwik.api.*;
import net.jqwik.api.constraints.Positive;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMap {
    @Provide
    Arbitrary<Integer> notZero() {
        return Arbitraries.integers().filter(n -> n != 0);
    }

    @Property
    public void testGetters(@ForAll("notZero") @Positive int h, @ForAll("notZero") @Positive int w) throws IOException {
        Map map = new Map(h,w);
        assertEquals(h, map.getHeight());
        assertEquals(w, map.getWidth());
    }

    @Property
    public void testSetters(@ForAll @Positive int h, @ForAll @Positive int w) throws IOException {
        Map map = new Map(100,100);
        map.setHeight(h);
        map.setWidth(w);
        assertEquals(h, map.getHeight());
        assertEquals(w, map.getWidth());
    }

    @Test
    public void testDraw() throws IOException {
        Map map = new Map(100,100);
        //...
    }

    @Test
    public void testProcessKey() throws IOException {
        Map map = new Map(100,100);
        //a pessoa criada no Board(width,height) tem como posição inicial (10,10)
        //left - 37 ; up - 38 ; right - 39 ; down - 40
        //KeyStroke key = getKeyStroke(38,0); //getKeyStroke(int keyCode,int modifiers)
        //map.processKey(ArrowLeft);
    }

    @Test
    public void testMovePerson() throws IOException {
        Map map = new Map(100,100);
        //a pessoa criada no Board(width,height) tem como posição inicial (20,20)
        Position pos = new Position(21,21);
        map.movePerson(pos);
        assertEquals(21, map.getPerson().getPosition().getX());
        assertEquals(21, map.getPerson().getPosition().getY());
    }
}
