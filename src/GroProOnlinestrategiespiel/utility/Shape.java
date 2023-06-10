package GroProOnlinestrategiespiel.utility;


import java.util.ArrayList;
import java.util.List;

public class Shape<S extends Number,T> {

    private final List<S> contents;
    private final List<T> positions;

    public Shape() {
        contents = new ArrayList<>();
        positions = new ArrayList<>();
    }

    public Shape(List<S> that_contents) {

        this(that_contents,new ArrayList<>());
    }

    public Shape(List<S> that_contents, List<T> that_positions) {
//        if (that_contents.isEmpty()) {
//            throw new IllegalArgumentException("The filed is empty");
//        }
//        if (that_positions.isEmpty()) {
//            throw new IllegalArgumentException("Nothing is in the field located.");
//        }

        this.contents = new ArrayList<>();
        contents.addAll(that_contents);
        this.positions = new ArrayList<>();
        this.positions.addAll(that_positions);
    }



    public List<S> getContents() {
        return this.contents;
    }

    public List<T> getPositions() {
        return this.positions;
    }


    public S cumulative_sum(Arithmetic<S> arithmetic) {
        S sum = arithmetic.none();
        for (S content : contents) {
            sum = arithmetic.add(sum, content);
        }
        return sum;
    }

    @Override
    public String toString() {
        return "Shape{" +
                "contents=" + contents +
                ", positions=" + positions +
                '}';
    }
}
