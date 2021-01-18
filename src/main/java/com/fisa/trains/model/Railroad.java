package com.fisa.trains.model;

import java.util.ArrayList;
import java.util.List;

public class Railroad {

    private List<Town> towns;
    
    public void addTown(Town node) {
        if (towns == null) {
        	towns = new ArrayList<>();
        }
        towns.add(node);
    }
 
    public List<Town> getNodes() {
        return towns;
    }
    
    public Town getTown(String code) {
    	return this.towns.stream().filter(town -> town.getCode().equals(code)).findFirst().orElse(null);
    }
    
    public void build(){
    	Town a = new Town("A");
    	Town b = new Town("B");
    	Town c = new Town("C");
    	Town d = new Town("D");
    	Town e = new Town("E");
    	
    	a.addEdge(new Edge(a,b,5));
    	a.addEdge(new Edge(a,d,5));
    	a.addEdge(new Edge(a,e,7));
    	
    	b.addEdge(new Edge(b,c,4));
    	
    	c.addEdge(new Edge(c,d,8));
    	c.addEdge(new Edge(c,e,2));
    	
    	d.addEdge(new Edge(d,c,8));
    	d.addEdge(new Edge(d,e,6));
    	
    	e.addEdge(new Edge(e,b,3));
    	
    	this.addTown(a);
    	this.addTown(b);
    	this.addTown(c);
    	this.addTown(d);
    	this.addTown(e);
    }
}
