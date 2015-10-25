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
		return shortestPathLenght;
	}

	public static Set<List<Vertex>> BFS(Graph g) {
		Set<List<Vertex>> bfsResults = new HashSet<>();
		for (Vertex v : g.getVertices()) {

		}
		return bfsResults;
	}

	public static Set<List<Vertex>> DFS(Graph g) {
		Set<List<Vertex>> dfsResults = new HashSet<>();
		for (Vertex v : g.getVertices()) {
			// TODO Figure out how to do this.
		}
		return dfsResults;
	}

	public static List<Vertex> commonUpstreamVertices(Graph g, Vertex a, Vertex b) {
		List<Vertex> upstreamResults = new ArrayList<>();
		List<Vertex> aVertices = g.getUpstreamNeighbors(a);
		List<Vertex> bVertices = g.getUpstreamNeighbors(b);
		while (!(aVertices.isEmpty() || bVertices.isEmpty())) {
			Vertex aCheck = aVertices.remove(0);
			Vertex bCheck = bVertices.remove(0);
			if (aCheck.equals(bCheck)) {
				upstreamResults.add(aCheck);
			}
		}

		return upstreamResults;
	}

	public static List<Vertex> commonDownstreamVertices(Graph g, Vertex a, Vertex b) {
		List<Vertex> downstreamResults = new ArrayList<>();
		List<Vertex> aVertices = g.getDownstreamNeighbors(a);
		List<Vertex> bVertices = g.getDownstreamNeighbors(b);
		while (!(aVertices.isEmpty() || bVertices.isEmpty())) {
			Vertex aCheck = aVertices.remove(0);
			Vertex bCheck = bVertices.remove(0);
			if (aCheck.equals(bCheck)) {
				downstreamResults.add(aCheck);
			}
		}

		return downstreamResults;
	}
}