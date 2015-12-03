package ca.ubc.ece.cpen221.mps.tests;

//import static org.junit.Assert.*;
//
//import java.util.*;
//
//import org.junit.Test;
//
//import ca.ubc.ece.cpen221.mp3.graph.AdjacencyListGraph;
//import ca.ubc.ece.cpen221.mp3.graph.Algorithms;
//import ca.ubc.ece.cpen221.mp3.staff.Graph;
//import ca.ubc.ece.cpen221.mp3.staff.Vertex;
//
///**
// * 
// * @author Robert Drinnan and Ryan Schmidt
// *
// */
//
//public class algorithmTest {
//
//    private Graph testGraph = new AdjacencyListGraph();
//
//    @Test
//    public void shortesDistanceTest() {
//
//        Vertex test1 = new Vertex("test1");
//        Vertex test2 = new Vertex("test2");
//        Vertex test3 = new Vertex("test3");
//        Vertex test4 = new Vertex("test4");
//        Vertex test5 = new Vertex("test5");
//        Vertex test6 = new Vertex("test6");
//        Vertex test7 = new Vertex("test7");
//
//        testGraph.addVertex(test1);
//        testGraph.addVertex(test2);
//        testGraph.addVertex(test3);
//        testGraph.addVertex(test4);
//        testGraph.addVertex(test5);
//        testGraph.addVertex(test6);
//        testGraph.addVertex(test7);
//
//        testGraph.addEdge(test1, test2);
//        testGraph.addEdge(test1, test3);
//        testGraph.addEdge(test1, test4);
//        testGraph.addEdge(test2, test3);
//        testGraph.addEdge(test4, test5);
//        testGraph.addEdge(test5, test2);
//        testGraph.addEdge(test6, test7);
//
//        assertEquals(1, Algorithms.shortestDistance(testGraph, test1, test3));
//        assertEquals(3, Algorithms.shortestDistance(testGraph, test4, test3));
//        assertEquals(1, Algorithms.shortestDistance(testGraph, test6, test7));
//        assertEquals(0, Algorithms.shortestDistance(testGraph, test5, test6));
//        assertEquals(1, Algorithms.shortestDistance(testGraph, test1, test2));
//        assertEquals(0, Algorithms.shortestDistance(testGraph, test1, test1));
//    }
//
//    @Test
//    public void commonDownstreamVerticesTest() {
//
//        Vertex test1 = new Vertex("test1");
//        Vertex test2 = new Vertex("test2");
//        Vertex test3 = new Vertex("test3");
//        Vertex test4 = new Vertex("test4");
//        Vertex test5 = new Vertex("test5");
//        Vertex test6 = new Vertex("test6");
//        Vertex test7 = new Vertex("test7");
//
//        testGraph.addVertex(test1);
//        testGraph.addVertex(test2);
//        testGraph.addVertex(test3);
//        testGraph.addVertex(test4);
//        testGraph.addVertex(test5);
//        testGraph.addVertex(test6);
//        testGraph.addVertex(test7);
//
//        testGraph.addEdge(test1, test2);
//        testGraph.addEdge(test1, test3);
//        testGraph.addEdge(test1, test4);
//        testGraph.addEdge(test2, test3);
//        testGraph.addEdge(test4, test5);
//        testGraph.addEdge(test5, test2);
//        testGraph.addEdge(test5, test3);
//        testGraph.addEdge(test6, test7);
//
//        List<Vertex> expectedResults1 = new LinkedList<Vertex>();
//        List<Vertex> expectedResults2 = new LinkedList<Vertex>();
//        List<Vertex> expectedResults3 = new LinkedList<Vertex>();
//        expectedResults1.add(test3);
//        expectedResults3.add(test2);
//        expectedResults3.add(test3);
//
//        assertEquals(true, Algorithms.commonDownstreamVertices(testGraph, test1, test2).containsAll(expectedResults1));
//        assertEquals(true, Algorithms.commonDownstreamVertices(testGraph, test1, test4).containsAll(expectedResults2));
//        assertEquals(true, Algorithms.commonDownstreamVertices(testGraph, test1, test5).containsAll(expectedResults3));
//
//    }
//
//    @Test
//    public void commonUpstreamVerticesTest() {
//        Vertex test1 = new Vertex("test1");
//        Vertex test2 = new Vertex("test2");
//        Vertex test3 = new Vertex("test3");
//        Vertex test4 = new Vertex("test4");
//        Vertex test5 = new Vertex("test5");
//        Vertex test6 = new Vertex("test6");
//        Vertex test7 = new Vertex("test7");
//
//        testGraph.addVertex(test1);
//        testGraph.addVertex(test2);
//        testGraph.addVertex(test3);
//        testGraph.addVertex(test4);
//        testGraph.addVertex(test5);
//        testGraph.addVertex(test6);
//        testGraph.addVertex(test7);
//
//        testGraph.addEdge(test1, test2);
//        testGraph.addEdge(test1, test3);
//        testGraph.addEdge(test1, test4);
//        testGraph.addEdge(test2, test3);
//        testGraph.addEdge(test4, test5);
//        testGraph.addEdge(test5, test2);
//        testGraph.addEdge(test5, test3);
//        testGraph.addEdge(test6, test7);
//
//        List<Vertex> expectedResults1 = new LinkedList<Vertex>();
//        List<Vertex> expectedResults2 = new LinkedList<Vertex>();
//        List<Vertex> expectedResults3 = new LinkedList<Vertex>();
//
//        expectedResults1.add(test1);
//        expectedResults3.add(test1);
//        expectedResults3.add(test5);
//
//        assertEquals(true, Algorithms.commonUpstreamVertices(testGraph, test4, test3).containsAll(expectedResults1));
//        assertEquals(true, Algorithms.commonUpstreamVertices(testGraph, test7, test4).containsAll(expectedResults2));
//        assertEquals(true, Algorithms.commonUpstreamVertices(testGraph, test2, test3).containsAll(expectedResults3));
//
//    }
//
//    @Test
//    public void bfsTest() {
//        Vertex a = new Vertex("a");
//        Vertex b = new Vertex("b");
//        Vertex c = new Vertex("c");
//        Vertex d = new Vertex("d");
//        Vertex e = new Vertex("e");
//        Vertex f = new Vertex("f");
//
//        testGraph.addVertex(a);
//        testGraph.addVertex(b);
//        testGraph.addVertex(c);
//        testGraph.addVertex(d);
//        testGraph.addVertex(e);
//        testGraph.addVertex(f);
//
//        testGraph.addEdge(a, b);
//        testGraph.addEdge(a, c);
//        testGraph.addEdge(b, d);
//        testGraph.addEdge(b, e);
//        testGraph.addEdge(d, f);
//        
//        Set<List<Vertex>> testSet = new HashSet<>();
//        List<Vertex> testList1 = new LinkedList<>();
//        testList1.add(e);
//
//        List<Vertex> testList2 = new LinkedList<>();
//        testList2.add(f);
//        
//        List<Vertex> testList3 = new LinkedList<>();
//        testList3.add(c);
//        
//        List<Vertex> testList4 = new LinkedList<>();
//        testList4.add(d);
//        testList4.add(f);
//        
//        List<Vertex> testList5 = new LinkedList<>();
//        testList5.add(b);
//        testList5.add(d);
//        testList5.add(e);
//        testList5.add(f);
//
//        
//        List<Vertex> testList6 = new LinkedList<>();
//        testList6.add(a);
//        testList6.add(b);
//        testList6.add(c);
//        testList6.add(d);
//        testList6.add(e);
//        testList6.add(f);
//
//        
//        
//        testSet.add(testList1);
//        testSet.add(testList2);
//        testSet.add(testList3);
//        testSet.add(testList4);
//        testSet.add(testList5);
//        testSet.add(testList6);
//
//        assertEquals(true, Algorithms.BFS(testGraph).equals(testSet));
//    }
//
//    @Test
//    public void dfsTest() {
//        Vertex a = new Vertex("a");
//        Vertex b = new Vertex("b");
//        Vertex c = new Vertex("c");
//        Vertex d = new Vertex("d");
//        Vertex e = new Vertex("e");
//        Vertex f = new Vertex("f");
//
//        testGraph.addVertex(a);
//        testGraph.addVertex(b);
//        testGraph.addVertex(c);
//        testGraph.addVertex(d);
//        testGraph.addVertex(e);
//        testGraph.addVertex(f);
//
//        testGraph.addEdge(a, b);
//        testGraph.addEdge(a, c);
//        testGraph.addEdge(b, d);
//        testGraph.addEdge(b, e);
//        testGraph.addEdge(d, f);
//        
//        Set<List<Vertex>> testSet = new HashSet<>();
//        List<Vertex> testList1 = new LinkedList<>();
//        testList1.add(e);
//
//        List<Vertex> testList2 = new LinkedList<>();
//        testList2.add(f);
//        
//        List<Vertex> testList3 = new LinkedList<>();
//        testList3.add(c);
//        
//        List<Vertex> testList4 = new LinkedList<>();
//        testList4.add(d);
//        testList4.add(f);
//        
//        List<Vertex> testList5 = new LinkedList<>();
//        testList5.add(b);
//        testList5.add(e);
//        testList5.add(d);
//        testList5.add(f);
//
//        
//        List<Vertex> testList6 = new LinkedList<>();
//        testList6.add(a);
//        testList6.add(c);
//        testList6.add(b);
//        testList6.add(e);
//        testList6.add(d);
//        testList6.add(f);
//
//        
//        
//        testSet.add(testList1);
//        testSet.add(testList2);
//        testSet.add(testList3);
//        testSet.add(testList4);
//        testSet.add(testList5);
//        testSet.add(testList6);
//
//        
//        assertEquals(true, Algorithms.DFS(testGraph).equals(testSet));
//    }
//
//    @Test
//    public void cyclicGraphTest() {
//        Vertex a = new Vertex("a");
//        Vertex b = new Vertex("b");
//        Vertex c = new Vertex("c");
//        Vertex d = new Vertex("d");
//
//        testGraph.addVertex(a);
//        testGraph.addVertex(b);
//        testGraph.addVertex(c);
//        testGraph.addVertex(d);
//
//        testGraph.addEdge(a, b);
//        testGraph.addEdge(b, c);
//        testGraph.addEdge(c, d);
//        testGraph.addEdge(d, a);
//        
//        Set<List<Vertex>> testSet = new HashSet<>();
//        List<Vertex> testList1 = new LinkedList<>();
//        testList1.add(a);
//        testList1.add(b);
//        testList1.add(c);
//        testList1.add(d);
//
//        List<Vertex> testList2 = new LinkedList<>();
//        testList2.add(b);
//        testList2.add(c);
//        testList2.add(d);
//        testList2.add(a);
//        
//        List<Vertex> testList3 = new LinkedList<>();
//        testList3.add(c);
//        testList3.add(d);
//        testList3.add(a);
//        testList3.add(b);
//        
//        List<Vertex> testList4 = new LinkedList<>();
//        testList4.add(d);
//        testList4.add(a);
//        testList4.add(b);
//        testList4.add(c);
//        
//        testSet.add(testList1);
//        testSet.add(testList2);
//        testSet.add(testList3);
//        testSet.add(testList4);
//
//        assertEquals(true,Algorithms.BFS(testGraph).equals(testSet));
//        assertEquals(true,Algorithms.DFS(testGraph).equals(testSet));
//
//
//    }
//}

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ca.ubc.ece.cpen221.mp3.graph.*;
import ca.ubc.ece.cpen221.mp3.staff.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


