package SensorDataStorage;


/**
 * @author Montahaee
 * @version 1.0
 * @created 24-Oct-2022 05:40:30 PM
 */
public class BatterySensor extends Sensor {


	/**
	 * @param that_manufacturer of the sensor.
	 * @param that_modelNr      of the sensor.
	 */
	public BatterySensor(String that_manufacturer, String that_modelNr) {
		super(that_manufacturer, that_modelNr);
	}

	/**
	 *
	 * @return data which measured by {@link BatterySensor}
	 */
	public SensorData measure(){
		return null;
	}
}//end BatterySensor