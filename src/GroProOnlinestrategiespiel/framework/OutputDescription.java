package GroProOnlinestrategiespiel.framework;

import GroProOnlinestrategiespiel.graph.Field;
import GroProOnlinestrategiespiel.graph.SimpelMaze;
import GroProOnlinestrategiespiel.graph.Store;
import GroProOnlinestrategiespiel.utility.Arithmetic;
import GroProOnlinestrategiespiel.utility.Coordinates;
import GroProOnlinestrategiespiel.utility.Shape;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class define output behavior of the {@link DescriptionDecorator}
 * @author Montahaee
 * @version 1.0
 * @created 26-July-2022 12:37:40 PM
 */
public class OutputDescription extends DescriptionDecorator {

    public OutputDescription(Description description) {
        super(description);
    }

    /**
     * @param field is the available place for traversing
     */
    @Override
    public void way(Field field) {

        Coordinates boundaries = field.getBoundaries();
        Coordinates start = field.getStart();
        Coordinates end = field.getTarget();
        int[] weights = field.getWeights();
        StringBuilder content = new StringBuilder();
        content.append(field.getPlace());
        content.append("with ");
        content.append(boundaries.getRow());
        content.append(" X ");
        content.append(boundaries.getColumn());
        content.append("rows\n");
        content.append("start element: ");

        content.append(start.toString());
        content.append("\n");
        content.append("target element: ");
        content.append(end.toString());
        content.append("\n");
        content.append("estimation of the maximum timescale: ");
        content.append(estimate_maxCoast(weights,boundaries,start,end));
        content.append("\n");
        int[][] fmz = field.mazeMatrix();
        SimpelMaze sm = new SimpelMaze(fmz);
        Shape<Integer, Coordinates> way_shape = sm.bestWay(fmz.length, fmz[0].length,new Store());
        content.append("minimal time cost: ");
//        int mtc = way_shape.cumulative_sum((Integer e1,Integer e2)-> e1 +e2)
        int mtc = way_shape.cumulative_sum(new Arithmetic<>() {
            @Override
            public Integer none() {
                return 0;
            }

            @Override
            public Integer add(Integer ls, Integer rs) {
                return ls + rs;
            }
        });
        content.append(mtc);
        content.append("\n");
        content.append("The corresponding path: ");
        content.append("S, ");
        content.append("Corresponding path difficulty is successively shown as below:\n");
        content.append(way_shape.getContents());
        content.append("\n");
        content.append(way_shape.getPositions());
        content.append("T");

        try {
            var input = new File("rawInfo.txt");
            if (input.createNewFile()) {
                System.out.println("input File create: " + input.getName());
            } else {
                System.out.println("File already exists.");
            }
            var inputWriter = new FileWriter("./" + input.getName());
            inputWriter.write(content.toString());
            inputWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
///
///    /**
//     * To represent {@link #way(Field)}
//     * in visual form on a display{@link #<GroProOnlinestrategiespiel.Demo>}.
//     */
///    @Override
///    public void render() {
//
//    }

    private int estimate_maxCoast(int[] weights, Coordinates size, Coordinates from, Coordinates to) {

        int estimated_distance = 0;
//      Top down movement
        for (int i= from.getRow() ; i < to.getRow(); i++) {
            estimated_distance += weights[i * size.getRow() + from.getColumn()];
        }
//      Now left movement to reach the target's wall.
        for (int i = from.getColumn() + 1; i < to.getColumn(); i++) {
            estimated_distance += weights[(to.getRow() - 1)* size.getRow() + i];
        }
        return estimated_distance;
    }
}
