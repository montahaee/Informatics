package SensorDataStorage;


import java.sql.Array;
import java.util.*;

/**
 * @author Montahaee
 * @version 1.0
 * @created 24-Oct-2022 05:40:31 PM
 */
public class SensorDataList {

	private final List<SensorData> data_list;
	private String sensor_type;


	/**
	 * 
	 * @param other_type new {@link #sensor_type}
	 * @param new_data as {@link SensorData}
	 */
	public SensorDataList(String other_type, SensorData new_data){
		this.data_list = new ArrayList<>();
		this.data_list.add(new_data);
		this.sensor_type = other_type;
	}

	/**
	 *
	 * @return upper quartile which splits off the highest 25%
	 * of data from the lowest 75%.
	 */
	public double third_quartile(){
		int n = data_list.size();
		if (n % 4 == 0) {
			return data_list.get((3*n)/4 - 1).get_measured_val();
		}
		else {
			return data_list.get((3*n)/4).get_measured_val();
		}
	}

	/**
	 *
	 * @return the empirical standard division of measured values
	 * recorded from sensors in the {@link #data_list}.
	 */
	public double emp_std(){
		double mv = this.meanVal();
		double sum = data_list.stream().mapToDouble(e -> Math.pow(e.get_measured_val() - mv, 2)).sum();
//		double sum = data_list.stream().map(SensorData::get_measured_val).reduce(0.0,
//				(v1, v2) -> (v1 - mv) + (v2 - mv));
		return Math.sqrt(sum/(data_list.size() - 1));
	}

	protected List<SensorData> getData_list(){
		return data_list;
	}

	protected String getSensor_type(){
		return sensor_type;
	}

	/**
	 *
	 * @return the max measured value between all {@link SensorData}s
	 * in the {@link #data_list}.
	 */
	public SensorData max(){
//		alternative : this.sort(); return this.data_list.get(data_list.size() -1);
		return data_list.stream().max((e1, e2)-> Double.compare(
				e1.get_measured_val(), e2.get_measured_val())).get();
	}

	/**
	 *
	 * @return the value separating the higher half from the lower half.
	 */
	public double median(){
		this.sort();
		int n = data_list.size();
		if (n % 2 == 1) {
			return data_list.get(n/2).get_measured_val();
		}
		else {
			return (data_list.get(n/2).get_measured_val() +
					data_list.get((n/2) - 1).get_measured_val())/2;
		}
	}


	/**
	 *
	 * @return the max measured value between all {@link SensorData}s
	 * in the {@link #data_list}.
	 */
	public SensorData min(){
//				alternative : this.sort(); return this.data_list.get(0);
		return data_list.stream().min((e1, e2)-> Double.compare(
				e1.get_measured_val(), e2.get_measured_val())).get();


	}

	/**
	 * 
	 * @param sensorData  which inis replaced by the newSensorData.
	 * @param newSensorData as position of the {@link #<SensorDataList>}
	 */
	protected void setSensorData(SensorData sensorData, SensorData newSensorData){
		
		data_list.set(data_list.indexOf(sensorData), newSensorData);
	}

	/**
	 * 
	 * @param newType of the {@link Sensor}
	 */
	protected void setSensor_type(String newType){
		sensor_type = newType;
	}

	/**
	 * 
	 * @param expected_val of the total measurements.
	 */
	public double variance(double expected_val){
		double sum = data_list.stream().mapToDouble(e -> (Math.pow (e.get_measured_val() -expected_val, 2))).sum();
//		double sum = data_list.stream().mapToDouble(e -> ((e.get_measured_val() -expected_val) * (e.get_measured_val() -expected_val))).sum();
//				reduce(
//				0.0, (v1, v2) -> (v1 - expected_val) + (v2 - expected_val));

		return sum/data_list.size();
	}

	private double meanVal() {
		return data_list.stream().mapToDouble(SensorData::get_measured_val).sum()/ data_list.size();
//		return data_list.stream().map(SensorData::get_measured_val).reduce(
//				Double::sum).get()/data_list.size();
	}

	private void sort() {
		data_list.sort(((e1, e2) ->Double.compare(e1.get_measured_val(),e2.get_measured_val())));
	}
}//end SensorDataList