/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class ConcreteVerticesGraphTest extends GraphInstanceTest {

    /*
     * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
     */
    @Override
    public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph();
    }

    /*
     * Testing ConcreteVerticesGraph...
     */

    // Testing strategy for ConcreteVerticesGraph.toString()
    // TODO
    // - Test when the graph is empty
    // - Test when the graph has vertices and edges
    // - Test to ensure the format of the string is as expected

    @Test
    public void testConcreteVerticesGraphToStringEmpty() {
        Graph<String> graph = emptyInstance();
        assertEquals("Expected an empty graph representation", "[]", graph.toString());
    }

    @Test
    public void testConcreteVerticesGraphToStringNonEmpty() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        graph.set("A", "B", 3);
        String expected = "[A, B] -> [A -> B (3)]";
        assertEquals("Expected a non-empty graph representation", expected, graph.toString());
    }

    /*
     * Testing Vertex...
     */

    // Testing strategy for Vertex
    // TODO
    // - Test the constructor of Vertex
    // - Test adding edges to a vertex
    // - Test removing edges from a vertex
    // - Test getting sources and targets from a vertex

    // TO BE MODIFIED ONCE VERTEX IS IMPLEMENTED

    // @Test
    // public void testVertexConstructor() {
    // Vertex vertex = new Vertex("A");
    // assertEquals("Vertex constructor should set the label", "A",
    // vertex.getLabel());
    // }

    // @Test
    // public void testVertexAddEdge() {
    // Vertex vertex = new Vertex("A");
    // vertex.addEdge("B", 2);
    // assertTrue("Vertex should have a target vertex 'B'", vertex.hasTarget("B"));
    // assertEquals("Edge weight from A to B should be 2", 2,
    // vertex.getWeightTo("B"));
    // }

    // @Test
    // public void testVertexRemoveEdge() {
    // Vertex vertex = new Vertex("A");
    // vertex.addEdge("B", 2);
    // vertex.removeEdge("B");
    // assertFalse("Vertex should not have a target vertex 'B'",
    // vertex.hasTarget("B"));
    // assertEquals("Edge weight from A to B should be 0", 0,
    // vertex.getWeightTo("B"));
    // }

    // @Test
    // public void testVertexGetSources() {
    // Vertex vertex = new Vertex("B");
    // vertex.addEdge("A", 3);
    // Map<String, Integer> sources = vertex.getSources();
    // assertTrue("Vertex should have a source vertex 'A'",
    // sources.containsKey("A"));
    // assertEquals("Edge weight from A to B should be 3", 3,
    // sources.get("A").intValue());
    // }

    // @Test
    // public void testVertexGetTargets() {
    // Vertex vertex = new Vertex("A");
    // vertex.addEdge("B", 4);
    // Map<String, Integer> targets = vertex.getTargets();
    // assertTrue("Vertex should have a target vertex 'B'",
    // targets.containsKey("B"));
    // assertEquals("Edge weight from A to B should be 4", 4,
    // targets.get("B").intValue());
    // }
}
