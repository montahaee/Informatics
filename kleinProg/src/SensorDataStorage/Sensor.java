package SensorDataStorage;


/**
 * @author Montahaee
 * @version 1.0
 * @created 24-Oct-2022 05:40:30 PM
 */
public abstract class Sensor {

	private final String manufacturer;
	private final String model_nr;


	/**
	 * 
	 * @param that_manufacturer of the sensor.
	 * @param that_modelNr		of the sensor.
	 */
	public Sensor(String that_manufacturer, String that_modelNr){
		this.manufacturer = that_manufacturer;
		this.model_nr = that_modelNr;
	}


	protected String get_manufacturer(){
		return this.manufacturer;
	}

	protected String getModel_nr(){
		return model_nr;
	}

	public abstract SensorData measure();

}//end Sensor