package OpticalAutocorrelation.action;

import OpticalAutocorrelation.receiver.*;
import OpticalAutocorrelation.source.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author Montahaee
 * @version 1.0
 * @created 11-May-2022 11:47:15 AM
 */
public class Detector extends Action {

	private final String path;
///	private LinkedList<String> paths = new LinkedList<>();
//	private LinkedList<File> inputs = new LinkedList<>();
//TODO see the guidelines of Seb and check/find/create
// a specific boolean function for distinguish specific
// activity in the specific threads. The run function.
// Another point check/read about yield and slip in run
// function.The runnable has to happen here but track
// in the receiver. Think about if you run a list
// of files with their contents at one times or only one file
// with its corresponding content. Insert after backup into run
// and writ while true mit sleep for detector process, then
// complete other boolean condition to deactivate the action.

	/**
	 * 
	 * @param receiver contains some logic to simulate the autocorrelator
	 */
//	public Detector(Receiver receiver, String thatPath){
	public Detector(Receiver receiver, String thatPath){
		super (receiver);
//		if (getFiles(thatPath).size() != 10) {
//			throw new IllegalArgumentException("The data load capacity are constant with 10.");
//		}
//		this.paths.addAll(thatPaths);
		this.path = thatPath;


	}

///	private String detectorStr(){
//		return "";
//	}

//	private LinkedList<String> getFiles (final String folderPath) {
//		LinkedList<String> circularFiles = new LinkedList<>();
//		File folder = new File(folderPath);
//		File[] files = folder.listFiles();
//		for (File file :
//				Objects.requireNonNull(files)) {
//			if (file.isFile() && file.getName().endsWith(".txt")) {
//				circularFiles.add(file.getName());
//			} else if (file.isDirectory()) {
//				getFiles(file.getPath());
//			}
//		}
////		circularFiles.
//		return circularFiles;
//	}
	private LinkedList<File> getFiles (final String folderPath) {
		LinkedList<File> circularFiles = new LinkedList<>();
		File folder = new File(folderPath);
		File[] files = folder.listFiles();
		for (File file :
				Objects.requireNonNull(files)) {
			if (file.isFile() && file.getName().endsWith(".txt")) {
				circularFiles.add(file);
			} else if (file.isDirectory()) {
				getFiles(file.getPath());
			}
		}
//		circularFiles.
		return circularFiles;
	}
//	@SuppressWarnings("unchecked")
	public boolean activate(){
//		Pair<String, String[]> resource = (Pair<String, String[]>) receiver.transfer.miner();
		String resource = "";
		LinkedList<?> contents = new LinkedList<>();
//		if (receiver.transfer == null ) {
//		if (!(receiver.transfer.miner().isEmpty())) {
//		In the first step/thread the both processor and miner are in the silence
//		if (!(receiver.processor.isEmpty() && receiver.transfer.miner().isEmpty())) {
//		if (!receiver.processor.isEmpty()) {
//		DataSource input = receiver.transfer;
//		TODO is the following if statement is necessary/true
//		 here? Or maybe is better to ask if the transformation
//		 is an instance of output.(the third if statement.
		if (!(receiver.transformation instanceof InputSource)) {
			return false;
		}


		if (path.isEmpty() || path.isBlank()) {
			return  false;
		}

		if (!(receiver.transformation instanceof OutputSource)) {
			return false;
		}

//		InputSource input = (InputSource) receiver.transfer;
//		if (!(input instanceof InputSource)) {
//			return false;
//		}
		if (!(receiver.transformation.miner().getSource() instanceof String)) {
			return false;
		} else
		if (receiver.transformation.miner().isEmpty() || !(receiver.transformation.miner().getContent() instanceof LinkedList<?>)) {
//		if (!receiver.transfer.miner().isEmpty() || !(receiver.transfer.miner().getContent() instanceof String)) {
			return false;
		} else if (receiver.transformation.miner().getSource() instanceof String &&
				   receiver.transformation.miner().getContent() instanceof LinkedList<?>) {
			resource = (String) receiver.transformation.miner().getSource();
			contents = (LinkedList<?>) receiver.transformation.miner().getContent();
			boolean expected = true;
			if (resource.isEmpty() || resource.isBlank()) {
				throw new IllegalArgumentException("The path to parse is not available or blanked");
//			} else if (!contents.parallelStream().allMatch(String.class::isInstance)) {
			} else if (!contents.stream().allMatch(File.class::isInstance)) {
				return false;
			} else if (contents.stream().allMatch(File.class::isInstance)) {


			}


//
//
//				return String.class.isInstance(obj);
//			}) ) .startsWith("^[1-9]?$|^10$")) {
//			}  else if (!contents.parallelStream().map((String)e -> e. ) .startsWith("^[1-9]?$|^10$")) {
//			}  else if (!resource.startsWith("^[1-9]?$|^10$")) {
//			} else if (!getFiles(resource).startsWith("^[1-9]?$|^10$"))) {
				throw new IllegalArgumentException("The resource name to explorer is restricted" +
						" with starting a number between 1 to 10");
			}

//		else if (receiver.transfer.miner().getContent() instanceof String) {
//			resource.getContent() = (String) receiver.transfer.miner().getContent();
//

//			if (resource.isEmpty() || resource.isBlank() || resource.startsWith(String.valueOf("^[1-9]?"))
		backup();

//		TODO is rename test and its content as linkedList of String
//		 And change the path parameter in the inputSource
//		 to the filename and
///		var filenames = getFiles(path).offer();
///
///		for (String filename :
//				filenames) {
//
//		}
//		receiver.start();
//		var miner = receiver.transfer.miner();
//		receiver.processor = new InputSource(filenames.getFirst()) {
//		var filenames = getFiles(resource.getSource());

//		var files = getFiles(((InputSource<?, ?>) receiver.transfer).getSourceName());
		var files = getFiles(path);
//		InputSource<String,Integer> input;
//		var filenames = re;
		new Thread(() -> {
			try {
				while (!files.isEmpty()) {
					InputSource<String,Integer> input = new InputSource<>(files.pop().getName()) {
//					var input = new InputSource<String,Integer>(files.pop().getName()) {
//					receiver.processor = new InputSource<String,Integer>(files.pop().getName()) {

						/**
						 * @param file is a concrete type for initial processing
						 * @return concrete processing data to next step
						 */
						@Override
						public List<Pair<Integer, Integer>> inputData(File file) {
							List<Pair<Integer, Integer>> content = new ArrayList<>();

							if (!(file.canRead() && file.getName().startsWith("^[0-9]?$")
									&& file.getName().endsWith(".txt"))) {
								throw new IllegalArgumentException("the file is not readable as text format!");
							}
							try {
								Scanner in = new Scanner(file);
								String line = "";
								do {
									line = in.nextLine();
								} while (line.charAt(0) == '#');
								while (in.hasNextLine()) {
									String[] YX = line.split("\t");
									if (YX.length != 2) {
										throw new InvalidPropertiesFormatException(
												"The number of datasets is expected to be tow!");
									}
									line = in.nextLine();
									content.add(new Pair<>(Integer.valueOf(YX[1]), Integer.valueOf(YX[0])));
								}
							} catch (FileNotFoundException | InvalidPropertiesFormatException  fie) {
								fie.printStackTrace();
							}
							return content;
						}
					};
					receiver.processor = input.miner();
					/*
					  The following puts thread to sleep for 0.05
					   second to allow other threads to execute.
					 */
					TimeUnit.MILLISECONDS.sleep(50);
					receiver.transformation = input;
				}
//				Thread.sleep(50);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}

		}).start();
//		TODO is decision for suitable variable as transmission and passing in to other
//		 threads like: https://stackoverflow.com/questions/9761816/java-thread-passing-parameters-to-another-thread
//		receiver.transformation =
//		receiver.transformation.miner() = receiver.processor;


//			/**
//			 * //			 * @param input is coming data to parsing
//			 *
//			 * @return
//			 */
//			@Override
//			public Pair processingData() {
//				List<Pair<Integer, Integer>> content = new ArrayList<>();
//
//				if (!((File) input.getContent()).canRead() || !((File) input.getContent()).getName().endsWith(".txt")) {
//					throw new IllegalArgumentException("the file is not readable as text format!");
//				}
//				try {
//					Scanner in = new Scanner((File) input.getContent());
//					String line = "";
//					do {
//						line = in.nextLine();
//					} while (line.charAt(0) == '#');
//					while (in.hasNextLine()) {
//						String[] YX = line.split(" ");
//						line = in.nextLine();
//						content.add(new Pair<>(Integer.valueOf(YX[1]), Integer.valueOf(YX[0])));
//					}
//				} catch (FileNotFoundException e) {
//					e.printStackTrace();
//				}
//				return content;
//			}

//			/**
//			 * @param file to parsing
//			 * @return processing content data
//			 */

//			public List<Pair<Integer, Integer>> processingData(File file) {
//				List<Pair<Integer, Integer>> content = new ArrayList<>();
//				if (!file.canRead() || !file.getName().endsWith(".txt")) {
//					throw new IllegalArgumentException("the file is not readable as text format!");
//				}
//				try {
//					Scanner input = new Scanner(file);
//					String line = "";
//					do {
//						line = input.nextLine();
//					} while (line.charAt(0) == '#');
//					while (input.hasNextLine()) {
//						String[] YX = line.split(" ");
//						line = input.nextLine();
//						content.add(new Pair<>(Integer.valueOf(YX[1]), Integer.valueOf(YX[0])));
//					}
//				} catch (FileNotFoundException e) {
//					throw new RuntimeException(e);
//				}
//				return content;
//			}
//		}.miner();

		return true;
	}

//	/**
//	 * When an object implementing interface {@code Runnable} is used
//	 * to create a thread, starting the thread causes the object's
//	 * {@code run} method to be called in that separately executing
//	 * thread.
//	 * <p>
//	 * The general process of the method {@code run} is that it may
//	 * take any action whatsoever.
//	 *
//	 * @see Thread#run()
//	 */
//	@Override
//	public void run() {
//
//		var filenames = getFiles(path);
//
//	}
}//end Detector