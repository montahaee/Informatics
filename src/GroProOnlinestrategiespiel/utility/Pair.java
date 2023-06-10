package GroProOnlinestrategiespiel.utility;

public class Pair <X, Y>{
    private final X x;
    private final Y y;

    public Pair(X that_x, Y that_y) {
        this.x = that_x;
        this.y = that_y;
    }

    public X getX() {
        return x;
    }

    public Y getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "key=" + x +
                ", value=" + y +
                '}';
    }
}
