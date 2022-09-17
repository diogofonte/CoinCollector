package coin_collector;

import java.util.Random;

public abstract class Ghost extends Element implements IndependentMovableElement {
    int cases;

    Ghost(int x, int y, int n){
        position = new Position(x,y);
        cases = n;
    }

    public Position move(){
        int number = generateNumber();
        switch (number) {
            case 0 -> {
                return new Position(position.getX() - cases,position.getY() - cases);
            }
            case 1 -> {
                return new Position(position.getX(),position.getY() - cases);
            }
            case 2 -> {
                return new Position(position.getX() + cases,position.getY() - cases);
            }
            case 3 -> {
                return new Position(position.getX() - cases,position.getY());
            }
            case 4 -> {
                return new Position(position.getX() + cases,position.getY());
            }
            case 5 -> {
                return new Position(position.getX() - cases,position.getY() + cases);
            }
            case 6 -> {
                return new Position(position.getX(),position.getY() + cases);
            }
            case 7 -> {
                return new Position(position.getX() + cases,position.getY() + cases);
            }
        }
        return null;
    }

    public int generateNumber(){
        Random random = new Random();
        return random.nextInt(7);
    }
}
