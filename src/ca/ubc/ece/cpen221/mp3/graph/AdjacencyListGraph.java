package ca.ubc.ece.cpen221.mp3.graph;

import java.util.*;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

/**
 * 
 * @author Robert Drinnan and Ryan Schmidt
 *
 */

public class AdjacencyListGraph implements Graph {
	private ArrayList<Vertex> vertexList = new ArrayList<>();
	private ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();

	@Override
	public void addVertex(Vertex v) {

		vertexList.add(v); 
		adjList.add(new ArrayList<>()); //Creates a new list in the adjacency list at the position of the vertex in vertexList 

	}

	@Override
	public void addEdge(Vertex v1, Vertex v2) {
		int index1 = vertexList.indexOf(v1);
		int index2 = vertexList.indexOf(v2);
		adjList.get(index1).add(index2); //Adds the index of the child vertex to the list corresponding to the parent veretex 
	}

	@Override
	public boolean edgeExists(Vertex v1, Vertex v2) {
		int index1 = vertexList.indexOf(v1);
		int index2 = vertexList.indexOf(v2);
		return adjList.get(index1).contains(index2);  //Checks if the index a v2 is in the list corresponding to v2
	}

	@Override
	public List<Vertex> getDownstreamNeighbors(Vertex v) {
		int index = vertexList.indexOf(v);
		List<Vertex> downStream = new ArrayList<>();
		for (Integer i : adjList.get(index)) { //Find all the indices in the adjList corresponding to v
			downStream.add(vertexList.get(i));  // Adds the Vertices that correspond to the indices to the list in downstream neighbors
		}
		return downStream;
	}

	@Override
	public List<Vertex> getUpstreamNeighbors(Vertex v) {
		int index = vertexList.indexOf(v);
		List<Vertex> upStream = new ArrayList<>();
		for (List<Integer> i : adjList) {  //iterates through all the indices in the adjlist
			for (Integer j : i) {
				if (j.equals(index)) {
					upStream.add(vertexList.get(adjList.indexOf(i))); //If it finds the index for v it it add its parent to the Upstream neighbors list
				}
			}
		}
		return upStream;
	}

	@Override
	public List<Vertex> getVertices() {
		return vertexList; //Return the internal list of vertices
	}
}
