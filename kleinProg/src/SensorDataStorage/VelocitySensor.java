package SensorDataStorage;


/**
 * Velocity_Sensor
 * @author Montahaee
 * @version 1.0
 * @created 24-Oct-2022 05:40:31 PM
 */
public class VelocitySensor extends Sensor {

	private final int manufacture_year;


	/**
	 * 
	 * @param year where the sensor is manufactured for new instantiated {@link #<VelocitySensor>}
	 */
	public VelocitySensor(int year){
		this.manufacture_year = year;
	}

	protected int getManufacture_year(){
		return manufacture_year;
	}

	/**
	 *
	 * @return data which measured by {@link VelocitySensor}
	 */
	public SensorData measure(){
		return null;
	}

	public void velocity(){

	}
}//end VelocitySensor