@RunWith(Parameterized.class)
public class algorithmTest {
    // Graph aGraph = new AdjacencyListGraph();
    Graph aGraph;
    static Vertex a;
    static Vertex b;
    static Vertex c;

    static Vertex d;
    static Vertex e;
    static Vertex f;

    static Vertex g;
    static Vertex h;
    static Vertex i;

    static Vertex j;
    static Vertex k;
    static Vertex l;

    static int GRAPH_SIZE = 12;

    // @BeforeClass
    // public void setupGraph() {
    //
    // }

    public algorithmTest(Graph anInterface) {
        this.aGraph = anInterface;
    }

    @Before
    public void setup() throws InstantiationException, IllegalAccessException {
        this.aGraph = aGraph.getClass().newInstance();

        a = new Vertex("A");
        b = new Vertex("B");
        c = new Vertex("C");
        d = new Vertex("D");
        e = new Vertex("E");
        f = new Vertex("F");
        g = new Vertex("G");
        h = new Vertex("H");
        i = new Vertex("I");
        j = new Vertex("J");
        k = new Vertex("K");
        l = new Vertex("L");

        aGraph.addVertex(a);
        aGraph.addVertex(b);
        aGraph.addVertex(c);

        aGraph.addVertex(d);
        aGraph.addVertex(e);
        aGraph.addVertex(f);

        aGraph.addVertex(g);
        aGraph.addVertex(h);
        aGraph.addVertex(i);

        aGraph.addVertex(j);
        aGraph.addVertex(k);
        aGraph.addVertex(l);

        aGraph.addEdge(a, b);
        aGraph.addEdge(b, a);
        aGraph.addEdge(b, c);
        aGraph.addEdge(b, d);
        aGraph.addEdge(c, a);
        aGraph.addEdge(d, c);
        aGraph.addEdge(b, e);
        aGraph.addEdge(b, f);
        aGraph.addEdge(e, b);
        aGraph.addEdge(f, b);
        aGraph.addEdge(d, g);
        aGraph.addEdge(g, h);
        aGraph.addEdge(g, i);
        aGraph.addEdge(i, j);
        aGraph.addEdge(i, k);
    }

