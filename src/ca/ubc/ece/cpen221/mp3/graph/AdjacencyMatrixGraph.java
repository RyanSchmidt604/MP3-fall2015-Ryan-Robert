package ca.ubc.ece.cpen221.mp3.graph;

import java.util.*;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

/**
 * Thsi Class implements the Graph interface and uses the adjacency matrix 
 * rep of a graph
 * @author Ryan Schmidt & Robert Drinnan
 *
 */
public class AdjacencyMatrixGraph implements Graph {
    //A list of lists is used to represent the matrix
    private List<ArrayList<Integer>> adjacencyMatrix = new ArrayList<>();
    //this list holds the verticies in the graph
    private List<Vertex> vertexList = new ArrayList<>();
    

    @Override
    public void addVertex(Vertex v) {
        vertexList.add(v);
        ArrayList<Integer> newList = new ArrayList<>();
        //populate the new list with enough zeros for the whole matrix
        for(int i = 0; i < vertexList.size(); i++){
            newList.add(0);
        }
        //add an extra zero to every line in the matrix
        for(ArrayList<Integer> current : adjacencyMatrix){
            for(int i = current.size(); i < newList.size(); i++){
                current.add(0);
            }
        }
        adjacencyMatrix.add(newList);
    }

    @Override
    public void addEdge(Vertex v1, Vertex v2) {
        int v1Position = vertexList.indexOf(v1);
        int v2Position = vertexList.indexOf(v2);
        
        adjacencyMatrix.get(v1Position).set(v2Position, 1);
    }

    @Override
    public boolean edgeExists(Vertex v1, Vertex v2) {
        int v1Position = vertexList.indexOf(v1);
        int v2Position = vertexList.indexOf(v2);
        
        if(adjacencyMatrix.get(v1Position).get(v2Position) == 1){
            return true;
        }else return false;
    }

    @Override
    public List<Vertex> getDownstreamNeighbors(Vertex v) {
        List<Vertex> downStreamNeighbors = new ArrayList<>();
        int vertexIndex = vertexList.indexOf(v);
        for(int i = 0; i < adjacencyMatrix.size(); i++){
            if(adjacencyMatrix.get(vertexIndex).get(i) == 1){
                downStreamNeighbors.add(vertexList.get(i));
            }
        }
        return downStreamNeighbors;
    }

    @Override
    public List<Vertex> getUpstreamNeighbors(Vertex v) {
        List<Vertex> upStreamNeighbors = new ArrayList<>();
        int vertexIndex = vertexList.indexOf(v);
        
        for(int i = 0; i < adjacencyMatrix.size(); i++){
            if(adjacencyMatrix.get(i).get(vertexIndex) == 1){
                upStreamNeighbors.add(vertexList.get(i));
            }
        }
        
        return upStreamNeighbors;
    }

    @Override
    public List<Vertex> getVertices() {
        return new ArrayList<Vertex>(vertexList);
    }
}
