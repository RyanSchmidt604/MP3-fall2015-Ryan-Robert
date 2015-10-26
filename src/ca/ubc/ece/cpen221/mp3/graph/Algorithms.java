package ca.ubc.ece.cpen221.mp3.graph;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;
import java.util.*;

public class Algorithms {

	/**
	 * *********************** Algorithms ****************************
	 * 
	 * Please see the README for the machine problem for a more detailed
	 * specification of the behavior of each method that one should implement.
	 */

	/**
	 * This is provided as an example to indicate that this method and other
	 * methods should be implemented here.
	 * 
	 * You should write the specs for this and all other methods.
	 * 
	 * @param graph
	 * @param a
	 * @param b
	 * @return
	 */
	public static int shortestDistance(Graph graph, Vertex a, Vertex b) {
		Map<Vertex, Boolean> visited = new HashMap<>();
		Map<Vertex, Vertex> parent = new HashMap<>();
		Queue<Vertex> vertexQueue = new LinkedList<>();
		Vertex currentVertex = a;
		vertexQueue.add(currentVertex);
		visited.put(currentVertex, true);
		while (!vertexQueue.isEmpty()) {
			currentVertex = vertexQueue.remove();
			if (currentVertex.equals(b)) {
				break;
			} else {
				for (Vertex v : graph.getDownstreamNeighbors(currentVertex)) {
					if (!visited.containsKey(v)) {
						vertexQueue.add(v);
						visited.put(v, true);
						parent.put(v, currentVertex);
					}
				}
			}
		}
		int shortestPathLenght = 0;
		for (Vertex v = b; v != null; v = parent.get(v)) {
			shortestPathLenght++;
		}
		return shortestPathLenght - 1;
	}

	public static Set<List<Vertex>> BFS(Graph g) {
		Set<List<Vertex>> bfsResults = new HashSet<>();

		for (Vertex v : g.getVertices()) {
			List<Vertex> resultsForV = new ArrayList<>();
			Map<Vertex, Boolean> visited = new HashMap<>();
			Map<Vertex, Vertex> parent = new HashMap<>();
			Queue<Vertex> vertexQueue = new LinkedList<>();
			Vertex currentVertex = v;
			visited.put(currentVertex, true);
			vertexQueue.add(currentVertex);
			while (!vertexQueue.isEmpty()) {
				currentVertex = vertexQueue.remove();
				for (Vertex n : g.getDownstreamNeighbors(currentVertex)) {
					if (!visited.containsKey(n)) {
						vertexQueue.add(n);
						visited.put(n, true);
						parent.put(n, currentVertex);
					}

				}
			}
			for (Vertex v1 = v; v1 != null; v1 = parent.get(v1)) {
				resultsForV.add(v1);
			}
			bfsResults.add(resultsForV);
		}
		return bfsResults;
	}

	public static Set<List<Vertex>> DFS(Graph g) {
		Set<List<Vertex>> DFSResults = new HashSet<>();
		for(Vertex v: g.getVertices()){
			DFSResults.add(dfs(g,v));
		}
		return DFSResults;
	}
		

	public static List<Vertex> commonUpstreamVertices(Graph g, Vertex a, Vertex b) {
		List<Vertex> upstreamResults = new ArrayList<>();
		List<Vertex> aVertices = g.getUpstreamNeighbors(a);
		List<Vertex> bVertices = g.getUpstreamNeighbors(b);
		if (aVertices.size() > bVertices.size()) {
			for (Vertex v : aVertices) {
				if (bVertices.contains(v)) {
					upstreamResults.add(v);
				}
			}
		} else {
			for (Vertex w : bVertices) {
				if (aVertices.contains(w)) {
					upstreamResults.add(w);
				}
			}
		}
		return upstreamResults;
	}

	public static List<Vertex> commonDownstreamVertices(Graph g, Vertex a, Vertex b) {
		List<Vertex> downstreamResults = new ArrayList<>();
		List<Vertex> aVertices = g.getDownstreamNeighbors(a);
		List<Vertex> bVertices = g.getDownstreamNeighbors(b);
		if (aVertices.size() > bVertices.size()) {
			for (Vertex v : aVertices) {
				if (bVertices.contains(v)) {
					downstreamResults.add(v);
				}
			}
		} else {
			for (Vertex w : bVertices) {
				if (aVertices.contains(w)) {
					downstreamResults.add(w);
				}
			}
		}

		return downstreamResults;
	}
	
	private static List<Vertex> dfs(Graph g, Vertex v){
		List<Vertex> dfsResults = new ArrayList<>();
		dfsResults.add(v);
		for(Vertex w: g.getDownstreamNeighbors(v)){
			if(dfsResults.contains(w)){
				dfs(g,w);
			}
		}
		return dfsResults;
	}
}