package OpticalAutocorrelation.action;

import OpticalAutocorrelation.receiver.Receiver;
import OpticalAutocorrelation.source.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.*;


/**
 * @author Montahaee
 * @version 1.0
 * @created 11-May-2022 11:47:15 AM
 */
public class Simulator extends Action {




	/**
	 * 
	 * @param receiver
	 */
	public Simulator(Receiver receiver){
		super(receiver);

	}

	@SuppressWarnings("unchecked")
	public boolean activate(){

		if (receiver.transformation.miner().isEmpty()){

			return false;
		}
		backup();
		Pair<File,Pair<Pair<Float,Pair<Integer, Integer>>,Pair<double[],Pair<double[], double[]>>>> miner = (Pair<
		File, Pair<Pair<Float,Pair<Integer,Integer>>,Pair<double[],Pair<double[],double[]>>>>) receiver.transformation.miner();
		/**
		 * instance
		 */
//		Question:
		receiver.processor = new OutputSource <File,Pair<Pair<Float, Pair<Integer, Integer>>, Pair<double[], Pair<double[], double[]>>>>(miner) {
//		receiver.processor = new OutputSource (miner) {
//		receiver.processor = new OutputSource <File,Pair<Pair<Float,Pair<Integer, Integer>>,
//			Pair<double[],Pair<double[],double[]>>>>((Pair<File,Pair<Pair<Float,Pair<Integer, Integer>>,
//			Pair<double[],Pair<double[],double[]>>>>)receiver.transfer.miner()) {
//
			final String sourceName = ((File)receiver.processor.getSource()).getName();
			/**
			 * @return
			 */
			@Override
//			public List<Pair> processingData() {
			public Pair outputData() {
				Pair<Float,Pair<Integer, Integer>> fwh = ((Pair<Pair<Float,Pair<Integer, Integer>>,Pair>)
						receiver.processor.getContent()).getSource();
				double[] smooths =  miner.getContent().getContent().getSource();

				File outputFile = new File("out"+sourceName);
				if (outputFile.exists()) {
//					throw new FileAlreadyExistsException("The outputFile"+ outputFile.getName() +"is already exist.");
					throw new RuntimeException("The outputFile " + outputFile.getName() + " is already exist!");
				} else if (!outputFile.canWrite()) {
					throw new IllegalArgumentException("The outputFile " + outputFile.getName() + " cannot be written!");
				}
				try { //TODO replacing the file writer form Evaluator to Simulator
					// and replace null in the Evaluator with some type as receiver processor like here!

					FileWriter fw = new FileWriter(outputFile);
					fw.write("# FWHM = " + format(miner.getContent().getSource()) +
							"\n# pos\tint\tenv\n" + format(miner.getContent().getContent()));

					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}
		}.miner();
		return true;
	}

	private String format(Pair<?, ?> pair) {
// TODO add space between all elements of keys as well as values
		StringBuilder str = new StringBuilder();
		if (pair.getSource() != null && pair.getSource() instanceof Pair) {
			Pair<?,?> sPair = (Pair<?, ?>) pair.getSource();
			if (pair.getContent() instanceof Number[]) {
				if (!((sPair.getSource() instanceof Number[] && sPair.getContent() instanceof Number[])) ||
						(((Number[]) sPair.getContent()).length != ((Number[]) sPair.getSource()).length) &&
								((Number[]) sPair.getSource()).length != ((Number[]) pair.getContent()).length) {
					throw new IllegalArgumentException("The nested input pair was not formed as expected");
				} else {
					Map<Double, Pair<Double, Double>> triple = new LinkedHashMap<>(((Number[]) pair.getContent()).length);
					double[] pgc = (double[]) pair.getContent();
					double[] sgs = (double[]) sPair.getSource();
					double[] sgc = (double[]) sPair.getContent();
					triple = IntStream.range(0, pgc.length).boxed().collect(Collectors.toMap(
							i -> pgc[i], i -> new Pair<>(sgs[i], sgc[i])));
//					either add triple directly in the string
//					str.append(triple);
//					or using loop to reach the desired writing format
					triple.forEach((k, v) -> str.append(k).append(" ").append(
							v.getSource()).append("\t").append(v.getContent()));
				}
			} else {
				str.append(format(sPair));
			}
		} else if (pair.getContent() != null && pair.getContent() instanceof Pair) {
			Pair<?, ?> cPair = (Pair<?, ?>) pair.getContent();
			if (pair.getSource() instanceof Number[]) {
				if (!((cPair.getSource() instanceof Number[] && cPair.getContent() instanceof Number[])) ||
						(((Number[]) cPair.getContent()).length != ((Number[]) cPair.getSource()).length) &&
				((Number[]) cPair.getSource()).length != ((Number[]) pair.getSource()).length) {
					throw new IllegalArgumentException("The nested input pair was not formed as expected");
				} else {
					Map<Double,Pair<Double,Double>> triple = new LinkedHashMap<>(((Number[]) pair.getSource()).length);
//					var t = ();
//					for (Map.Entry<Double, Pair<Double, Double>>)
//					var cps = Stream.of(cPair.getSource()).collect(Collectors.toList()).toArray();
					double[] pgs = (double[])pair.getSource();
					double[] cgs = (double[])cPair.getSource();
					double[] cgc = (double[])cPair.getContent();
					triple = IntStream.range(0, pgs.length).boxed().collect(Collectors.toMap(
									i -> pgs[i], i -> new Pair<>(cgs[i], cgc[i])));
//									i -> (Arrays.stream((double[])pair.getSource()).toArray()[i]), i ->

//							(Stream.of(cPair).collect(Collectors.toList()).toArray()[i])));
//					for (double gs :
//							(double[])pair.getSource()) {
//						triple.p
//					}
//					for (int i = 0; i < ((Number[]) pair.getSource()).length; i++) {
//						triple.put((double[])pair.getSource()[i],);
//						triple.p
//					}

//					either add triple directly in the string
//					str.append(triple);
//					or using loop to reach the desired writing format
					triple.forEach((k, v) -> str.append(k).append(" ").append(
							v.getSource()).append("\t").append(v.getContent()));
				}

			} else {
				str.append(format(cPair));
			}

		} else if (pair.getSource() instanceof Arrays && pair.getContent() instanceof Arrays){
			if (Stream.of ((Arrays)pair.getSource()).toArray().length != Stream.of((Arrays)
					pair.getContent()).toArray().length) {
				throw new RuntimeException("The length of the pairwise lists has to be the same as process data!");
			}
			double[] ps = (double[]) pair.getSource();
			double[] pc = (double[]) pair.getContent();
//
//			double[] s = (double[]) IntStream.range(0, IntStream.of(pair.getSource())
//			Arrays.parallelSetAll(sc, i -> Stream.of(pair.getSource()).mapToDouble(e ->
//					Arrays.stream((double[]) e)).toArray()
//					(Arrays.stream(e.getSource()
////			str.append(Arrays.toString((Object[]) pair.getSource()));
//			var t = (Stream.of(((Arrays) pair.getSource()).toString(),
//					((Arrays) pair.getContent()).toString()).flatMap(
//					(key) -> {
//						return (key.split(" ")));
//					}).
///					Stream.of(((Arrays) pair.getSource()).toString().split(" ")),
//					Stream.of((Arrays) pair.getSource()).toString().split(" ")));
///			String source = Arrays.toString((Object[]) pair.getSource());
//			String content = Arrays.toString((Object[]) pair.getContent());
			Map<Double, Double> psc = IntStream.range(0, ps.length).boxed().collect(Collectors.toMap(
					i -> ps[i], i -> pc[i]));
			psc.forEach((k,v) -> str.append(k).append("\t").append(v));
		}


		return str.toString();
	}

}//end Output