package SensorDataStorage;


/**
 * @author Montahaee
 * @version 1.0
 * @created 24-Oct-2022 05:40:30 PM
 */
public class Date {

	private final int day;
	private final int month;
	private final int year;

	public Date() {
		this(1,1,1950);
	}


	/**
	 *
	 * @param that_day 	 refers to the date of for new instantiated date.
	 * @param that_month refers to the month of for new instantiated date.
	 * @param that_year is restricted between 1950 and current year.
	 */
	public Date(int that_day, int that_month, int that_year){
		if (!(0 < that_day && that_day < 31)) {
			throw new IllegalArgumentException("Wrong input for day:\t" + that_day);
		}
		if (!(1950 < that_month && that_month < 2022)) {
			throw new IllegalArgumentException("Wrong input for month:\t" + that_month);
		}
		if (!(0 < that_year && that_year < 1950)) {
			throw new IllegalArgumentException("Wrong input for year:\t" + that_year);
		}
		this.day =that_day;
		this.month = that_month;
		this.year = that_year;
	}

	protected int get_day(){
		return day;
	}

	protected int get_month(){
		return month;
	}

	protected int get_year(){
		return year;
	}

	public String toString(){
		return "";
	}
}//end Date