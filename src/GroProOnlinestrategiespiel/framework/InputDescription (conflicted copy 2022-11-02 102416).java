package GroProOnlinestrategiespiel.framework;


import GroProOnlinestrategiespiel.graph.Field;
import GroProOnlinestrategiespiel.utility.Coordinates;

import javax.swing.*;
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

		Coordinates start_end = field.getDirection();
		Coordinates boundaries = field.getBoundaries();
		int[] weights = field.getWeights();
		StringBuilder content = new StringBuilder("%");
		content.append(field.getPlace());
		content.append(" with ");
		content.append(boundaries.getRow());
		content.append(" X ");
		content.append(boundaries.getColumn());
		content.append(" elements\n");
		int k = 0;
		for (int i = 1; i <= boundaries.getRow(); i++) {
			for (int j = 1; j <= boundaries.getColumn(); j++) {
//				first check if the weight is positiv!
				if (weights[k] <= 0 || weights[k] > 10 ) {
					throw new IllegalArgumentException("the weight " + weights[i] + " is not restricted between 1 and 10!");
				}
				if (start_end.getRow() == boundaries.getColumn()*(i-1) +  j) {
					content.append("S, "); //  Additions start symbol insert into the way.

// 			    pseudo remove the weight as it required in the task sheet to prevent a loop view of start point
					weights[k++] = 0;   //
				} else if (start_end.getColumn() == boundaries.getColumn()*(i-1) +  j) {
					if (boundaries.getColumn()*(i-1) + j == weights.length) {
						content.append('T'); // Additions target symbol at the end fo the raw way.
					} else {
						content.append("T, "); // Additions target symbol into the raw way.
					}
					weights[k++] = 0;   // pseudo remove of the weight as it required in the task sheet!
				} else {
					if (boundaries.getColumn()*(i-1) + j == weights.length) {
						content.append(weights[k++]); // Additions the integer at the end fo the raw way.
					} else {
						content.append(weights[k++]);
						content.append(", ");
					}
				}
			}
			content.append("\n");
		}

		try {//TODO give the truth path for input file(source/input/rawInfo.txt)!/ or this prompt in the Demo/test class
			String filename = JOptionPane.showInputDialog("Enter the filename to write raw input in that");
			String currentPath = Paths.get("src","GroProOnlinestrategiespiel","source","input").toString();
//			var input = Paths.get("\\source\\input"+ filename +".txt").toFile();
			var input = Paths.get(currentPath + "/"+ filename+".txt").toFile();
//			String path = input.getAbsolutePath();
			if (input.createNewFile()) {
				System.out.println("input File create: " + input.getAbsolutePath());
			} else {
				System.out.println("File already exists, thus it will be override!");
//				throw new RuntimeException ("The raw info file already exists!");
			}
			var inputWriter = new FileWriter(input.getAbsolutePath());
//			var inputWriter = new FileWriter("./" + input.getAbsolutePath());
			inputWriter.write(content.toString());
			inputWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred:");
			e.printStackTrace();
		}
	}

//	/**
//	 * To represent {@link #way(Field)}
//	 * in rawly visual form on a display{@link #<GroProOnlinestrategiespiel.Demo>}.
//	 */
//	@Override //TODO add new frame to employ in the concert input display class!
//	public void render() {
////		setDefaultCloseOperation(EXIT_ON_CLOSE);
////		make boundaries to build the field
//		String field_name = JOptionPane.showInputDialog("Enter the field's name");
//		int lower_bound = Integer.parseInt(JOptionPane.showInputDialog("Enter lower bound of the field"));
//		int upper_bound = Integer.parseInt(JOptionPane.showInputDialog("Enter upper bound of the field"));
//		int weight_size = Integer.parseInt(JOptionPane.showInputDialog("Enter the length of the corresponding" +
//				" weight of each cell in the field.Notice this must be equal to the sum of the field which " +
//				"you have above bounded. Violation of this will cause an exceptions and leads to break the program."));
//		int[] weights = new int[weight_size];
//		for (int i = 0; i < weight_size; i++) {
//			weights[i] = ThreadLocalRandom.current().nextInt();
//		}
//
//
//		JLabel label = new JLabel("Input Description");
//		label.setOpaque(true);
//		label.setBackground(new Color(235, 220, 126));
//		label.setFont(new Font("DialogDescription", Font.BOLD, 44));
//		label.setHorizontalAlignment(SwingConstants.CENTER);
//		JPanel panel = new JPanel();
//		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
//		getContentPane().add(panel);
//		panel.add(label);
//
//		setTitle("Input Description");
//		pack();
//		setLocationRelativeTo(null);
//		setVisible(true);
//		label
//	}
}//end InputDescription