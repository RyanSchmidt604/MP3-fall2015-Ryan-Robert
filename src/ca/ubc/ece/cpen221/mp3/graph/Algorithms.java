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
        for (Vertex v : g.getVertices()) {
            Queue<Vertex> vertexQueue = new LinkedList<>();
            List<Vertex> visited = new LinkedList<>();
            visited.add(v);
            vertexQueue.add(v);
            while (!(vertexQueue.isEmpty())) {
                Vertex currentVertex = vertexQueue.poll();
                for (Vertex w : g.getDownstreamNeighbors(currentVertex)) {
                    if (!(visited.contains(w))) {
                        visited.add(w);
                        vertexQueue.add(w);
                    }
                }
            }
            bfsResults.add(visited);
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
        for (Vertex v : g.getVertices()) {
            Stack<Vertex> vertexStack = new Stack<>();
            List<Vertex> visited = new LinkedList<>();
            vertexStack.push(v);
            while (!(vertexStack.isEmpty())) {
                Vertex currentVertex = vertexStack.pop();
                if (!(visited.contains(currentVertex))) {
                    visited.add(currentVertex);
                }
                for (Vertex w : g.getDownstreamNeighbors(currentVertex)) {
                    vertexStack.add(w);
                }
            }
            dfsResults.add(visited);
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

}