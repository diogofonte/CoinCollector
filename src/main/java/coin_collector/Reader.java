package coin_collector;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reader<T extends Element> {
    private char finding;

    Reader(char c){
        finding = c;
    }

    public List<T> readPositions() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileReader("src/main/resources/map/map.txt")).useDelimiter("\\n");
        List<T> list = new ArrayList<>();
        int i = 0;
        while(scanner.hasNext()){
            String line = scanner.next();
            for(int j = 0 ; j < line.length() ; j++){
                if(line.charAt(j) == finding){
                    T t = decideElement(j,i);
                    list.add(t);
                }
            }
            i++;
        }
        return list;
    }

    private T decideElement(int x, int y){
        if(finding == '#'){
            return (T) new Wall(x,y);
        } else if(finding == '.'){
            return (T) new Coin(x,y);
        } else if(finding == 'F'){
            return (T) new FatalGhost(x,y);
        } else if(finding == 'J'){
            return (T) new JumperGhost(x,y);
        }
        return null;
    }
}

