package ca.ubc.ece.cpen221.mps.tests;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

import ca.ubc.ece.cpen221.mp3.graph.AdjacencyListGraph;
import ca.ubc.ece.cpen221.mp3.graph.Algorithms;
import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

/**
 * 
 * @author Robert Drinnan and Ryan Schmidt
 *
 */

public class algorithmTest {

    private Graph testGraph = new AdjacencyListGraph();

    @Test
    public void shortesDistanceTest() {

        Vertex test1 = new Vertex("test1");
        Vertex test2 = new Vertex("test2");
        Vertex test3 = new Vertex("test3");
        Vertex test4 = new Vertex("test4");
        Vertex test5 = new Vertex("test5");
        Vertex test6 = new Vertex("test6");
        Vertex test7 = new Vertex("test7");

        testGraph.addVertex(test1);
        testGraph.addVertex(test2);
        testGraph.addVertex(test3);
        testGraph.addVertex(test4);
        testGraph.addVertex(test5);
        testGraph.addVertex(test6);
        testGraph.addVertex(test7);

        testGraph.addEdge(test1, test2);
        testGraph.addEdge(test1, test3);
        testGraph.addEdge(test1, test4);
        testGraph.addEdge(test2, test3);
        testGraph.addEdge(test4, test5);
        testGraph.addEdge(test5, test2);
        testGraph.addEdge(test6, test7);

        assertEquals(1, Algorithms.shortestDistance(testGraph, test1, test3));
        assertEquals(3, Algorithms.shortestDistance(testGraph, test4, test3));
        assertEquals(1, Algorithms.shortestDistance(testGraph, test6, test7));
        assertEquals(0, Algorithms.shortestDistance(testGraph, test5, test6));
        assertEquals(1, Algorithms.shortestDistance(testGraph, test1, test2));
        assertEquals(0, Algorithms.shortestDistance(testGraph, test1, test1));
    }

    @Test
    public void commonDownstreamVerticesTest() {

        Vertex test1 = new Vertex("test1");
        Vertex test2 = new Vertex("test2");
        Vertex test3 = new Vertex("test3");
        Vertex test4 = new Vertex("test4");
        Vertex test5 = new Vertex("test5");
        Vertex test6 = new Vertex("test6");
        Vertex test7 = new Vertex("test7");

        testGraph.addVertex(test1);
        testGraph.addVertex(test2);
        testGraph.addVertex(test3);
        testGraph.addVertex(test4);
        testGraph.addVertex(test5);
        testGraph.addVertex(test6);
        testGraph.addVertex(test7);

        testGraph.addEdge(test1, test2);
        testGraph.addEdge(test1, test3);
        testGraph.addEdge(test1, test4);
        testGraph.addEdge(test2, test3);
        testGraph.addEdge(test4, test5);
        testGraph.addEdge(test5, test2);
        testGraph.addEdge(test5, test3);
        testGraph.addEdge(test6, test7);

        List<Vertex> expectedResults1 = new LinkedList<Vertex>();
        List<Vertex> expectedResults2 = new LinkedList<Vertex>();
        List<Vertex> expectedResults3 = new LinkedList<Vertex>();
        expectedResults1.add(test3);
        expectedResults3.add(test2);
        expectedResults3.add(test3);

        assertEquals(true, Algorithms.commonDownstreamVertices(testGraph, test1, test2).containsAll(expectedResults1));
        assertEquals(true, Algorithms.commonDownstreamVertices(testGraph, test1, test4).containsAll(expectedResults2));
        assertEquals(true, Algorithms.commonDownstreamVertices(testGraph, test1, test5).containsAll(expectedResults3));

    }

    @Test
    public void commonUpstreamVerticesTest() {
        Vertex test1 = new Vertex("test1");
        Vertex test2 = new Vertex("test2");
        Vertex test3 = new Vertex("test3");
        Vertex test4 = new Vertex("test4");
        Vertex test5 = new Vertex("test5");
        Vertex test6 = new Vertex("test6");
        Vertex test7 = new Vertex("test7");

        testGraph.addVertex(test1);
        testGraph.addVertex(test2);
        testGraph.addVertex(test3);
        testGraph.addVertex(test4);
        testGraph.addVertex(test5);
        testGraph.addVertex(test6);
        testGraph.addVertex(test7);

        testGraph.addEdge(test1, test2);
        testGraph.addEdge(test1, test3);
        testGraph.addEdge(test1, test4);
        testGraph.addEdge(test2, test3);
        testGraph.addEdge(test4, test5);
        testGraph.addEdge(test5, test2);
        testGraph.addEdge(test5, test3);
        testGraph.addEdge(test6, test7);

        List<Vertex> expectedResults1 = new LinkedList<Vertex>();
        List<Vertex> expectedResults2 = new LinkedList<Vertex>();
        List<Vertex> expectedResults3 = new LinkedList<Vertex>();

        expectedResults1.add(test1);
        expectedResults3.add(test1);
        expectedResults3.add(test5);

        assertEquals(true, Algorithms.commonUpstreamVertices(testGraph, test4, test3).containsAll(expectedResults1));
        assertEquals(true, Algorithms.commonUpstreamVertices(testGraph, test7, test4).containsAll(expectedResults2));
        assertEquals(true, Algorithms.commonUpstreamVertices(testGraph, test2, test3).containsAll(expectedResults3));

    }

    @Test
    public void bfsTest() {
        Vertex a = new Vertex("a");
        Vertex b = new Vertex("b");
        Vertex c = new Vertex("c");
        Vertex d = new Vertex("d");
        Vertex e = new Vertex("e");
        Vertex f = new Vertex("f");
  
        testGraph.addVertex(a);
        testGraph.addVertex(b);
        testGraph.addVertex(c);
        testGraph.addVertex(d);
        testGraph.addVertex(e);
        testGraph.addVertex(f);

        testGraph.addEdge(a, b);
        testGraph.addEdge(a, c);
        testGraph.addEdge(b, d);
        testGraph.addEdge(b, e);
        testGraph.addEdge(d, f);

        


        assertEquals(6, Algorithms.BFS(testGraph).toString());
    }
    @Test
    public void dfsTest() {
        Vertex a = new Vertex("a");
        Vertex b = new Vertex("b");
        Vertex c = new Vertex("c");
        Vertex d = new Vertex("d");
        Vertex e = new Vertex("e");
        Vertex f = new Vertex("f");
  
        testGraph.addVertex(a);
        testGraph.addVertex(b);
        testGraph.addVertex(c);
        testGraph.addVertex(d);
        testGraph.addVertex(e);
        testGraph.addVertex(f);

        testGraph.addEdge(a, b);
        testGraph.addEdge(a, c);
        testGraph.addEdge(b, d);
        testGraph.addEdge(b, e);
        testGraph.addEdge(d, f);

        


        assertEquals(6, Algorithms.DFS(testGraph).toString());
    }
}
