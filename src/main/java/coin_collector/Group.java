package coin_collector;

import com.googlecode.lanterna.graphics.TextGraphics;
import java.util.List;

public class Group<T extends Element> {
    private final List<T> list;

    Group(List<T> list){
        this.list = list;
    }

    public int getListSize(){
        return list.size();
    }

    public void addElement(T element){
        list.add(element);
    }

    public void removeElement(T element){
        list.remove(element);
    }

    public void draw(TextGraphics graphics){
        for(T object : list){
            object.draw(graphics);
        }
    }
}
