package OpticalAutocorrelation.source;

public class Pair <Key, Value>{

    private final Key source;
    private final Value content;


    public Pair (Key key, Value value) {
        this.source = key;
        this.content = value;
    }

    public Key getSource() {
        return source;
    }

    public Value getContent() {
        return content;
    }

    public boolean isEmpty() {
        return content == null;
    }

}
