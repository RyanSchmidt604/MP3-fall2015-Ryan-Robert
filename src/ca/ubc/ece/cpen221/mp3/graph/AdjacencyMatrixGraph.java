package ca.ubc.ece.cpen221.mp3.graph;

import java.util.*;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class AdjacencyMatrixGraph implements Graph {
    private List<ArrayList<Integer>> adjacencyMatrix = new ArrayList<>();
    private List<Vertex> vertexList = new ArrayList<>();
    

    @Override
    public void addVertex(Vertex v) {
        vertexList.add(v);
        ArrayList<Integer> newList = new ArrayList<>();
        for(int i = 0; i < vertexList.size(); i++){
            newList.add(0);
        }
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
    
    private List<ArrayList<Integer>> getSquaredMatrix(){
        List<ArrayList<Integer>> productMatrix = new ArrayList<>(adjacencyMatrix);
        
        for(int i = 0; i < adjacencyMatrix.size(); i++){
            for(int j = 0; j < adjacencyMatrix.get(i).size(); j++){
                productMatrix.get(i).set(j, (productMatrix.get(i).get(j) == 1 &&
                        adjacencyMatrix.get(j).get(i) == 1) ? 1 : 0);
            }
        }
        
        return productMatrix;
    }
}
