package coin_collector;

import net.jqwik.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCoin {
    @Provide
    Arbitrary<Integer> notZero() {
        return Arbitraries.integers().filter(n -> n != 0);
    }

    @Property
    public void testPosition(@ForAll("notZero") int x, @ForAll("notZero") int y){
        Coin coin = new Coin(0,0);
        assertEquals(0,coin.getPosition().getX());
        assertEquals(0,coin.getPosition().getY());
        Position newP = new Position(x,y);
        coin.setPosition(newP);
        assertEquals(x,coin.getPosition().getX());
        assertEquals(y,coin.getPosition().getY());
    }
}
