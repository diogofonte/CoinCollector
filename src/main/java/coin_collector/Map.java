package coin_collector;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import java.io.IOException;
import java.util.*;

public class Map {
    private int width;
    private int height;
    private final Person person;
    private final List<Wall> maze;
    private final List<FatalGhost> fatalGhosts;
    private final List<JumperGhost> jumperGhosts;
    private final List<Coin> coins;

    Map(int height, int width) throws IOException {
        this.width = width;
        this.height = height;
        person = new Person(20,20);
        Reader<Wall> readWalls = new Reader<>('#');
        maze = readWalls.readPositions();
        Reader<Coin> readCoins = new Reader<>('.');
        coins = readCoins.readPositions();
        Reader<FatalGhost> readFGhosts = new Reader<>('F');
        fatalGhosts = readFGhosts.readPositions();
        Reader<JumperGhost> readJGhosts = new Reader<>('J');
        jumperGhosts = readJGhosts.readPositions();
    }

    public void setWidth(int width){
        this.width = width;
    }
    public void setHeight(int height){
        this.height = height;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public Person getPerson(){
        return person;
    }

    public void draw(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#D5B59C"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        person.draw(graphics);
        Group<Wall> groupWall = new Group(maze);
        groupWall.draw(graphics);
        Group<FatalGhost> groupFGhost = new Group(fatalGhosts);
        groupFGhost.draw(graphics);
        Group<JumperGhost> groupJGhost = new Group(jumperGhosts);
        groupJGhost.draw(graphics);
        Group<Coin> groupCoin = new Group(coins);
        groupCoin.draw(graphics);
    }

    public void processKey(KeyStroke key) throws IOException {
        moveGhosts();
        verifyGhostsCollisions();
        switch (key.getKeyType()) {
            case ArrowLeft -> movePerson(person.moveLeft());
            case ArrowRight -> movePerson(person.moveRight());
            case ArrowUp -> movePerson(person.moveUp());
            case ArrowDown -> movePerson(person.moveDown());
        }
    }

    public void moveGhosts(){
        for(FatalGhost g : fatalGhosts){
            moveGhost(g);
        }
        for(JumperGhost g : jumperGhosts){
            moveGhost(g);
        }
    }

    private void moveGhost(Ghost g) {
        Position pos = g.move();
        while(!canElementMove(pos)){
            pos = g.move();
        }
        g.setPosition(pos);
    }

    public void movePerson(Position position) throws IOException {
        if(canElementMove(position)){
            person.setPosition(position);
            verifyGhostsCollisions();
            collectCoins();
        }
    }

    private boolean canElementMove(Position position){
        if(position.getX() >= 0 && position.getX() < width){
            if(position.getY() >= 0 && position.getY() < height){
                for(Wall wall : maze){
                    if(wall.getPosition().equals(position)){
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    private void verifyGhostsCollisions() throws IOException {
        for(FatalGhost g : fatalGhosts){
            if(g.getPosition().equals(person.getPosition())){
                lost();
            }
        }
        for(JumperGhost g : jumperGhosts){
            if(g.getPosition().equals(person.getPosition())){
                person.decreaseHealth();
                person.setWallet(0);
                if(person.getHealth() == 0){
                    lost();
                }
            }
        }
    }

    private void collectCoins() throws IOException {
        //para não ocorrer java.util.ConcurrentModificationException
        for(int i = 0 ; i < coins.size() ; i++){
            Coin c = coins.get(i);
            if(c.getPosition().equals(person.getPosition())){
                person.increaseWallet();
                coins.remove(c);
            }
            if(coins.isEmpty()){
                won();
            }
        }
    }

    private void won() throws IOException {
        System.out.println("WON");
        Game.getScreen().close();
    }

    private void lost() throws IOException {
        System.out.println("GAME OVER");
        Game.getScreen().close();
    }
}
