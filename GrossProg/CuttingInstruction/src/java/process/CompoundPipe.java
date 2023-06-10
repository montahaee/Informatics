package process;

import cutting.utility.MyMath;
import cutting.utility.Truncation;
import org.jetbrains.annotations.NotNull;

import java.util.*;

//public class CompoundPipe implements Comparable<CompoundPipe> {
public class CompoundPipe extends Pipe {

//    private final double length;
    List<Pipe> cuttings;
    private double remaining;

    public CompoundPipe(double length) {
        super(length);
        if (length <= 0 ) { //TODO add your exception LengthException
            throw new  IllegalArgumentException("The length of the pipe negative!");
        }
//        this.length = length;
        this.cuttings = new ArrayList<>();
        this.remaining = MyMath.round(length);
    }

    public CompoundPipe(CompoundPipe that) {
        super(that);
//        this.length = that.getLength();
        this.cuttings = new ArrayList<>();
        this.remaining =  that.getRemaining();
//        this.remaining =  getLength();
        that.getCuttings().forEach(this::addCutout);
    }

//    public double getLength() {
//        return this.length;
//    }

    public List<Pipe> getCuttings() {
        return this.cuttings;
    }

    public double getRemaining() {

        return MyMath.round(this.remaining);
    }

    public void addCutout(double length) {
//        double offcut = this.remaining;
//        offcut -= length;
//        offcut = MyMath.round(offcut);
//        if (offcut < 0) {
//            return;
//        }
//        cuttings.add(new Pipe(length));
//        remaining = offcut;

//        setRemaining(Offcut());

        addCutout(new Pipe(length));
    }
    public void addCutout(@NotNull Pipe pipe) {
        if (pipe.getLength() > this.remaining ) {
            //TODO define self exception. But this cannot happen because the constructor of the pipe
            return;
        }
        this.remaining -= pipe.getLength();
        cuttings.add(pipe);
//        setRemaining(Offcut());
    }

    public void setRemaining(double remaining){
        this.remaining = remaining;
    }

    @Override
    public String toString() {
        String print = Truncation.form("",false, getLength());
        print += Truncation.form(" -> ","s",false, printCutouts());
        print += Truncation.form(" Offcut: ",false, remaining);
        return print;
    }

    private String printCutouts() {
        if (cuttings.isEmpty()) {
            return "";
        } else {
            StringBuilder sb = new StringBuilder();
            cuttings.forEach(c -> sb.append(Truncation.form("",false, c.getLength())).append("; "));
            sb.delete(sb.length() - 2, sb.length());
            return sb.toString();
        }
    }

    public int cuttingCount() {
        if (remaining > 0.0) {
            return cuttings.size();
        } else { //remaining == 0
            return cuttings.size() - 1;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other == null || (this.getClass() != other.getClass())) {
            return false;
        } else {
            CompoundPipe that = (CompoundPipe) other;
            boolean equal = (this.getLength() == that.getLength());
            equal &= (this.remaining == that.getRemaining());
            equal &= (this.cuttings.size() == that.getCuttings().size());
            for (int i = 0; (i < this.cuttings.size() && equal); i++) {
                equal = (this.cuttings.get(i).equals(that.getCuttings().get(i)));
            }
            return equal;
        }
    }

    public double Offcut(){ //TODO according to the CompoundPipe Test the output of two methods in the getRemaining() and these are the same. So one of them has to be removed.
        if (cuttings.isEmpty()) {
            return this.getLength();
        } else {
            double sumCut = cuttings.stream().mapToDouble(Pipe::getLength).sum();
            return MyMath.round(this.getLength() - sumCut);
        }
    }

    public void sortCuttings() {
//        this.cuttings.sort((c1, c2) -> (c1 > c2)? -1: (c1 < c2)? 1 : 0);
        this.cuttings.sort(Comparator.comparingDouble(Pipe::getLength).reversed());
    }




//
//    /**
//     * Compares this object with the specified object for order.  Returns a
//     * negative integer, zero, or a positive integer as this object is less
//     * than, equal to, or greater than the specified object.
//     *
//     * <p>The implementor must ensure {@link Integer#signum
//     * signum}{@code (x.compareTo(y)) == -signum(y.compareTo(x))} for
//     * all {@code x} and {@code y}.  (This implies that {@code
//     * x.compareTo(y)} must throw an exception if and only if {@code
//     * y.compareTo(x)} throws an exception.)
//     *
//     * <p>The implementor must also ensure that the relation is transitive:
//     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
//     * {@code x.compareTo(z) > 0}.
//     *
//     * <p>Finally, the implementor must ensure that {@code
//     * x.compareTo(y)==0} implies that {@code signum(x.compareTo(z))
//     * == signum(y.compareTo(z))}, for all {@code z}.
//     *
//     * @param p the object pipe to be compared.
//     * @return a negative integer, zero, or a positive integer as this object
//     * is less than, equal to, or greater than the specified object.
//     * @throws NullPointerException if the specified object is null
//     * @throws ClassCastException   if the specified object's type prevents it
//     *                              from being compared to this object.
//     * @apiNote It is strongly recommended, but <i>not</i> strictly required that
//     * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
//     * class that implements the {@code Comparable} interface and violates
//     * this condition should clearly indicate this fact.  The recommended
//     * language is "Note: this class has a natural ordering that is
//     * inconsistent with equals."
//     */
//    @Override
//    public int compareTo(CompoundPipe p) {
//        return (this.length < p.getLength())? 0 : 1;
//    }
}
