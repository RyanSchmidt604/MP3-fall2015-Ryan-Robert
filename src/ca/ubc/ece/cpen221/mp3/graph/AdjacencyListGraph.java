package ca.ubc.ece.cpen221.mp3.graph;
import java.util.*;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class AdjacencyListGraph implements Graph {
	private List<Vertex> vertices = new ArrayList<>(); 
	private HashMap<Vertex,Vertex> edges = new HashMap<Vertex,Vertex>();

	@Override
	public void addVertex(Vertex v) {
		vertices.add(v);		
		
		
	}

	@Override
	public void addEdge(Vertex v1, Vertex v2) {
		edges.put(v1, v2);
		
	}

	@Override
	public boolean edgeExists(Vertex v1, Vertex v2) {
		return edges.
	}

	@Override
	public List<Vertex> getDownstreamNeighbors(Vertex v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vertex> getUpstreamNeighbors(Vertex v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vertex> getVertices() {
		// TODO Auto-generated method stub
		return null;
	}
// TODO: Implement this class
}
