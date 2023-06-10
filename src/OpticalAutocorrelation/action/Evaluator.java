package OpticalAutocorrelation.action;

import OpticalAutocorrelation.receiver.*;
import OpticalAutocorrelation.source.InputSource;
import OpticalAutocorrelation.source.TransactionSource;
import OpticalAutocorrelation.source.Pair;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Montahaee
 * @version 1.0
 * @created 11-May-2022 11:47:15 AM
 */
public class Evaluator extends Action  {

	private final double transformFactor =  0.00101585775702574549005695364744;

	/**
	 * 
	 * @param receiver
	 */
	public Evaluator(Receiver receiver){
		super(receiver);

	}

	private double[] xTranslation(int[] xValues){
		if (xValues == null) {
			throw new NullPointerException("The input array 'x values' is null for a translation!");
		}
		return Arrays.stream(xValues).mapToDouble(e -> e * transformFactor - 132.3).toArray();
	}
	@SuppressWarnings("unchecked")
//	TODO is the transformation is kind of transaction or input
	public boolean activate(){
//		if (!(receiver.transformation instanceof TransactionSource)) {
//			return false;
//		}

//		if (!(receiver.transformation instanceof InputSource)) {
//			return false;
//		}
		boolean executed = (receiver.processor == null);
		if (!executed) {
			return false;
		}
//		if (receiver.transformation instanceof InputSource) {
//			InputSource<?, ?> input = (InputSource<?, ?>) receiver.transformation;
//			if (!input.getSourceName().isEmpty() && input.miner().getSource() instanceof File &&
//					input.miner().getContent() instanceof List &&
//					Stream.of(input.miner().getContent()).noneMatch(List::isEmpty) &&
//					Stream.of(input.miner().getContent()).allMatch(Pair.class::isInstance)) {
//				List<? extends Pair<?, ?>> content = input.miner().getContent();
//				if (Stream.of(content).allMatch(l -> l.stream().noneMatch(Pair::isEmpty)) &&
//						Stream.of(content).allMatch(l -> l.stream().allMatch(p -> p.getSource() instanceof Integer)) &&
//						Stream.of(content).allMatch(l -> l.stream().allMatch(p -> p.getContent() instanceof Integer))) {
//					List<Pair<Integer, Integer>> s = (List<Pair<Integer, Integer>>) content;
//				}
//			}
//		}
		executed = receiver.transformation instanceof InputSource;
		while (executed) {
			InputSource<?, ?> input = (InputSource<?, ?>) receiver.transformation;
			executed = !input.getSourceName().isEmpty();
			executed &= input.miner().getSource() instanceof File;
			executed &= input.miner().getContent() instanceof List;
			executed &= Stream.of(input.miner().getContent()).noneMatch(List::isEmpty);
			executed &= Stream.of(input.miner().getContent()).allMatch(Pair.class::isInstance);
			List<? extends Pair<?, ?>> content = input.miner().getContent();
//			executed &= Stream.of(content).allMatch(Pair.)
			executed &= Stream.of(content).allMatch(l-> l.stream().noneMatch(Pair::isEmpty));
			executed &= Stream.of(content).allMatch(l-> l.stream().allMatch(p -> p.getSource() instanceof Integer));
			executed &= Stream.of(content).allMatch(l-> l.stream().allMatch(p -> p.getContent() instanceof Integer));
			backup();
			File file = input.miner().getSource();
			List<Pair<Integer,Integer>> pairs = (List<Pair<Integer, Integer>>) content;

			new Thread(() -> {
//				List<Pair<Integer,Integer>> s = (List<Pair<Integer, Integer>>) content;
//				var t = (InputSource)receiver.transformation;
				TransactionSource<File,Integer> transaction = new TransactionSource<File, Integer>(
						new Pair<>(input.miner().getSource(), pairs)) {
//					@Override
//					public Pair<?, ?> transactionData() {
//						return null;
//					}
//				}
//				TransactionSource<File, Integer> transaction = new TransactionSource<>(
//						((InputSource<String,Integer>)receiver.transformation).miner()
//				) {
//				@Override
//				public Pair<?, ?> transactionData() {
//					return null;
//				}
//			}
//			receiver.transformation = new TransactionSource<Integer>() {}
//			receiver.processor =
//					new TransactionSource<Integer>(receiver.transformation.miner()) {
//			String sourceName = receiver.getName(); ??? IS NEEDED
//			final String sourceName = ((File)receiver.transfer.miner().getSource()).getName();
//			final String sourceName = ((File)receiver.processor.getSource()).getName();
//			final List<Pair<T,T>> sources =

					/**
					 //			 * @param input
					 * @return
					 */
					@Override
					public Pair<?,?> transactionData() {
						List<Pair<Integer,Integer>> content =
								((List<Pair<Integer, Integer>>) receiver.processor.getContent());
//						public Pair<?,?> transactionData() {
//												List<Pair<Integer,Integer>> content =
//														((List<Pair<Integer, Integer>>) receiver.processor.getContent());

//				var norm = normalize(receiver.transfer.miner().getContent().
//				content.forEach((k, v)-> v);
						int[] positions = Stream.of(content).mapToInt(e -> {
							Iterator<Pair<Integer,Integer>> it = e.iterator();
							return it.next().getSource();
						}).toArray();

						int[] intensities = Stream.of(content).mapToInt(e -> {
							var it = e.iterator();

//						content.get(e.indexOf(i)).getContent();
							return it.next().getContent();
						}).toArray();
//				int[] norm= Arrays.stream(new List[]{content}).mapToInt(e->)
						double[] smooths = smoothing(xTranslation(positions)) ;
						double[] norms = normalize(intensities);
						double[] upperEnv = upperEnveloping(norms);
//				double[] upperCovers = norms;
//				Pair<double[], double[]> p1 = new Pair<>(smooths, norms);
//				Pair<double[], double[]> p2 = new Pair<>(smooths, upperEnveloping(norms));
//				File outputFile = new File("out"+sourceName);
//				if (outputFile.exists()) {
////					throw new FileAlreadyExistsException("The outputFile"+ outputFile.getName() +"is already exist.");
//					throw new RuntimeException("The outputFile " + outputFile.getName() + "is already exist!");
//				} else if (!outputFile.canWrite()) {
//					throw new IllegalArgumentException("The outputFile " + outputFile.getName() +
//							" cannot be written!");
//				}
//				try {
//					FileWriter fw = new FileWriter(outputFile);
//					fw.write("# FWHM = " + format(FWH(new Pair<>(smooths, upperEnveloping(norms)))) +
//							"\n# pos\tint\tenv\n" + format(new Pair<>(smooths,
//							new Pair<>(norms,upperEnveloping(norms)))));
//					fw.close();
//
//				} catch (IOException e) {
//					e.printStackTrace();
//				}

//				List<Pair> output= new ArrayList<>();
						if (smooths.length != norms.length) {
							throw new RuntimeException("The smoothing process is failed because of" +
									" different size between x and y!");
						}
						return new Pair<>(FWH(new Pair<>(smooths, upperEnv)),new Pair<>(
								smooths,new Pair<>(norms,upperEnv)))  ;
//				return null;
					}
				};
			}).start();
		}
		if (receiver.transformation instanceof InputSource) {
//			InputSource<S, T> input = (InputSource<S, T>) receiver.transformation;
			InputSource<?, ?> input = (InputSource<?, ?>) receiver.transformation;
//			if (Stream.of(input.getSourceName()).noneMatch(String::isEmpty) &&
//			if (Stream.of(input.getSourceName()).allMatch((String::isEmpty)) &&
			if (!input.getSourceName().isEmpty() &&
					input.miner().getSource() instanceof File  &&
					input.miner().getContent() instanceof List &&
//					Stream.of(input.miner().getSource()).allMatch(File.class::isInstance) &&
					Stream.of(input.miner().getContent()).noneMatch(List::isEmpty) 		  &&
					Stream.of(input.miner().getContent()).allMatch(Pair.class::isInstance)) {

				InputSource<String, List<?>> in = (InputSource<String, List<?>>) input;

			}
		}
//		if (!(receiver.transfer.miner().getSource() instanceof File))
//		if (!receiver.transfer.miner().isEmpty() || !(receiver.transfer.miner().getContent() instanceof List<?>)) {
//			return false;
//		}
//		if (receiver.transfer.miner().getContent() instanceof List<?>) {
//			List<?> list = (List<?>) receiver.transfer.miner();
////			if (!list.stream().allMatch(obj  -> Pair.class.isInstance(obj)))
////			if (list.isEmpty() || !list.stream().allMatch(Pair.class::isInstance)) {
//			if (!list.isEmpty() && list.stream().allMatch(Pair.class::isInstance)) {
//				List<Pair<?,?>> pairs = (List<Pair<?, ?>>) list;
////				pairs.isEmpty()
//			} else {
////				var pairs = (List<Pair>)list;
//
//			}
//		}
		backup();
		new Thread(() -> {
			var t = (InputSource)receiver.transformation;
			TransactionSource<File, Integer> transaction = new TransactionSource<>(
					((InputSource<String,Integer>)receiver.transformation).miner()
			      ) {
//				@Override
//				public Pair<?, ?> transactionData() {
//					return null;
//				}
//			}
//			receiver.transformation = new TransactionSource<Integer>() {}
//			receiver.processor =
//					new TransactionSource<Integer>(receiver.transformation.miner()) {
//			String sourceName = receiver.getName(); ??? IS NEEDED
//			final String sourceName = ((File)receiver.transfer.miner().getSource()).getName();
//			final String sourceName = ((File)receiver.processor.getSource()).getName();
//			final List<Pair<T,T>> sources =

						/**
						 //			 * @param input
						 * @return
						 */
						@Override
						public Pair<?,?> transactionData() {
							List<Pair<Integer,Integer>> content =
									((List<Pair<Integer, Integer>>) receiver.processor.getContent());
//						public Pair<?,?> transactionData() {
//												List<Pair<Integer,Integer>> content =
//														((List<Pair<Integer, Integer>>) receiver.processor.getContent());

//				var norm = normalize(receiver.transfer.miner().getContent().
//				content.forEach((k, v)-> v);
							int[] positions = Stream.of(content).mapToInt(e -> {
								Iterator<Pair<Integer,Integer>> it = e.iterator();
								return it.next().getSource();
							}).toArray();

							int[] intensities = Stream.of(content).mapToInt(e -> {
								var it = e.iterator();

//						content.get(e.indexOf(i)).getContent();
								return it.next().getContent();
							}).toArray();
//				int[] norm= Arrays.stream(new List[]{content}).mapToInt(e->)
							double[] smooths = smoothing(xTranslation(positions)) ;
							double[] norms = normalize(intensities);
							double[] upperEnv = upperEnveloping(norms);
//				double[] upperCovers = norms;
//				Pair<double[], double[]> p1 = new Pair<>(smooths, norms);
//				Pair<double[], double[]> p2 = new Pair<>(smooths, upperEnveloping(norms));
//				File outputFile = new File("out"+sourceName);
//				if (outputFile.exists()) {
////					throw new FileAlreadyExistsException("The outputFile"+ outputFile.getName() +"is already exist.");
//					throw new RuntimeException("The outputFile " + outputFile.getName() + "is already exist!");
//				} else if (!outputFile.canWrite()) {
//					throw new IllegalArgumentException("The outputFile " + outputFile.getName() +
//							" cannot be written!");
//				}
//				try {
//					FileWriter fw = new FileWriter(outputFile);
//					fw.write("# FWHM = " + format(FWH(new Pair<>(smooths, upperEnveloping(norms)))) +
//							"\n# pos\tint\tenv\n" + format(new Pair<>(smooths,
//							new Pair<>(norms,upperEnveloping(norms)))));
//					fw.close();
//
//				} catch (IOException e) {
//					e.printStackTrace();
//				}

//				List<Pair> output= new ArrayList<>();
							if (smooths.length != norms.length) {
								throw new RuntimeException("The smoothing process is failed because of" +
										" different size between x and y!");
							}
							return new Pair<>(FWH(new Pair<>(smooths, upperEnv)),new Pair<>(
									smooths,new Pair<>(norms,upperEnv)))  ;
//				return null;
						}
					};
		}).start();

//		receiver.transfer.setResource(receiver.processor);
		return true;
	}

