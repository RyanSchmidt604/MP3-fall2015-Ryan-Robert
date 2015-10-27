package twitterAnalysis;

import java.util.List;

import ca.ubc.ece.cpen221.mp3.graph.Algorithms;
import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;
/**
 * 
 * @author Ryan Schmidt & Robert Drinnan
 *
 */
public class Query {
    private Vertex a;
    private Vertex b;
    private String type;
    
    public Query(Vertex a, Vertex b, String type)throws IllegalArgumentException{
        this.a = a;
        this.b = b;
        //only create a new query if the type is valid
        if(type.contentEquals("commonInfluencers") || type.contentEquals("numRetweets")){
            this.type = type;
        }else{
            throw new IllegalArgumentException();
        }
    }
    
    public String performQuery(Graph graph){
        StringBuilder result = new StringBuilder();
        result.append("<result>\r\n");
        // the common influencers are the common downstream verticies between the two verticies
        if(type.contentEquals("commonInfluencers")){
            List<Vertex> downStreamVerticies = Algorithms.commonDownstreamVertices(graph, a, b);
            for(Vertex v : downStreamVerticies){
                result.append(v.toString() + "\r\n");
            }
        // the number of retweets to get from user a to user b is the shortest distance 
            //between the two minus 1
        }else if(type.contentEquals("numRetweets")){
            result.append((Integer.toString((Algorithms.shortestDistance(graph, b, a)) - 1))+ "\r\n");
        }
        result.append("</result>\r\n");
        return result.toString();
    }
    @Override
    public String toString(){
        return ("query: " + type + " " + a.toString() + " " + b.toString());
    }
}
