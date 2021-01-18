package com.fisa.trains.model;

public class Edge {

    private Town origin;
    private Town destination;
    private double distance;
 
    public Edge(Town origin, Town destination, double distance) {
        this.origin = origin;
        this.destination = destination;
        this.distance = distance;
    }

	public Town getOrigin() {
		return origin;
	}

	public void setOrigin(Town origin) {
		this.origin = origin;
	}

	public Town getDestination() {
		return destination;
	}

	public void setDestination(Town destination) {
		this.destination = destination;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}
}
