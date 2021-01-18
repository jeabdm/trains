package com.fisa.trains.model;

import java.util.ArrayList;
import java.util.List;

public class Town {

	private String code;
	private List<Edge> edges;

	public Town(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public List<Edge> getEdges() {
		return edges;
	}
	
    public void addEdge(Edge edge) {
        if (edges == null) {
            edges = new ArrayList<>();
        }
        edges.add(edge);
    }
	
	
}
