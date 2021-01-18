package com.fisa.trains.main;

import java.util.Arrays;

import com.fisa.trains.model.Railroad;
import com.fisa.trains.service.RailRoadService;

public class App {

	public static void main(String[] args) {
		
		Railroad railroad = new Railroad();
		railroad.build();
		
		RailRoadService service = new RailRoadService(railroad);
		
		System.out.println(service.getFormatterDistance(Arrays.asList("A", "B","C")));
		System.out.println(service.getFormatterDistance(Arrays.asList("A", "D")));
		System.out.println(service.getFormatterDistance(Arrays.asList("A", "D","C")));
		System.out.println(service.getFormatterDistance(Arrays.asList("A", "E","B","C","D")));
		System.out.println(service.getFormatterDistance(Arrays.asList("A", "E","D")));
		
		System.out.println(service.numberOfTripsMaxStops("C", "C", 3));
		
		System.out.println(service.numberOfTripsExacltyStops("A", "C", 4));
		
		System.out.println(service.lengthShortestRoute("A", "C"));
		System.out.println(service.lengthShortestRoute("B", "B"));
		
		System.out.println(service.numberDifferenRoutes("C", "C", 30));
	}

}
