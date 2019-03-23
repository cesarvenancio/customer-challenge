package com.challenge.customer.service;

public class DistanceCalcService {

	/**
     * Equatorial earth radius in Kilometers (KM)
     */
    private static final int EARTH_RADIUS = 6371;

    /**
     * <p>
     * Method using the Haversine formula to calculate the great-circle distance
     * between tow points by the latitude and longitude coordinates.</p>
     *
     * @param startLati Initial latitude
     * @param longitudeLocationA Initial longitude
     * @param latitudeB Final latitude
     * @param longitudeB Final longitude
     * @return The distance in Kilometers
     */
    public static double distanceInKilometers(double latitudeLocationA, double longitudeLocationA, 
    		double latitudeB, double longitudeB) {

        double differenceLatitude = Math.toRadians(latitudeB - latitudeLocationA);
        double differenceLongitude = Math.toRadians(longitudeB - longitudeLocationA);
        double radiusStartLati = Math.toRadians(latitudeLocationA);
        double radiusEndLati = Math.toRadians(latitudeB);

		double a = Math.pow(Math.sin(differenceLatitude / 2), 2) +
				   Math.pow(Math.sin(differenceLongitude / 2), 2) * 
				   Math.cos(radiusStartLati) * Math.cos(radiusEndLati);

        return EARTH_RADIUS * 2 * Math.asin(Math.sqrt(a));
    }
    
    public static boolean isDistanceLessOrEqualsThan(double distance, double distanceExpected) {
		if (distance <= distanceExpected) {
			return true;
		}

		return false;
	}

}
