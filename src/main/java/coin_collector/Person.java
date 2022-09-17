package coin_collector;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Person extends Element implements MovableElement {
    private int wallet;
    private int health;

    Person(int x, int y){
        position = new Position(x,y);
        wallet = 0;
        health = 3;
    }

    public void draw(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString("#274360"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()), "p");
    }

    public int getWallet(){
        return wallet;
    }

    public void setWallet(int money){
        wallet = money;
    }

    public int getHealth(){
        return health;
    }

    public void setHealth(int h){
        health = h;
    }

    public void decreaseHealth(){
        if(health > 0){
            health--;
        }
        System.out.println("HEALTH DECREASED");
    }

    public void increaseWallet(){
        wallet++;
    }

    public Position moveUp(){
        return new Position(getPosition().getX(),getPosition().getY() - 1);
    }

    public Position moveRight(){
        return new Position(getPosition().getX() + 1,getPosition().getY());
    }

    public Position moveDown(){
        return new Position(getPosition().getX(),getPosition().getY() + 1);
    }

    public Position moveLeft(){
        return new Position(getPosition().getX() - 1,getPosition().getY());
    }
}
