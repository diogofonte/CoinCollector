package coin_collector;

import net.jqwik.api.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPerson {
    @Provide
    Arbitrary<Integer> notZero() {
        return Arbitraries.integers().filter(n -> n != 0);
    }

    @Property
    public void testPosition(@ForAll("notZero") int x, @ForAll("notZero") int y){
        Person person = new Person(0,0);
        assertEquals(0,person.getPosition().getX());
        assertEquals(0,person.getPosition().getY());
        Position newP = new Position(x,y);
        person.setPosition(newP);
        assertEquals(x,person.getPosition().getX());
        assertEquals(y,person.getPosition().getY());
    }

    @Test
    public void testMoveUp(){
        Person person = new Person(10,10);
        person.setPosition(person.moveUp());
        assertEquals(10,person.getPosition().getX());
        assertEquals(9,person.getPosition().getY());
    }

    @Test
    public void testMoveRight(){
        Person person = new Person(10,10);
        person.setPosition(person.moveRight());
        assertEquals(11,person.getPosition().getX());
        assertEquals(10,person.getPosition().getY());
    }

    @Test
    public void testMoveDown(){
        Person person = new Person(10,10);
        person.setPosition(person.moveDown());
        assertEquals(10,person.getPosition().getX());
        assertEquals(11,person.getPosition().getY());
    }

    @Test
    public void testMoveLeft(){
        Person person = new Person(10,10);
        person.setPosition(person.moveLeft());
        assertEquals(9,person.getPosition().getX());
        assertEquals(10,person.getPosition().getY());
    }

    @Test
    public void testWallet(){
        Person person = new Person(10,10);
        assertEquals(0,person.getWallet());
        person.increaseWallet();
        assertEquals(1,person.getWallet());
        person.setWallet(10);
        assertEquals(10,person.getWallet());
    }

    @Test
    public void testHealth(){
        Person person = new Person(10,10);
        assertEquals(3,person.getHealth());
        person.decreaseHealth();
        assertEquals(2,person.getHealth());
        person.setHealth(10);
        assertEquals(10,person.getHealth());
    }
}