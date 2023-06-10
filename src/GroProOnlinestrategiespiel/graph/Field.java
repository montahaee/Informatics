package GroProOnlinestrategiespiel.graph;


import GroProOnlinestrategiespiel.utility.Coordinates;

/**
 * {@link #direction} is graph's view for start and end in the feld.
 * @author Montahaee
 * @version 1.0
 * @created 26-July-2022 12:37:40 PM
 */
public class Field {

    /**
     * boundaries show the size restriction of the field.
     */
    private final Coordinates boundaries;
    /**
     * distance show only 1d position of start and
     * target with respect to {@link #weights}.
     */
    private final Coordinates direction;
    private final String place;
    private final int[] weights;

    /**
     * @param thatBoundaries is 2d length of the field .
     * @param thatPlace      is the name of the field area.
     * @param thatWeights    is the timescale difficulty to reach
     *                       next element inside the field.
     *
     */
    public Field (Coordinates thatBoundaries, String thatPlace, int[] thatWeights, Coordinates thatDirection) {

//		Check if the length of the weight array matches with the size of squared array way.
//        if (thatWeights.length != ((thatBoundaries.getRow() - 1) * thatBoundaries.getColumn()) +
//                                   (thatBoundaries.getColumn() - 1) * thatBoundaries.getRow()) {
//            throw new IllegalArgumentException("the size of the area is not" +
//                    " agreed with the size of its weight!");
//        }
//		Check if the length of the weight array is fit with the size of squared array way minus start-end.
        if (thatWeights.length != (thatBoundaries.getRow() * thatBoundaries.getColumn())) {
            throw new IllegalArgumentException("the size of the area is not" +
                    " agreed with the size of its weight!");
        }

        if (thatDirection.getRow() >= thatDirection.getColumn()) {
            throw new IllegalArgumentException("A start vertex is always less than end vertex!");
        }
//      Be sure that the start and end point are bounded into the field.
        if (thatDirection.getRow() >= thatBoundaries.getRow() * thatBoundaries.getColumn()    ||
            thatDirection.getColumn() > thatBoundaries.getRow() * thatBoundaries.getColumn()) {
            throw new IllegalArgumentException("The start and End vertex must be bounded in the field!");
        }
//      Be sure that the field size is positive and less than or equal 20 X 20
        if (!(thatBoundaries.getRow() > 0 && thatBoundaries.getColumn() > 0) ||
            !(thatBoundaries.getRow() <= 20 && thatBoundaries.getColumn() <= 20)) {
            throw new IllegalArgumentException("the boundaries are restricted between 1 and 20!");
        }
//
//        if (getStart().getColumn() > getTarget().getColumn()) {
//            throw new IllegalArgumentException("The position of start and end point " +
//                    "cannot be fit for a representation of an anticlockwise movement as it required");
//        }
//               if (!(thatBoundaries.getRow() <=20  && thatBoundaries.getColumn() <=20)) {
//            throw new IllegalArgumentException("the boundaries "
//                    " which is ");
//        }
        this.boundaries = thatBoundaries;
        this.place = thatPlace;
        this.weights = thatWeights;
        this.direction = thatDirection;

        if (getStart().getColumn() > getTarget().getColumn()) {
            throw new IllegalArgumentException("The position of start and end point " +
                    "cannot be fit for a represent of an anticlockwise movement as it required");
        }
    }

    /**
     *
     * @return the 2d upper boundaries
     */
    public Coordinates getBoundaries() {
        return this.boundaries;
    }

    /**
     *
     * @return the name of the field
     */
    public String getPlace() {
        return this.place;
    }

    /**
     *
     * @return the potential wight to reach the next element.
     */
    public int[] getWeights() {
        return this.weights;
    }

    /**
     *
     * @return only start and end point in 1d view
     */
    public Coordinates getDirection() {
        return direction;
    }

    /**
     *
     * @return 2d position of the first point
     */
    public Coordinates getStart() {
        int row  = (direction.getRow()/boundaries.getColumn()) + 1;
        int column = (row * boundaries.getColumn()) % direction.getRow();
        return new Coordinates(row, column);
    }

    /**
     *
     * @return 2d position of the end point
     */
     public Coordinates getTarget() {
        int row  = (direction.getColumn()/boundaries.getColumn()) + 1;
        int column = (row * boundaries.getColumn()) % direction.getColumn();
        return new Coordinates(row, column);
    }

    /**
     *
     * @return skew wighted adjacent matrix(the diagonal element
     * unlike adjacent isn't senn as loop in the field)
     */
    public int[][] matrixField() {
         int[][] matrix = new int[boundaries.getRow()][boundaries.getColumn()];
         for (int k = 0; k < weights.length; k++) {
             matrix[k/4][k%4]= weights[k];
         }
        return matrix;
    }

    /**
     *
     * @return the maze field inside the origin field.
     */
    public int[][] mazeMatrix() {
        int[][] field = matrixField();
        Coordinates s = getStart();
        Coordinates t = getTarget();
        int row = t.getRow() - s.getRow();
        int column = t.getColumn() - s.getColumn();
        int[][] maze = new int[row][column];
        for (int i = 0; i < row; i++) {
            System.arraycopy(field[s.getRow() + i], s.getColumn(), maze[i], 0, column);
///            for (int j = 0; j < column; j++) {
//                maze[i][j] = field[s.getRow() +i][s.getColumn() + j];
//            }
        }
        return maze;
    }
}
