package RNG.rng;

import RNG.validator.Validation;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.List;
import java.util.StringJoiner;

/**
 * This class makes a basis linear congruential Generator(LCG) how
 * its generated pseudo numbers build a uniform distribution.
 * This because there are in the table 2 of the task sheet the
 * increments either equal with zero(c= 0) or equal wit seed(x0 = c).
 * <p>
 *  Many input parameter/Attributes like modulus(m) or multipler(a) are
 *  Biginteger. You can use BigDecimal to more precision during implementation
 *  in the following methode but there is a restriction for that.
 *  In other words we can because of the freedom of Number extension choose in
 * @see RandomSequence as output type of many methodes BigDecimal.
 * But this cannot 100 % to prevent a float perecision of the result of
 * some methode as I tested. Make this simple test under x64 system:
 * BigInteger myBI = new BigInteger("100000000000000");
 * BigInteger nd = new BigInteger("1000000000000000000000");
 * BigDecimal d_myBI= new BigDecimal(myBI);
 * BigDecimal dbi_myBI = d_myBI.divide(new BigDecimal(nd), MathContext.UNLIMITED);
 * The result will be dbi_myBI = 1E-7.
 * <p>
 *
 *
 *
 * @author Montahaee
 * @version 1.0
 * @created 11-Oct-2022 5:38:29 PM
 */
public abstract class LCG implements RandomSequence<BigInteger> {

    protected BigInteger seed;
    protected BigInteger multiplier;
    protected BigInteger increment;
    protected BigInteger modulus;
    private List<BigInteger> sequence;

    /**
     * default/regular constructor
     */
    public LCG() {
//        this.sequence = new ArrayList<>();
    }

    /**
     *
     * @param lcg is a fresh object(LCG type) initialized by attributes
     *           of existing object.
     */
    public LCG(LCG lcg) {
        this();
//        if (!((0 <= lcg.multiplier && lcg.multiplier < lcg.modulus) &&
        if (!((0 <= lcg.multiplier.compareTo(BigInteger.ZERO) && lcg.multiplier.compareTo(lcg.modulus) <0 ) &&
              (0 <= lcg.increment.compareTo(BigInteger.ZERO)  && lcg.increment.compareTo( lcg.modulus) < 0) &&
              (0 <= lcg.seed.compareTo(BigInteger.ZERO)       && lcg.seed.compareTo(lcg.modulus) < 0 ))) {
            throw new IllegalArgumentException("The input parameter to construct a LCG cannot be fulfilled " +
                    "the obligated condition for a LCG!");
        }
        if (lcg.sequence.isEmpty()) {
            throw new IllegalArgumentException("The order of a sequence is greater than or equal to one!");
        }
        this.seed = lcg.seed;
        this.multiplier = lcg.multiplier;
        this.increment = lcg.increment;
        this.modulus = lcg.modulus;
        this.sequence = lcg.sequence;
    }

    /**
     *
     * @return The clone operation returns one of the LCG types(subclasses).
     */
    public abstract LCG clone();

    /**
     * @param x is a real number.
     * @return cumulative distribution function of
     * the discrete uniform distribution(LCG distribution).
     */
    @Override
    public double cdf(double x) {
//        if (x < this.min()) {
        if (0 < new BigDecimal(this.min()).compareTo(BigDecimal.valueOf(x))) {
            return 0.0;
        }
        if (this.range().contains(x)) {
//            return ( BigDecimal.valueOf ((int) x). - this.min() + 1.0) / (this.max() - this.min() + 1.0);
            return (BigDecimal.valueOf ((int) x + 1.0).subtract(new BigDecimal( this.min()))).divide (new BigDecimal(
                this.max().subtract( this.min())).add(BigDecimal.valueOf(1.0)), MathContext.DECIMAL128).doubleValue();
        }
        return 1.0;
    }

    /**
     * @return excepted value for random numbers associated
     * with discrete random variables(LCG random variables).
     */
    @Override
    public double exv() {
        BigDecimal sum = BigDecimal.ZERO;
        for (BigInteger rv : sequence) {
///            sum += rv.multiply((BigInteger) this.probability());
//            sum = sum.add(new BigDecimal(rv).multiply(BigDecimal.valueOf(this.probability())));
            sum = sum.add(new BigDecimal(rv));
        }
        return sum.multiply(BigDecimal.valueOf(this.probability())).doubleValue();
    }


    /**
     * @return mean value for a discrete uniform distribution.
     */
    @Override
    public double mean() {
        return new BigDecimal(this.min().add(this.max())).divide(BigDecimal.valueOf(2),
                                                MathContext.DECIMAL128).doubleValue();
    }

    /**
     * @return probability mass function associated for discrete uniform distribution.
     */
    @Override
    public double probability() {
        BigDecimal length = new BigDecimal(this.max().subtract(this.min())).add(BigDecimal.valueOf(1));
        return BigDecimal.valueOf(1).divide(length, MathContext.DECIMAL128).doubleValue();
    }

    /**
     *
     * @return  true if the number of sequential integer random variables by LCG more than 2.
     */
    @Override
    public boolean randomVariable() {
        sequence.add(this.seed);
//        int next = 0;
        do {
//          sequence.add(next);
//            sequence.add(multiplier*sequence.get(sequence.size() - 1) + increment % modulus);
            sequence.add(multiplier.multiply(sequence.get(sequence.size() - 1)).add(increment).mod(modulus));
        } while (!sequence.get(sequence.size() - 1).equals(this.seed));
        return (sequence.size() > 2);
    }

    /**
     * @return the range of the cdf() function.
     */
    @Override
    public Interval range() {
        return new Interval(this.min().doubleValue(), this.max().doubleValue());
    }

    /**
//     * @param random_numbers are generated by LCG.
     * @return true/false according to result of{@link #<Validation>}.
     */
    @Override
    public boolean validated() {
        return false;
    }

    /**
     * @return variance for a discrete uniform distribution with [min, max]
     */
    @Override
    public double var() {
        BigDecimal var = new BigDecimal((this.max().subtract(this.min())).pow(2).subtract(BigInteger.ONE));
        return var.divide(BigDecimal.valueOf(12), MathContext.DECIMAL128).doubleValue(); // value of convert it to float point precision.
    }

    /**
     *
     * @return all needed parameters to build a random generator.
     */
    @Override
    public BigInteger[] getParams(){
        return new BigInteger[]{this.seed, this.multiplier,
                this.increment, this.multiplier, this.modulus};
    }

    /**
     *
     * @return string representation of all numbers by LCG.
     */
    @Override
    public String toString() {
        return new StringJoiner(", ", LCG.class.getSimpleName() + "[", "]")
                .add("sequence=" + sequence)
                .toString();
    }

    /**
     *
     * @return the minimum random number generated by LCG.
     */
    private BigInteger min () {
        return this.sequence.stream().min(BigInteger::compareTo).get();
    }

    /**
     *
     * @return the maximum random number generated by LCG.
     */
    private BigInteger max () {
        return this.sequence.stream().max(BigInteger::compareTo).get();
    }

    public BigInteger next() {
        return this.seed;
    }

}