    @Test
    public void testDFS() {
        Set<List<Vertex>> setOfTraversalListsDFS = Algorithms.DFS(aGraph);

        // check the size of the set
        assertEquals(GRAPH_SIZE, setOfTraversalListsDFS.size());

        // check the traversal(s)
        for (List<Vertex> aList : setOfTraversalListsDFS) {
            Vertex startingVertex = aList.get(0);

            // Check the traversal of Vertex b
            if (startingVertex.equals(b)) {
                // check the size of each list
                // and make sure that there are no duplicates
                assertEquals(GRAPH_SIZE - 1, aList.size()); // "L" is not
                                                            // connected to the
                                                            // graph
                assertFalse(checkIfThereIsADuplicate(aList));

                // Check that A is immediatly after C
                assertEquals(1, Math.abs((aList.indexOf(c) - aList.indexOf(a))));
            }
        }
    }

    @Test
    public void testBFS() {
        Set<List<Vertex>> setOfTraversalListsDFS = Algorithms.BFS(aGraph);

        // Check the size of the set
        assertEquals(GRAPH_SIZE, setOfTraversalListsDFS.size());

        // check the traversal(s)
        for (List<Vertex> aList : setOfTraversalListsDFS) {
            Vertex startingVertex = aList.get(0);

            // Check the traversal of Vertex b
            if (startingVertex.equals(b)) {
                // Check the size of each list
                // and make sure that there are no duplicates
                assertEquals(GRAPH_SIZE - 1, aList.size()); // "L" is not
                                                            // connected to the
                                                            // graph
                assertFalse(checkIfThereIsADuplicate(aList));

                // Check that H and I are not more than one hop distance apart
                assertTrue((Math.abs(aList.indexOf(h) - aList.indexOf(i))) == 1);
            }
        }
    }

