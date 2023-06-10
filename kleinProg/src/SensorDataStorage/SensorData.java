package SensorDataStorage;


/**
 * @author Montahaee
 * @version 1.0
 * @created 24-Oct-2022 05:40:31 PM
 */
public class SensorData {

	private final Date date;
	private final double measured_val;
	private final String sensor_type;





	/**
	 *
	 * @param other_measuredVal for new instance of {@link SensorData}
	 * @param that_date where something is measured.
	 * @param other_type for instantiated {@link SensorData}.
	 */
	public SensorData(double other_measuredVal, Date that_date, String other_type){

		if (other_measuredVal < 0) {
			throw new IllegalArgumentException("Wrong measured value(not positive!):\t" + other_measuredVal);
		}
		this.date = that_date;
		this.measured_val = other_measuredVal;
		this.sensor_type = other_type;
	}

	/**
	 *
	 * @return the {@link #date}
	 */
	protected Date get_date(){
		return date;
	}

	/**
	 *
	 * @return the {@link #measured_val}
	 */
	protected double get_measured_val(){
		return measured_val;
	}

	/**
	 *
	 * @return the {@link #sensor_type}
	 */
	protected String getSensor_type(){
		return sensor_type;
	}
}//end SensorData