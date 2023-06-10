package Cartography.framework;


/**
 * @author Montahaee
 * @version 1.0
 * @created 23-Aug-2022 4:14:47 PM
 */
public class Orbit {

	private int characteristic;
	/**
	 * Radius of the earth in km
	 */
	private final double R = 6399.137;
	private final double latitude;
	private final double longitude;

	public Orbit(){
		this(0,0,0);
	}

	/**
	 * 
	 * @param that_characteristic represent a distinguishing feature.
	 * @param that_latitude represents the north-south position on the surface of the earth
	 * @param that_longitude represents the east-west position on the surface of the earth
	 */
	public Orbit(int that_characteristic, double that_latitude, double that_longitude){

		this.characteristic = that_characteristic;
		this.latitude = that_latitude;
		this.longitude = that_longitude;
	}

	public int getCharacteristic(){
		return characteristic;
	}

	/**
	 *
	 * @return the current {@link #latitude}
	 */
	public double getLatitude(){
		return this.latitude;
	}

	/**
	 *
	 * @return the current {@link #longitude}
	 */
	public double getLongitude(){
		return longitude;
	}

	/**
	 *
	 * @param newCharacteristic is depended on the #
	 */
	public void setCharacteristic(int newCharacteristic) {
		this.characteristic = newCharacteristic;
	}

	/**
	 *
	 * @return horizontal cartesian position
	 */
	public double getX(){
		return 0;
	}

	/**
	 *
	 * @return vertical cartesian position
	 */
	public double getY(){
		return 0;
	}

	/**
	 *
	 * @return the corresponding radius w.r.t. current{@link #characteristic}.
	 */
	public double radius(){
		return Math.sqrt(this.characteristic/Math.PI);
	}

	/**
	 * The following methode is based on the 'Haversine formula':
	 * <a href="https://en.wikipedia.org/wiki/Haversine_formula">'Haversine formula'</a>
	 * @param lat is the second latitude.
	 * @param lon is the second longitude.
	 * @return teh distance between tow geodesic coordinates.
	 */
	public double getDistance(double lat, double lon) {
//		double d_lat = deg2rad(lat - this.latitude);
		double d_lat = deg2rad(this.latitude - lat);
//		double d_lon = deg2rad(lon - this.longitude);
		double d_lon = deg2rad(this.longitude - lon);
		double arc = Math.sin(d_lat/2) * Math.sin(d_lat/2) + Math.cos(deg2rad(this.latitude)) *
				Math.cos(deg2rad(lat)) * Math.sin(d_lon/2) * Math.sin(d_lon/2);
		double theta = 2 * Math.atan2(Math.sqrt(arc), Math.sqrt(1 - arc));
		double beta  = 2* Math.asin(Math.sqrt( arc));
//		return R * theta; // Distance in km
//		return 61.6553056717896603 * theta; // Distance in km
//		return 61.6553056717896603 * 2 * Math.sqrt(2) * beta; // Distance in km
//		return R * beta; // Distance in km
//		return radius() * beta; // Distance in km
//		return beta; // Distance in km
//		return beta; // Distance in km
		return radius() * theta; // Distance in km
	}

	@Override
	public String toString() {
		return String.format("%8d%9.1f%9.1f",this.characteristic, this.longitude,
				this.latitude);
	}



	/**
	 *
	 * @param angle is based on degree
	 * @return corresponding radian to {@param angle}
	 */
	private double deg2rad(double angle) {
		return angle*(Math.PI/180);
	}

}//end Orbit