	private Pair<Float,Pair<Integer, Integer>> FWH(Pair<double[], double[]> sc){
		if (sc.getSource().length != sc.getContent().length) {
			throw new IllegalArgumentException("The pair elements must have the same length!");
		}

		Map<Double, Double> pairs = new LinkedHashMap<>(sc.getContent().length);
		for (int i = 0; i < sc.getContent().length; i++) {
			pairs.put(sc.getContent()[i], sc.getSource()[i]);
		}
//		ArrayList <Pair<Double, Double>> pairs1 = new ArrayList<>(sc.getContent().length);
//		for (int i = 0; i < sc.getContent().length; i++) {
//			pairs1.add(new Pair<>(sc.getContent()[i], sc.getSource()[i]));
//		}
////		This list has to consist of two pair elements namely L & R
//		double[] result1 = pairs1.stream().distinct().mapToDouble(Pair::getContent
//		) .filter(e -> e >= 0 && e == DoubleStream.of(sc.getContent()).max().getAsDouble() / 2).toArray();
//		if (result1.length !=2) {
//			throw new RuntimeException("The try to identify the right and left of at half of maximum is failed!");
//		}
//		var t2 = result1.
//		if (!pairs1.ge) {
//
//		}
//		Pair<Integer, Integer> idx = new Pair<>(pairs1.indexOf(result1[0]), pairs1.indexOf(result1[1]));

//		ArrayList<Double> vat = new ArrayList<>();
//		var t = vat.stream().filter(aDouble -> aDouble>=0 && aDouble ==
//				DoubleStream.of(sc.getContent()).max().getAsDouble()/2)
//		pairs1.en
//		var  max = pairs.entrySet().stream().max(Map.Entry.comparingByValue());
		Map<Double, Double> result = pairs.entrySet().stream().filter(
				map -> map.getValue() >= 0 &&  map.getValue() ==
						DoubleStream.of(sc.getContent()).max().getAsDouble()/2
//		).collect(Collectors.toMap(map ->map.getKey(), map -> map.getValue()));
		).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		if (result.size() != 2) {
			throw new RuntimeException("The try to identify the right and left of at half of maximum is failed!");
		}
		Pair<Integer, Integer> p;
		ArrayList<Double> keys = new ArrayList<>(pairs.keySet());
		Pair<Integer, Integer> pIdx = new Pair<>(keys.indexOf(result.keySet().toArray(Double[]::new)[0]),
				keys.indexOf(result.keySet().toArray(Double[]::new)[1]));
		//TODO find a way to save the indexes  of the L and R plus FWH
//		Pair<Integer, Integer> lr = pairs
//		var t = result.keySet().stream().reduce((a, b) -> (a +b)/2).stream().mapToDouble(e->e).average().getAsDouble();
//		float fwh = (float) result.keySet().stream().mapToDouble(e-> Math.abs(e)).average().getAsDouble();
		float fwh = (float) result.keySet().stream().mapToDouble(Math::abs).average().getAsDouble();
//      If there generally are mor than two candidates r.g.t.
//      filtering condition average calculate mean value of them.
		return new Pair<>(fwh,pIdx);
//		return (float) Arrays.stream(result).distinct().mapToDouble(e-> Math.abs(e)).average().getAsDouble();
//		return (float) Stream.of(result1).distinct().mapToDouble(e-> Math.abs(e)).average().getAsDouble();
	}

