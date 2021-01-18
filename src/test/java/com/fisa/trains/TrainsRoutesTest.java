package com.fisa.trains;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.fisa.trains.model.Railroad;
import com.fisa.trains.service.RailRoadService;

public class TrainsRoutesTest {

	public Railroad railroad = new Railroad();
	public RailRoadService service;
	
	@Before
	public void before() {
		this.railroad.build();
		this.service = new RailRoadService(railroad);
	}
	
	@Test
	public void testRailRoad() {
		assertEquals(this.railroad.getNodes().size(), 5);
	}
	
	@Test
	public void testDistanceOfRoute() {
		assertEquals(service.getFormatterDistance(Arrays.asList("A", "B","C")), "9");
		assertEquals(service.getFormatterDistance(Arrays.asList("A", "D")), "5");
		assertEquals(service.getFormatterDistance(Arrays.asList("A", "D","C")), "13");
		assertEquals(service.getFormatterDistance(Arrays.asList("A", "E","B","C","D")), "22");
		assertEquals(service.getFormatterDistance(Arrays.asList("A", "E","D")), "NO SUCH ROUTE");
	}
	
	@Test
	public void testNumberOfTripsMaxStops() {
		assertEquals(service.numberOfTripsMaxStops("C", "C", 3), "2");
	}
	
	@Test
	public void testNumberOfTripsExacltyStops() {
		assertEquals(service.numberOfTripsExacltyStops("A", "C", 4), "3");
	}
	
	@Test
	public void testLengthShortestRoute() {
		assertEquals(service.lengthShortestRoute("A", "C"), "9");
		assertEquals(service.lengthShortestRoute("B", "B"), "9");
	}
	
	@Test
	public void testNumberDifferenRoutes() {
		assertEquals(service.numberDifferenRoutes("C", "C", 30), "7");
	}
}
