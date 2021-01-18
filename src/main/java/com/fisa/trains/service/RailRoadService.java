package com.fisa.trains.service;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.fisa.exception.RouteNotFoundException;
import com.fisa.trains.model.Edge;
import com.fisa.trains.model.Railroad;
import com.fisa.trains.model.Town;
import com.fisa.trains.util.FormatterUtils;

public class RailRoadService {
	
	private Railroad railroad;
	
	public RailRoadService(Railroad railroad) {
		this.railroad = railroad;
	}

	public String getFormatterDistance(List<String> towns) {
		try {
			
			Double distance = this.getDistance(towns);
			return FormatterUtils.formatDouble(distance, 0);
		
		} catch (RouteNotFoundException e) {
			return e.getMessage();
		}catch (Exception e) {
			return "Internal server error";
		}
	}
	
    public String numberOfTripsMaxStops(String start, String end, int maxStops) {        
        AtomicInteger counter = new AtomicInteger();
        Deque<Edge> queue = new LinkedList<>();
        
        Town townSatart = railroad.getTown(start);
        Town townEnd = railroad.getTown(end);
        
        this.countTripsMaxStops(townSatart, townEnd, maxStops, queue, counter);
        
        return counter.toString();
    }
	
    private void countTripsMaxStops(Town start, Town end, int maxStops, 
            Deque<Edge> queue, AtomicInteger counter) {
        
        if (queue.size() < maxStops) {
            List<Edge> routes = start.getEdges();
            for (Edge edge : routes) {                
                if (edge.getDestination().getCode().equals(end.getCode())) {
                    counter.incrementAndGet();
                }
                queue.addLast(edge);
                countTripsMaxStops(edge.getDestination(), end, maxStops, queue, counter);
                queue.removeLast();
            } 
        }
    }
    
    public String numberOfTripsExacltyStops(String start, String end, int exacltyStops) {
        AtomicInteger counter = new AtomicInteger();
        Deque<Edge> queue = new LinkedList<>();
        
        Town townSatart = railroad.getTown(start);
        Town townEnd = railroad.getTown(end);
        
        this.countTripsExacltyStops(townSatart, townEnd, exacltyStops, queue, counter);
        
        return counter.toString();
    }
    
    private void countTripsExacltyStops(Town start, Town end, int exacltyStops, 
    		Deque<Edge> queue, AtomicInteger counter) {
       
        if (queue.size() < exacltyStops) {
        	List<Edge> routes = start.getEdges();
        	for (Edge edge : routes) {
                if (edge.getDestination().getCode().equals(end.getCode()) && queue.size() == exacltyStops -1) {
                    counter.incrementAndGet();
                }
                queue.addLast(edge);
                countTripsExacltyStops(edge.getDestination(), end, exacltyStops, queue, counter);
                queue.removeLast();

            } 
        }      
    }
    
    public String lengthShortestRoute(String start, String end) {
        ThreadLocal<Double> minDistance = new ThreadLocal<>();
        minDistance.set(999999D);
        int sumDistance = 0;
        Deque<Edge> queue = new LinkedList<>();
        
        Town townSatart = railroad.getTown(start);
        Town townEnd = railroad.getTown(end);
        
        this.calculateShortestRoute(townSatart, townEnd, queue, minDistance, sumDistance);
        
        return FormatterUtils.formatDouble(minDistance.get(), 0);
    }
    
    
    private void calculateShortestRoute(Town start, Town end, Deque<Edge> queue, ThreadLocal<Double> minDistance, int sumDistance) {

            List<Edge> routes = start.getEdges();
            for (Edge route : routes) {

                if (route.getDestination().getCode().equals(end.getCode())) {
                    if (sumDistance + route.getDistance() < minDistance.get()) {
                        minDistance.set(sumDistance + route.getDistance());
                        continue;
                    }
                }
                
                if (!queue.contains(route)) {
                    queue.addLast(route);
                    sumDistance += route.getDistance();
                    calculateShortestRoute(route.getDestination(), end, queue, minDistance, sumDistance);
                    sumDistance -= route.getDistance();
                    queue.removeLast();
                }
            }
    }
    
    public String numberDifferenRoutes(String start, String end, int maxDistance) {
        int sumDistance = 0;
        AtomicInteger counter = new AtomicInteger();
        
        Town townSatart = railroad.getTown(start);
        Town townEnd = railroad.getTown(end);
        
        calculateDifferenRoutes(townSatart, townEnd, maxDistance, counter, sumDistance);
        
        return counter.toString();
    }


    private void calculateDifferenRoutes(Town start, Town end, int maxDistance, 
            AtomicInteger counter, int sumDistance) {
        
    	List<Edge> routes = start.getEdges();
        for (Edge route : routes) {

            if (sumDistance + route.getDistance() >= maxDistance) {
                continue;
            }
            
            if (route.getDestination().getCode().equals(end.getCode())) {
                counter.getAndIncrement();
            }
            
            sumDistance += route.getDistance();
            calculateDifferenRoutes(route.getDestination(), end, maxDistance, counter, sumDistance);
            sumDistance -= route.getDistance();
        }
        
    }
	
	public double getDistance(List<String> towns) throws RouteNotFoundException{
		
		if(towns == null || towns.size() < 2) {
			return 0;
		}
		
		Town before = null;
		Town after = null;
		Double distance = 0D;
		
		for (String currentTown : towns) {
			if(before == null) {
				before = railroad.getTown(currentTown);
			}else {
				after = railroad.getTown(currentTown);
				distance = distance + this.getDistanceDetween(before, after);
				before = after;
			}
		}
		return distance;
	}
	
	private double getDistanceDetween(Town from, Town destination) throws RouteNotFoundException{
		List<Edge> edges = from.getEdges();
		Edge distance = edges.stream().filter(e-> e.getDestination().getCode().equals(destination.getCode())).findFirst().orElse(null);
		
		if(distance != null) {
			return distance.getDistance();
		}else {
			throw new RouteNotFoundException("NO SUCH ROUTE");
		}
	}
}
