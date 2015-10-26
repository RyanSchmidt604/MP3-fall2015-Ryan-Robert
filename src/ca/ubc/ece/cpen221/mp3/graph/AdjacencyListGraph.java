package ca.ubc.ece.cpen221.mp3.graph;

import java.util.*;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class AdjacencyListGraph implements Graph {
	private ArrayList<Vertex> vertexList = new ArrayList<>();
	private ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();

	/**
	 * Adds a Vertex to the graph
	 * 
	 * @param v
	 *            A vertex that is not already in the graph
	 */

	@Override
	public void addVertex(Vertex v) {
		if (vertexList.contains(v)) {
			throw new IllegalArgumentException();
		} else {
			vertexList.add(v);
			adjList.add(new ArrayList<>());
		}

	}

	/**
	 * Adds a directed edge to the graph
	 * 
	 * @param v1
	 *            A vertex in the graph
	 * @param v2
	 *            A vertex in the graph
	 */
	@Override
	public void addEdge(Vertex v1, Vertex v2) {
		int index1 = vertexList.indexOf(v1);
		int index2 = vertexList.indexOf(v2);
		adjList.get(index1).add(index2);
	}

	/**
	 * Checks to see if there is a directed edge from v1 to v2
	 * 
	 * @param v1
	 *            A vertex in the graph
	 * @param v2
	 *            A vertex in the graph
	 * @return true if a directed edge exists from v1 to v2, false if no edge
	 *         exits
	 */
	@Override
	public boolean edgeExists(Vertex v1, Vertex v2) {
		int index1 = vertexList.indexOf(v1);
		int index2 = vertexList.indexOf(v2);
		return adjList.get(index1).contains(index2);
	}

	/**
	 * Gets a list containing all the vertices that are downstream to v
	 * 
	 * @param v
	 *            A vertex in the graph
	 * @return A List containing all vertices w such that there is a edge from v
	 *         to w, returns an empty list if no downstream neighbors exist
	 */
	@Override
	public List<Vertex> getDownstreamNeighbors(Vertex v) {
		int index = vertexList.indexOf(v);
		List<Vertex> downStream = new ArrayList<>();
		for (Integer i : adjList.get(index)) {
			downStream.add(vertexList.get(i));
		}
		return downStream;
	}

	/**
	 * Gets a list containing all the vertices that are upstream to v
	 * 
	 * @param v
	 *            A vertex in the graph
	 * @return A List containing all vertices w such that there is a edge from w
	 *         to v, returns an empty list if no upstream neighbors exist
	 */
	@Override
	public List<Vertex> getUpstreamNeighbors(Vertex v) {
		int index = vertexList.indexOf(v);
		List<Vertex> upStream = new ArrayList<>();
		for (List<Integer> i : adjList) {
			for (Integer j : i) {
				if (j.equals(index)) {
					upStream.add(vertexList.get(adjList.indexOf(i)));
				}
			}
		}
		return upStream;
	}

	/**
	 * Get all the vertices in the graph
	 * 
	 * @return A list of all the vertices in the graph, returns an empty list if
	 *         no vertices exits
	 */
	@Override
	public List<Vertex> getVertices() {
		return vertexList;
	}
}
