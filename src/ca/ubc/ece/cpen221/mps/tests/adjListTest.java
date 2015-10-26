package ca.ubc.ece.cpen221.mps.tests;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;
import ca.ubc.ece.cpen221.mp3.graph.AdjacencyListGraph;
import ca.ubc.ece.cpen221.mp3.graph.AdjacencyMatrixGraph;
import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;

public class adjListTest {
	private Graph g = new AdjacencyMatrixGraph();

	@Test
	public void addVertexAndGetVertexTest() {

		Vertex test1 = new Vertex("test1");
		Vertex test2 = new Vertex("test2");
		Vertex test3 = new Vertex("test3");
		Vertex test4 = new Vertex("test4");
		Vertex test5 = new Vertex("test5");

		g.addVertex(test1);
		g.addVertex(test2);
		g.addVertex(test3);
		g.addVertex(test4);

		assertEquals("[test1, test2, test3, test4]", g.getVertices().toString());

		g.addVertex(test5);

		assertEquals("[test1, test2, test3, test4, test5]", g.getVertices().toString());
	}

	@Test
	public void AddEdgeAndEdgeExistsTest() {

		Vertex test1 = new Vertex("test1");
		Vertex test2 = new Vertex("test2");
		Vertex test3 = new Vertex("test3");
		Vertex test4 = new Vertex("test4");

		g.addVertex(test1);
		g.addVertex(test2);
		g.addVertex(test3);
		g.addVertex(test4);

		g.addEdge(test1, test2);
		g.addEdge(test1, test3);

		assertEquals(true, g.edgeExists(test1, test2));
		assertEquals(false, g.edgeExists(test2, test1));
		assertEquals(true, g.edgeExists(test1, test3));
		assertEquals(false, g.edgeExists(test1, test4));

	}

	@Test
	public void getVerticesNullTest() {
		assertEquals(0, g.getVertices().size());
	}

	@Test
	public void getUpstreamNeighborsTest() {
		Vertex test1 = new Vertex("test1");
		Vertex test2 = new Vertex("test2");
		Vertex test3 = new Vertex("test3");
		Vertex test4 = new Vertex("test4");
		Vertex test5 = new Vertex("test5");

		g.addVertex(test1);
		g.addVertex(test2);
		g.addVertex(test3);
		g.addVertex(test4);
		g.addVertex(test5);

		g.addEdge(test1, test2);
		g.addEdge(test1, test3);
		g.addEdge(test1, test4);
		g.addEdge(test3, test5);
		g.addEdge(test5, test2);

		List<Vertex> testList2 = new LinkedList<>();
		testList2.add(test5);
		testList2.add(test1);

		List<Vertex> testList4 = new LinkedList<>();
		testList4.add(test1);

		assertEquals(true, g.getUpstreamNeighbors(test2).containsAll(testList2));
		assertEquals(true, g.getUpstreamNeighbors(test4).containsAll(testList4));
		assertEquals(0, g.getUpstreamNeighbors(test1).size());
	}

	@Test
	public void getDownstreamNeighborsTest() {
		Vertex test1 = new Vertex("test1");
		Vertex test2 = new Vertex("test2");
		Vertex test3 = new Vertex("test3");
		Vertex test4 = new Vertex("test4");
		Vertex test5 = new Vertex("test5");

		g.addVertex(test1);
		g.addVertex(test2);
		g.addVertex(test3);
		g.addVertex(test4);
		g.addVertex(test5);

		g.addEdge(test1, test2);
		g.addEdge(test1, test3);
		g.addEdge(test1, test4);
		g.addEdge(test3, test5);
		g.addEdge(test5, test2);

		List<Vertex> testList1 = new LinkedList<>();
		testList1.add(test2);
		testList1.add(test3);
		testList1.add(test4);
		List<Vertex> testList3 = new LinkedList<>();
		testList3.add(test5);

		assertEquals(true, g.getDownstreamNeighbors(test1).containsAll(testList1));
		assertEquals(true, g.getDownstreamNeighbors(test3).containsAll(testList3));
		assertEquals(0, g.getDownstreamNeighbors(test4).size());
	}
}
