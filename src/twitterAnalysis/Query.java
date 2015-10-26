package twitterAnalysis;

import java.util.List;

import ca.ubc.ece.cpen221.mp3.graph.Algorithms;
import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class Query {
    private Vertex a;
    private Vertex b;
    private String type;
    
    public Query(Vertex a, Vertex b, String type)throws IllegalArgumentException{
        this.a = a;
        this.b = b;
        if(type == "commonInfluencers" || type == "numRetweets"){
            this.type = type;
        }else{
            throw new IllegalArgumentException();
        }
    }
    
    public String performQuery(Graph graph){
        StringBuilder result = new StringBuilder();
        result.append("<result>\r\n");
        
        if(type == "commonInfluencers"){
            List<Vertex> upStreamVerticies = Algorithms.commonUpstreamVertices(graph, a, b);
            for(Vertex v : upStreamVerticies){
                result.append(v.toString() + "\r\n");
            }
            result.append("</result>");
            return result.toString();
        }else if(type == "numRetweets"){
            return Integer.toString(Algorithms.shortestDistance(graph, a, b));
        }
        
        return "";
    }
    @Override
    public String toString(){
        return ("query: " + type + " " + a.toString() + " " + b.toString());
    }
}
