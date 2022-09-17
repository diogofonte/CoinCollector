package coin_collector;

import net.jqwik.api.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGroup {
    @Provide
    Arbitrary<Integer> notZero() {
        return Arbitraries.integers().filter(n -> n != 0);
    }

    @Property
    public void testGetListSize(@ForAll int x, @ForAll int y){
        List<Wall> w = new ArrayList<>();
        coin_collector.Group<Wall> group = new coin_collector.Group<>(w);
        assertEquals(0, group.getListSize());
        group.addElement(new Wall(x,y));
        assertEquals(1, group.getListSize());
    }

    @Test
    public void testAddElement(){
        List<Wall> w = new ArrayList<>();
        coin_collector.Group<Wall> group = new coin_collector.Group<>(w);
        for(int i = 0 ; i < 10 ; i++){
            group.addElement(new Wall(i,i+1));
        }
        assertEquals(10, group.getListSize());
    }

    @Test
    public void testRemoveElement(){
        List<Wall> w = new ArrayList<>();
        Group<Wall> group = new coin_collector.Group<>(w);
        assertEquals(0, group.getListSize());
        Wall wall = new Wall(1,2);
        group.addElement(wall);
        assertEquals(1, group.getListSize());
        group.removeElement(wall);
        assertEquals(0, group.getListSize());
    }

    @Test
    public void testDraw(){
        //...
    }
}
