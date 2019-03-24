package com.challenge.customer;

import org.junit.Assert;
import org.junit.Test;

import com.challenge.customer.service.DistanceCalcService;

/**
 * Distance Calc Tests
 */
public class DistanceCalcTest {

	private static final double LATITUDE_FIRST = 53.339428;
	private static final double LONGITUDE_FIRST = -6.257664;
	
	private static final double LATITUDE_SECOND = 52.986375;
	private static final double LONGITUDE_SECOND = -6.043701;
	
	private static final double DISTANCE_RESULT = 41.7687255008362;
	
	private DistanceCalcService DistanceCalcService = new DistanceCalcService();
	
	@Test
	public void checkDistanceBetweenTwoPoints() {
		double distance = DistanceCalcService.distanceInKilometers(LATITUDE_FIRST, LONGITUDE_FIRST, 
				LATITUDE_SECOND, LONGITUDE_SECOND);
		
		Assert.assertEquals(DISTANCE_RESULT, distance, 0);
	}
	
	@Test
	public void checkWrongDistanceBetweenTwoPoints() {
		double distance = DistanceCalcService.distanceInKilometers(55.339428, LONGITUDE_SECOND, 
				LATITUDE_SECOND, LONGITUDE_SECOND);
		
		Assert.assertNotEquals(DISTANCE_RESULT, distance, 0);
	}
	
	@Test
	public void distanceLessOrEqualsThan100() {
		Assert.assertTrue(DistanceCalcService.isDistanceLessOrEqualsThan(DISTANCE_RESULT, 100));
	}
	
	@Test
	public void distanceLessOrEqualsThan100False() {
		Assert.assertFalse(DistanceCalcService.isDistanceLessOrEqualsThan(101.7687255008362, 100));
	}
}
