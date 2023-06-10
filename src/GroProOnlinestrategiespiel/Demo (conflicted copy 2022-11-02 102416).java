package GroProOnlinestrategiespiel;

import GroProOnlinestrategiespiel.framework.Description;
import GroProOnlinestrategiespiel.framework.DescriptionDecorator;
import GroProOnlinestrategiespiel.framework.DialogDescription;
import GroProOnlinestrategiespiel.framework.InputDescription;
import GroProOnlinestrategiespiel.graph.Field;
import GroProOnlinestrategiespiel.utility.Coordinates;

import javax.swing.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class define input behavior of the {@link DescriptionDecorator}
 * @author Montahaee
 * @version 1.0
 * @created 05-August-2022 10:37:40 PM
 */

public class Demo {

    public static void main(String[] args) {

//       First preparation respectively build field to
        String field_name = JOptionPane.showInputDialog("Enter the field's name");
//		make boundaries to build the field
        int lower_bound = Integer.parseInt(JOptionPane.showInputDialog("Enter lower bound of the field"));
        int upper_bound = Integer.parseInt(JOptionPane.showInputDialog("Enter upper bound of the field"));
        Coordinates boundaries = new Coordinates(lower_bound, upper_bound);

        int weight_size = Integer.parseInt(JOptionPane.showInputDialog("Enter the length of the corresponding" +
                " weight of each cell in the field.Notice this must be equal to the sum of the field which " +
                "you have above bounded. Violation of this will cause an exceptions and leads to break the program."));

        int[] weights = new int[weight_size];
        for (int i = 0; i < weight_size; i++) {
            weights[i] = ThreadLocalRandom.current().nextInt(1,11);
        }
//      specify start and target point regarding the weights.
        int start = Integer.parseInt(JOptionPane.showInputDialog("Enter start point of the movement in the field. " +
                "Notice it cannot be equal or greater than length of weights: " + weights.length));
        int target = Integer.parseInt(JOptionPane.showInputDialog("Enter target point of the movement in the field" +
                "Notice this cannot be less than of the start point: " + start));
        Coordinates direction = new Coordinates(start, target);
        Field field = new Field(boundaries,field_name,weights,direction);

        Description description = new DialogDescription();
        description = new InputDescription(description);
        description.way(field);
        description.render();
    }


}
