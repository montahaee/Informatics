package recycelbin.display.framework;


import GroProOnlinestrategiespiel.graph.Field;
import GroProOnlinestrategiespiel.utility.Coordinates;
import recycelbin.display.Display;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * This class define input behavior of the {@link DescriptionDecorator}
 * @author Montahaee
 * @version 1.0
 * @created 26-July-2022 12:37:40 PM
 */
public class InputDescription extends DescriptionDecorator {

	public InputDescription(Description description) {
		super(description);
	}

	/**
	 * @param field is the available place for traversing
	 */
	@Override
	public void way(Field field) {

		Coordinates start_end = field.getDistance();
		Coordinates boundaries = field.getBoundaries();
		int[] weights = field.getWeights();
		StringBuilder content = new StringBuilder("%");
		content.append(field.getPlace());
		content.append("with ");
		content.append(boundaries.getRow());
		content.append(" X ");
		content.append(boundaries.getColumn());
		content.append("elements\n");
		int k = 0;
		for (int i = 1; i <= boundaries.getRow(); i++) {
			for (int j = 1; j <= boundaries.getColumn(); j++) {
//				first check if the weight is positiv!
				if (weights[k] <= 0 || weights[k] > 10 ) {
					throw new IllegalArgumentException("the weight " + weights[i] + " is not restricted between 1 and 10!");
				}
				if (start_end.getRow() == boundaries.getColumn()*(i-1) +  j) {
					content.append('S'); //  Additions start symbol insert into the way.

// 			    pseudo remove the weight as it required in the task sheet to prevent a loop view of start point
					weights[k++] = 0;   //
				} else if (start_end.getColumn() == boundaries.getColumn()*(i-1) +  j) {
					content.append('T'); // Additions target symbol insert into the way.
					weights[k++] = 0;   // pseudo remove of the weight as it required in the task sheet!
				} else {
					content.append(weights[k++]);
					content.append(", ");
				}
			}
			content.append("\n");
		}

		try {//TODO give the truth path for input file(source/input/rawInfo.txt)!/ or this prompt in the Demo/test class
//			var input = new  File("/source/input/rawInfo.txt");
			var input = Paths.get("/source/input/rawInfo.txt").toFile();
			if (input.createNewFile()) {
				System.out.println("input File create: " + input.getName());
			} else {
//				System.out.println("File already exists.");
				throw new RuntimeException ("The raw info file already exists!");
			}
			var inputWriter = new FileWriter("./" + input.getName());
			inputWriter.write(content.toString());
			inputWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred:");
			e.printStackTrace();
		}
	}

	/**
	 * To represent {@link #way(Field)}
	 * in rawly visual form on a display{@link #<Display>}.
	 */
	@Override //TODO add new frame to employ in the concert input display class!
	public void render() {

	}
}//end InputDescription