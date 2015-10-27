package twitterAnalysis;

import java.io.*;
import java.util.*;

import ca.ubc.ece.cpen221.mp3.graph.*;
import ca.ubc.ece.cpen221.mp3.staff.*;

/**
 * A class used to analyze data from Twitter. It can figure out how many
 * retweets it takes for a tweet to get from one user to another. Also it
 * can determine all the twitter users that two different users follow.<br><br>
 * 
 * The data to be analyzed must be in a folder named "datasets" in the same directory 
 * as this program and named "twitter.txt". <br>
 * The data must be in the format "[USER a] -> [USER b]\r\n" for each line in "twitter.txt"
 * where "\r\n" are the default newline characters in a windows system. In this format,
 * the line is read as USER a follows USER b. The user field should be an integer number 
 * for the user id number.<br>,<br>
 * 
 * Queries to this data are read in from a file and output to another file.<br>
 * The directory and file name for the query input file is passed to this program as
 * args[0] and likewise for the output file but into args[1].<br><br>
 * 
 * Each line of the query file should be in the format: "[Query] [USER a] [USER b] ?\r\n"
 * where query is the type of query to be performed. This program can perform two types 
 * of queries:<br>
 * 1. "commonInfluencers" the program outputs all the users that both user A and user B 
 * follow.<br>
 * 2. "numRetweets" the program outputs the number of minimum number of
 *  retweets it would take for a tweet made by user a to appear in user b's feed.<br><br>
 *  
 * Lines in the queries file will be ignored if: [query] is not one of the two options,
 * either user is not in the dataset, or if the line does not end in a '?'.<br><br>
 * 
 * Query results will be outputed to the output file in the format:<br>
 *      "query: [Query] [USER a] [USER b]"<br>
 *      tag: "result"<br>
 *      ... //(List of common influencers or number of retweets)<br>
 *      tag:"/result"<br>
 *      "
 * 
 * 
 * @author Ryan Schmidt & Robert Drinnan
 *
 */

public class TwitterAnalysis {

    public static void main(String[] args) {
        // first open the data file for the twitter data
        InputStream dataStream;
        try {
            dataStream = new FileInputStream("datasets/twitter.txt");
        } catch (FileNotFoundException e1) {
            System.out.println("dataset missing. program terminated");
            return;
        }
        // put the whole file into a string buffer
        StringBuffer data;
        try {
            data = readWholeFile(dataStream);
        } catch (IOException e1) {
            System.out.println("IO error. program terminated");
            return;
        }
        //start creating a graph with the data
        Graph graph = new AdjacencyListGraph();
        Map<String, Vertex> labels = new HashMap<>();//this map holds all the verticies
        //that we are using so that there are no duplicates
        int index1;
        int index2;
        while(true){
            
            if((index1 = data.indexOf("->")) == -1 || (index2 = data.indexOf("\r\n")) == -1){
                break;// break if the buffer doesn't have another valid line
            }
            String labelA = data.substring(0, index1 - 1).trim();//parse the first user
            String labelB = data.substring(index1 + 2, index2).trim();//parse the second user
            //add the vertexes to graph only if they don't already exist
            if(!labels.containsKey(labelA)){   
                labels.put(labelA, new Vertex(labelA));
                graph.addVertex(labels.get(labelA));
            }
            if(!labels.containsKey(labelB)){
                labels.put(labelB, new Vertex(labelB));
                graph.addVertex(labels.get(labelB));
            }
            //add the edge between the verticies
            if(!graph.edgeExists(labels.get(labelA), labels.get(labelB))){
                graph.addEdge(labels.get(labelA), labels.get(labelB));
            }
            //move to the next line of the buffer
            data.delete(0, index2 + 2);
        }
        

        //open the file used for queries
        InputStream queryStream;
        try {
            queryStream = new FileInputStream(args[0]);
        } catch (FileNotFoundException e) {
            System.out.println("Invalid file name. Program Terminated");
            return;
        }
        //open the file for output
        File outputFile = new File(args[1]);
        
        OutputStream outStream;
        try {
            outStream = new FileOutputStream(outputFile, true);
        } catch (FileNotFoundException e) {
            System.out.println("Invalid file name. Program Terminated");
            return;
        }
        //create a new file if need be
        if(!outputFile.exists()){
            try {
                outputFile.createNewFile();
            } catch (IOException e) {
                System.out.println("IO error. program terminated");
            }
        }
        //put the whole query file into a buffer
        StringBuffer queryData;
        try {
            queryData = readWholeFile(queryStream);
        } catch (IOException e) {
            System.out.println("IO error. Program Terminated");
            return;
        }
        //make a list of queries from the buffer
        List<String> queryList = parseQueries(queryData);
        Queue<Query> queryQueue = new LinkedList<>();
        //add the queries from the list into the queue only if the graph contains the verticies
        for(int i = 0; i < queryList.size(); i++){
            String[] currentQuery = queryList.get(i).split(" ");
            
            if(labels.containsKey(currentQuery[1])){
                if(labels.containsKey(currentQuery[2])){
                    queryQueue.add(new Query(labels.get(currentQuery[1]),
                            labels.get(currentQuery[2]), currentQuery[0]));
                }
            }
        }
        //perform all the queries and output to the file
        while(!queryQueue.isEmpty()){
            try {
                outStream.write((queryQueue.peek().toString() + "\r\n").getBytes());
                outStream.write(queryQueue.poll().performQuery(graph).getBytes());
            } catch (IOException e) {
                System.out.println("IO error. program terminated");
            }
        }
        
        try {
            outStream.close();
            queryStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    private static StringBuffer readWholeFile(InputStream input) throws IOException{
        StringBuffer buffer = new StringBuffer();
        byte[] bytes = new byte[64];
        //read in from the file 64 bytes at a time to increase speed
        while ((input.read(bytes, 0, 64)) != -1) {
            buffer.append(new String(bytes));
        }
        return buffer;
    }
    
    
    private static List<String> parseQueries(StringBuffer buf){
        StringBuffer buffer = new StringBuffer(buf);
        List<String> queries = new ArrayList<>();
        
        while(buffer.indexOf("\r\n") != -1){//only continue if the buffer has another valid line
            String nextLine = readLine(buffer);
            String[] attributes = nextLine.split(" ");
            if(attributes[0].contentEquals("commonInfluencers")// the query must be of a valid
                    || attributes[0].contentEquals("numRetweets")){//type
                if(!queries.contains(nextLine)){
                    queries.add(nextLine);
                }
            }
        }
        return queries;
    }
    
    private static String readLine(StringBuffer buf){
        while(true){
            int index;
            if((index = buf.indexOf("\r\n")) == -1){//only read if the buffer has a valid line
                return "";
            }
            
            String nextLine = buf.substring(0, index);
            buf.delete(0, index + 2).toString();
            if(nextLine.charAt(index - 1) == '?'){
                return nextLine.substring(0, index - 2).trim();
            }
        }
    }
}