    @Test
    public void shortestDistanceTest() {
        // Varias Tests with Distances >= 0
        try {
            assertEquals(0, Algorithms.shortestDistance(aGraph, a, a));
            assertEquals(2, Algorithms.shortestDistance(aGraph, a, f));
            assertEquals(1, Algorithms.shortestDistance(aGraph, b, c));
            assertEquals(3, Algorithms.shortestDistance(aGraph, d, b));
            assertEquals(4, Algorithms.shortestDistance(aGraph, d, e));
            assertEquals(5, Algorithms.shortestDistance(aGraph, f, k));
        } catch (Exception e) {
            fail(); // Should not through an exception
        }

        // Test no path == Exception or return special value
        try {
            assertTrue(Algorithms.shortestDistance(aGraph, j, k) < 0);
        } catch (Exception e) {
            // Its alright to through an exception if there is no path
        }
    }

    @Test
    public void commonDownstreamTest() {
        // Test Normal case
        List<Vertex> intersectionList = Algorithms.commonDownstreamVertices(aGraph, a, e);
        assertEquals(new HashSet<Vertex>() {
            {
                add(b);
            }
        }, new HashSet<Vertex>(intersectionList));

        // Test No commonUpstream
        assertEquals(0, Algorithms.commonDownstreamVertices(aGraph, l, a).size()); // "L"
                                                                           // is
                                                                           // not
                                                                           // connected
                                                                           // to
                                                                           // the
                                                                           // graph
        // Test Symmetry
        assertEquals(Algorithms.commonDownstreamVertices(aGraph, a, e).size(),
                Algorithms.commonDownstreamVertices(aGraph, e, a).size());
    }

    @Test
    public void commonUpstreamTest() {

        // Test Normal case
        List<Vertex> intersectionList = Algorithms.commonUpstreamVertices(aGraph, d, e);

        assertEquals(new HashSet<Vertex>() {
            {
                add(b);
            }
        }, new HashSet<Vertex>(intersectionList));

        // Test No commonUpstream
        assertEquals(0, Algorithms.commonUpstreamVertices(aGraph, l, a).size()); // "L"
                                                                         // is
                                                                         // not
                                                                         // connected
                                                                         // to
                                                                         // the
                                                                         // graph

        // Test Symmetry
        assertEquals(Algorithms.commonUpstreamVertices(aGraph, d, e).size(), Algorithms.commonUpstreamVertices(aGraph, e, d).size());
    }

    private boolean checkIfThereIsADuplicate(List<Vertex> aList) {
        Set<Vertex> set1 = new HashSet<Vertex>();
        for (Vertex aVerterx : aList) {
            if (!set1.add(aVerterx)) {
                return true;
            }
        }
        return false;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> instancesToTest() {
        return Arrays.asList(new Object[] { new AdjacencyListGraph() }, new Object[] { new AdjacencyMatrixGraph() });
    }

}
