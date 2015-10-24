package ca.ubc.ece.cpen221.mp3.graph;

import java.util.*;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class AdjacencyListGraph implements Graph {
	private ArrayList<Vertex> vertexList = new ArrayList<>();
	private ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();

	@Override
	public void addVertex(Vertex v) {
		vertexList.add(v);
		adjList.add(new ArrayList<>());
	}

	@Override
	public void addEdge(Vertex v1, Vertex v2) {
		int index1 = vertexList.indexOf(v1);
		int index2 = vertexList.indexOf(v2);
		adjList.get(index1).add(index2);
	}

	@Override
	public boolean edgeExists(Vertex v1, Vertex v2) {
		int index1 = vertexList.indexOf(v1);
		int index2 = vertexList.indexOf(v2);
		return adjList.get(index1).contains(index2) || adjList.get(index2).contains(index1);
	}

	@Override
	public List<Vertex> getDownstreamNeighbors(Vertex v) {
		int index = vertexList.indexOf(v);
		List<Vertex> downStream = new ArrayList<>();
		for (Integer i : adjList.get(index)) {
			downStream.add(vertexList.get(i));
		}
		return downStream;
	}

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

	@Override
	public List<Vertex> getVertices() {
		return vertexList;
	}
}