	/**
	 *
	 * @param intensities as the first column in the data
	 */
	private double[] normalize(int[] intensities){
		if (intensities == null  ) {
			throw new NullPointerException("The input array 'y values' is null to normalize!");

		} else if (IntStream.of(intensities).max().isEmpty()) {
			throw new NoSuchElementException("the optional value is an empty optional int!");
		} else {
			double max = IntStream.of(intensities).max().getAsInt();
			return Arrays.stream(intensities).mapToDouble(e -> e/max).toArray();
		}
	}

	/**
	 *
	 * @param positions to make a smoothing domain approach.
	 */
	private double[] smoothing(double[] positions){
		int n = (int)(0.002 * positions.length);
		if (n % 2 == 0) {
			n--;
		}
		double[] sd = new double[positions.length];
		int t = (n -1) / 2;
		for (int k = 0; k < positions.length ; k++) {
			for (int i = n - (1 + t); i >= 0; i--) {
				sd[k] += positions[i + k];
			}
			sd[k] /= n;
		}
		return sd;
	}

	/**  This method will be latter written by file writer
	 *   and seen as setting of {@link #normalize(int[])}
 	 */
	private double[] upperEnveloping(double[] norms){
		double temp = norms[0];
		for (int i = 1; i < norms.length - 1; i++) {
			if (temp > norms[i]) {
				norms[i] = temp;
			} else {
				temp = norms[i];
			}
		}
		return norms;
	}

	private String format(Pair<?, ?> pair) {
// TODO add space between all elements of keys as well as values
		StringBuilder str = new StringBuilder();
		if (pair.getSource() != null && pair.getSource() instanceof Pair) {
			str.append(format((Pair<?, ?>) pair.getSource()));
		} else if (pair.getContent() != null && pair.getContent() instanceof Pair) {
			str.append(format((Pair<?, ?>) pair.getContent()));
		} else {
//			str.append(Arrays.toString((Object[]) pair.getSource()));
			str.append(pair.getSource()).append("\t");
			str.append(pair.getContent());
		}


		return str.toString();
	}
}//end Evaluator