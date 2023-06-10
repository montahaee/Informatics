package RNG.rng;//package RNG.rng;
//
//
//import RNG.validator.Validation;
//
///**
// *
// * @author Montahaee
// * @version 1.0
// * @created 08-Oct-2022 5:38:29 PM
// */
////public class RandomSequenceWrapper implements RandomSequence {
//public class RandomSequenceWrapper implements RandomSequence {
//
//	/**
//	 * Define wrapping interface for all concrete decorators
//	 */
//	private final RandomSequence wrapper;
////	int , int modulus, int increment, int seed, int length
//
//	private final int seed;
//	private final int multiplier;
//	private final int increment;
//	private final int modulus;
//	private final int order;
//	private final int[] sequence;
//
//	/**
//	 * @param that_multiplier as linear factor to LCG as integer constant.
//	 * @param that_modulus    in the LCG as integer constant.
//	 * @param that_increment  element in the LCG as integer constant.
//	 * @param that_seed       as start value in the LCG.
//	 * @param that_order      refers to the size of the generated random numbers.
//	 * @param wrapper to instantiate the new concrete
//	 */
//	public RandomSequenceWrapper(RandomSequence wrapper, int that_seed, int that_multiplier,
//								 int that_increment, int that_order, int that_modulus) {
////		boolean isLCG = true;
////		isLCG
//		if (!((0 <= that_multiplier && that_multiplier < that_modulus) &&
//			  (0 <= that_increment  && that_increment  < that_modulus) &&
//			  (0 <= that_seed       && that_seed       < that_modulus))) {
//			throw new IllegalArgumentException("The input parameter to construct a LCG cannot be fulfilled " +
//					"the obligated condition for a LCG!");
//		}
//		if (!(0 < that_order)) {
//			throw new IllegalArgumentException("The order of a sequence is greater than or equal to one!");
//		}
//		this.seed = that_seed;
//		this.multiplier = that_multiplier;
//		this.increment = that_increment;
//		this.modulus = that_modulus;
//		this.order = that_order;
//		this.sequence =
//		this.wrapper = wrapper;
//	}
//
//
//	/**
//	 * @param rv is abbreviation of random variable
//	 */
//	@Override
//	public double cdf(double rv) {
//		return wrapper.cdf(rv);
//	}
//
//	/**
//	 * @param rvs is abbreviation of random variables.
//	 * @return excepted value for random numbers.
//	 */
//	@Override
//	public double exv(int[] rvs) {
//		return wrapper.exv(rvs);
//	}
//
//
//	/**
//	 * @param rv is abbreviation of random variable.
//	 * @return probability density function.
//	 */
//	@Override
//	public double pdf(double rv) {
//		return wrapper.mean(rv);
//	}
//
//	/**
//	 * @param rv is abbreviation of random variable.
//	 * @return probability mass function associated with discrete random variables generated by LCG.
//	 */
//	@Override
//	public double pmf(int rv) {
//		return wrapper.probability(rv);
//	}
//
//	/**
//	 *
//	 * @return sequential integer random numbers using LCG algorithm.
//	 */
//	@Override
//	public int[] randomVariable() {
//		return wrapper.randomVariable(this);
//	}
//
//	/**
//	 * @param rvs refers to random variables.
//	 * @return the range of the cdf() function.
//	 */
//	@Override
//	public Interval range(int[] rvs) {
//		return null;
//	}
//
//	/**
//	 * @param random_numbers are generated by LCG.
//	 * @return true/false according to result of{@link #<Validation>}.
//	 */
//	@Override
//	public boolean validated(int[] random_numbers) {
//		return false;
//	}
//
//	/**
//	 * @param min random variable, which generated by LCG.
//	 * @param max random variable, which generated by LCG.
//	 * @return variance for a continues uniform distribution with [min, max]
//	 */
//	@Override
//	public double var(double min, double max) {
//		return 0;
//	}
//}//end RandomSequenceWrapper