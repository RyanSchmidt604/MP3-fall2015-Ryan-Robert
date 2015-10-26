package twitterAnalysis;

import java.io.*;
import java.util.*;

import ca.ubc.ece.cpen221.mp3.graph.*;
import ca.ubc.ece.cpen221.mp3.staff.*;

public class TwitterAnalysis {

    public static void main(String[] args) {
        InputStream dataStream;
        try {
            dataStream = new FileInputStream("datasets/twitter.txt");
        } catch (FileNotFoundException e1) {
            System.out.println("dataset missing. program terminated");
            return;
        }
        
        StringBuffer data;
        try {
            data = readWholeFile(dataStream);
        } catch (IOException e1) {
            System.out.println("IO error. program terminated");
            return;
        }
        
        Graph graph = new AdjacencyListGraph();
        Map<String, Vertex> labels = new HashMap<>();
        
        while(true){
            ArrayList<Vertex> nextEdge = nextEdge(data, labels, graph);
            if(nextEdge.isEmpty()){
                break;
            }
            
            if(!graph.edgeExists(nextEdge.get(0), nextEdge.get(1))){
                graph.addEdge(nextEdge.get(0), nextEdge.get(1));
            }
        }
        
        
        
        
        
        
        Scanner console = new Scanner(System.in);
        
        System.out.println("Queries file: ");
        String queriesFile = console.nextLine().trim();
        
        InputStream queryStream;
        try {
            queryStream = new FileInputStream(queriesFile);
        } catch (FileNotFoundException e) {
            System.out.println("Invalid file name. Program Terminated");
            return;
        }
        
        System.out.println("Output file: ");
        String outputFile = console.nextLine().trim();
        
        OutputStream outStream;
        try {
            outStream = new FileOutputStream(outputFile, true);
        } catch (FileNotFoundException e) {
            System.out.println("Invalid file name. Program Terminated");
            return;
        }
        
        StringBuffer queryData;
        try {
            queryData = readWholeFile(queryStream);
        } catch (IOException e) {
            System.out.println("IO error. Program Terminated");
            return;
        }
        
        List<String> queryList = parseQueries(queryData);
        Queue<Query> queryQueue = new LinkedList<>();
        
        for(int i = 0; i < queryList.size(); i++){
            String[] currentQuery = queryList.get(i).split(" ");
            
            //TODO finish
        }
            
        
    }
    private static StringBuffer readWholeFile(InputStream input) throws IOException{
        StringBuffer buffer = new StringBuffer();
        
        while (true) {
            int ch;
                ch = input.read();
            if (ch < 0) {
                break;
            }

            buffer.append((char) ch);
        }
        return buffer;
    }
    
    private static ArrayList<Vertex> nextEdge(StringBuffer buf, Map<String, Vertex> labels,
            Graph graph){
        if(buf.length() == 0){
            return new ArrayList<Vertex>();
        }
        int index1 = buf.indexOf("->");
        int index2 = buf.indexOf("\r\n");
        
        String labelA = buf.substring(0, index1 - 1).trim();
        String labelB = buf.substring(index1 + 2, index2 -1).trim();
        ArrayList<Vertex> result = new ArrayList<>();
        
        if (labels.containsKey(labelA)){
            result.add(labels.get(labelA));
        }else{
            labels.put(labelA, new Vertex(labelA));
            graph.addVertex(labels.get(labelA));
            result.add(labels.get(labelA));
        }
        
        if (labels.containsKey(labelB)){
            result.add(labels.get(labelB));
        }else{
            labels.put(labelA, new Vertex(labelB));
            graph.addVertex(labels.get(labelB));
            result.add(labels.get(labelB));
        }
        
        buf.delete(0, index2 + 2);
        return result;
    }

    private static List<String> parseQueries(StringBuffer buf){
        StringBuffer buffer = new StringBuffer(buf);
        List<String> queries = new ArrayList<>();
        
        while(buffer.length() != 0){
            String nextLine = readLine(buffer);
            String[] attributes = nextLine.split(" ");
            if(attributes[0] == "commonInfluencers" || attributes[0] == "numRetweets"){
                if(!queries.contains(nextLine)){
                    queries.add(nextLine);
                }
            }
        }
        return queries;
    }
    
    private static String readLine(StringBuffer buf){
        while(true){
            if(buf.length() == 0){
                return "";
            }
            int index = buf.indexOf("\r\n");
            String nextLine = buf.substring(0, index);
            buf.delete(0, index + 2).toString();
            if(nextLine.charAt(index - 1) == '?'){
                return nextLine.substring(0, index - 2).trim();
            }
        }
    }
}
