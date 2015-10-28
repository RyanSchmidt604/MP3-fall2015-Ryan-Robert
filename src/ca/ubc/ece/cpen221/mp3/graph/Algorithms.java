package ca.ubc.ece.cpen221.mp3.graph;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;
import java.util.*;

/**
 * 
 * @author Robert Drinnan and Ryan Schimdt.
 *
 */

public class Algorithms {

	/**
	 * 
	 * @param graph
	 *            An unweighted graph
	 * @param a
	 *            A vertex in the graph graph
	 * @param b
	 *            A vertex in the graph graph
	 * @return The number of edges in the shortest path between a and b
	 */
	public static int shortestDistance(Graph graph, Vertex a, Vertex b) {
		Map<Vertex, Boolean> visited = new HashMap<>();
		Map<Vertex, Vertex> parent = new HashMap<>();
		Queue<Vertex> vertexQueue = new LinkedList<>();
		Vertex currentVertex = a;
		vertexQueue.add(currentVertex); //start the process by adding the staring vertex to the queue
		visited.put(currentVertex, true); //Set the staring vertex to visited
		while (!vertexQueue.isEmpty()) { //iterates while there are still vertices to be analyzed
			currentVertex = vertexQueue.remove();
			if (currentVertex.equals(b)) {
				break; //break the cycle as soon as the end destination is reached
			} else {
				for (Vertex v : graph.getDownstreamNeighbors(currentVertex)) { //for each downstream neighbor to the current vertex
					if (!visited.containsKey(v)) {
						vertexQueue.add(v);  //add for future analysis
						visited.put(v, true); //State that it has been visited
						parent.put(v, currentVertex); //record it's parent
					}
				}
			}
		}
		int shortestPathLenght = 0;
		for (Vertex v = b; v != null; v = parent.get(v)) { //Iterate through all the vertices in the parent map
			shortestPathLenght++; //Add one to the shortest path for each one
		}
		return shortestPathLenght - 1; //return the shortest path minus one as we don't count the original vertex
	}

	/**
	 * 
	 * @param g
	 *            An unweighted graph
	 * @return A set all the possible BFS searches staring at every vertex in
	 *         graph
	 * 
	 */
	public static Set<List<Vertex>> BFS(Graph g) {
		Set<List<Vertex>> bfsResults = new HashSet<>();
		for (Vertex v : g.getVertices()) { //Iterates through all the vertices in the graph
			Queue<Vertex> vertexQueue = new LinkedList<>();
			List<Vertex> visited = new LinkedList<>();
			visited.add(v); //set the staring vertex to visited
			vertexQueue.add(v);
			while (!(vertexQueue.isEmpty())) { //Keep searching for vertices until the there are no more unvisited vertices
				Vertex currentVertex = vertexQueue.poll();
				for (Vertex w : g.getDownstreamNeighbors(currentVertex)) {
					if (!(visited.contains(w))) { //if it has been visited, ignore
						visited.add(w); //add it to the found vertices list
						vertexQueue.add(w);
					}
				}
			}
			bfsResults.add(visited); //add the visited list to the set
		}
		return bfsResults;

	}

	/**
	 * 
	 * @param g
	 *            An unweighted graph
	 * @return A set of all possible DFS searches starting at every vertex in
	 *         graph
	 */
	public static Set<List<Vertex>> DFS(Graph g) {
		Set<List<Vertex>> dfsResults = new HashSet<>();
		for (Vertex v : g.getVertices()) { //iterate through all the vertices in the graph
			Stack<Vertex> vertexStack = new Stack<>();
			List<Vertex> visited = new LinkedList<>();
			vertexStack.push(v);  //push the vertex onto the stack  first
			while (!(vertexStack.isEmpty())) { //Keep searching while there are still vertices in the stack
				Vertex currentVertex = vertexStack.pop();
				if (!(visited.contains(currentVertex))) {
					visited.add(currentVertex); //if it has not been visited mark it as visited
				}
				for (Vertex w : g.getDownstreamNeighbors(currentVertex)) {
					vertexStack.add(w);  //add the new vertices to the stack
				}
			}
			dfsResults.add(visited);  //add the visited list to the set
		}
		return dfsResults;

	}

	/**
	 * 
	 * @param g
	 *            an unweighted graph
	 * @param a
	 *            A vertex in the g
	 * @param b
	 *            A vertex in the g
	 * @return returns a List of all vertices w that have an edges that goes
	 *         from w to v, returns an empty list if no vertex w exist.
	 */
	public static List<Vertex> commonUpstreamVertices(Graph g, Vertex a, Vertex b) {
		List<Vertex> upstreamResults = new ArrayList<>();
		List<Vertex> aVertices = g.getUpstreamNeighbors(a);
		List<Vertex> bVertices = g.getUpstreamNeighbors(b);
		if (aVertices.size() > bVertices.size()) { //Finds the vertex with the bigger list of upstream neighbors
			for (Vertex v : aVertices) {
				if (bVertices.contains(v)) {
					upstreamResults.add(v); 
				}
			}//If if was bigger check to see if any of neighbors of the bigger one is in the list of neighbors of the smaller ones, add all matches to the list of results
		} else {
			for (Vertex w : bVertices) {
				if (aVertices.contains(w)) {
					upstreamResults.add(w);
				}
			}
		}
		return upstreamResults;
	}

	/**
	 * 
	 * @param g
	 *            An unweighted graph
	 * @param a
	 *            A vertex in g
	 * @param b
	 *            A vertex in g
	 * @return returns a List of all vertices w that have an edges that goes
	 *         from v to w, returns an empty list if no vertex w exist.
	 */
	public static List<Vertex> commonDownstreamVertices(Graph g, Vertex a, Vertex b) {
		List<Vertex> downstreamResults = new ArrayList<>();
		List<Vertex> aVertices = g.getDownstreamNeighbors(a);
		List<Vertex> bVertices = g.getDownstreamNeighbors(b);
		if (aVertices.size() > bVertices.size()) { //Check with one has more downstream neighbors
			for (Vertex v : aVertices) {
				if (bVertices.contains(v)) {
					downstreamResults.add(v);
				}
			} //Checks each neighbor of the bigger one against the smaller one, if any match add it to the results list 
		} else {
			for (Vertex w : bVertices) {
				if (aVertices.contains(w)) {
					downstreamResults.add(w);
				}
			}
		}

		return downstreamResults;
	}

}