package process;

import cutting.utility.Truncation;

public class Pipe {
    private final double length;

    public Pipe(double length) {
        if (length <= 0 ) { //TODO add your exception LengthException
            throw new  IllegalArgumentException("The length of the pipe negative!");
        }
        this.length = length;
    }

    public Pipe(Pipe other) {
        this.length = other.getLength();
    }

    public double getLength() {
        return this.length;
    }

    @Override
    public String toString() {
        return Truncation.form("",false,this.length);
    }

///    @Override
///    public boolean equals(Object that) {
//        if (this == that) {
//            return true;
//        } else if (that == null || (this.getClass() != that.getClass())) {
//            return false;
//        } else {
//            return this.length == ((Pipe) that).length;
//        }
//    }

    //    void shopping(Order order, Cutting2 cutting);
}
