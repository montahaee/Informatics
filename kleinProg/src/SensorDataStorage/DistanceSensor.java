package SensorDataStorage;
/**
 * @author Montahaee
 * @version 1.0
 * @created 24-Oct-2022 05:40:30 PM
 */
public class DistanceSensor  extends Sensor {

	public DistanceSensor (String manufacture, String modelNr){
		super(manufacture,modelNr);

	}

	/**
	 *
	 * @return data which measured by {@link DistanceSensor}
	 */
	public SensorData measure(){
		return null;
	}
}//end DistanceSensor 