package com.challenge.customer.service;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DistanceCalcService {

	/**
	 * Earth radius in Kilometers (KM)
	 */
	private static final int EARTH_RADIUS = 6371;

	/**
	 * Method using the Haversine formula to calculate the great-circle distance
	 *
	 * @param latitudeLocationA  first latitude
	 * @param longitudeLocationA first longitude
	 * @param latitudeLocationB  second latitude
	 * @param longitudeLocationB second longitude
	 * @return the distance in kilometers
	 */
	public static double distanceInKilometers(double latitudeLocationA, double longitudeLocationA,
			double latitudeLocationB, double longitudeLocationB) {

		double differenceLatitude = Math.toRadians(latitudeLocationB - latitudeLocationA);
		double differenceLongitude = Math.toRadians(longitudeLocationB - longitudeLocationA);
		double radiusStartLati = Math.toRadians(latitudeLocationA);
		double radiusEndLati = Math.toRadians(latitudeLocationB);

		double a = Math.pow(Math.sin(differenceLatitude / 2), 2)
				+ Math.pow(Math.sin(differenceLongitude / 2), 2) * Math.cos(radiusStartLati) * Math.cos(radiusEndLati);

		return EARTH_RADIUS * 2 * Math.asin(Math.sqrt(a));
	}

	public static boolean isDistanceLessOrEqualsThan(double distance, double distanceExpected) {
		return distance <= distanceExpected;
	}

}
