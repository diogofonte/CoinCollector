package coin_collector;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Wall extends Element {
    private Position position;

    Wall(int x, int y){
        position = new Position(x,y);
    }

    public void draw(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString("#B22222"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()), "w");
    }

    public void setPosition(Position position){
        this.position.setX(position.getX());
        this.position.setY(position.getY());
    }

    public Position getPosition(){
        return position;
    }
}
