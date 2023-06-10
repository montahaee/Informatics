package GroProOnlinestrategiespiel.utility;


import GroProOnlinestrategiespiel.graph.Field;

/**
 * This class will be utilized in the{@link Field} and
 * @see GroProOnlinestrategiespiel.graph.Store and thire corresponding app.
 *
 *
 * @author Montahaee
 * @version 1.0
 * @created 26-July-2022 12:37:40 PM
 */
//public class Coordinates extends Pair<Integer,Integer>{
public class Coordinates {

	private int column;
	private int row;

	public Coordinates(int horizontal, int vertical){
//		super(horizontal, vertical);
		this.row = horizontal;
		this.column = vertical;
	}

	public int getColumn(){
		return this.column;
	}

	public int getRow(){
		return this.row;
	}

	/**
	 *
	 * @param that_column as new column
	 */
	public void setColumn(int that_column) {
		this.column = that_column;
	}


	/**
	 *
	 * @param that_row as new row
	 */
	public void setRow(int that_row) {
		this.row = that_row;
	}

	@Override
	public String toString(){
		return "(" + this.row +
				"/ " + this.column + ")";
	}
}//end Coordinates