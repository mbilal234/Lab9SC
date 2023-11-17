package graph;

import static org.junit.Assert.*;
import java.util.Collections;
import java.util.Map;

import org.junit.Test;

/**
 * Tests for instance methods of Graph.
 */
public abstract class GraphInstanceTest {

    /**
     * Overridden by implementation-specific test classes.
     * 
     * @return a new empty graph of the particular implementation being tested
     */
    public abstract Graph<String> emptyInstance();

    @Test(expected = AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // Ensure that assertions are enabled with VM argument: -ea
    }

    @Test
    public void testInitialVerticesEmpty() {
        // Verify that a new graph has no vertices initially.
        Graph<String> graph = emptyInstance();
        assertEquals("Expected new graph to have no vertices",
                Collections.emptySet(), graph.vertices());
    }

    /**
     * Testing strategy document for Graph instance methods:
     * 
     * - Test adding a new vertex to the graph. i.e. test add()
     * - Test attempting to add a duplicate vertex to the graph. i.e. test add()
     * - Test adding a weighted directed edge between two vertices. i.e. test set()
     * - Test removing a vertex and its incident edges from the graph. i.e. test
     * remove()
     * - Test attempting to remove a nonexistent vertex from the graph. i.e. test
     * remove()
     * - Test updating the weight of a directed edge in the graph. i.e. test set()
     * - Test removing a weighted directed edge from the graph. i.e. test set()
     * - Test retrieving source vertices and their weights for a target vertex. i.e.
     * test sources()
     * - Test retrieving target vertices and their weights for a source vertex. i.e.
     * test targets()
     */

    @Test
    public void testAddVertex() {
        // Test adding a new vertex to the graph.
        Graph<String> graph = emptyInstance();
        assertTrue(graph.add("A"));
        assertTrue(graph.vertices().contains("A"));
    }

    @Test
    public void testAddDuplicateVertex() {
        // Test attempting to add a duplicate vertex to the graph.
        Graph<String> graph = emptyInstance();
        assertTrue(graph.add("A"));
        assertFalse(graph.add("A"));
    }

    @Test
    public void testAddEdge() {
        // Test adding a weighted directed edge between two vertices.
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        assertEquals(0, graph.set("A", "B", 3));
        assertTrue(graph.targets("A").containsKey("B"));
        assertTrue(graph.sources("B").containsKey("A"));
    }

    @Test
    public void testRemoveVertex() {
        // Test removing a vertex and its incident edges from the graph.
        Graph<String> graph = emptyInstance();
        graph.add("A");
        assertTrue(graph.remove("A"));
        assertFalse(graph.vertices().contains("A"));
    }

    @Test
    public void testRemoveNonexistentVertex() {
        // Test attempting to remove a nonexistent vertex from the graph.
        Graph<String> graph = emptyInstance();
        assertFalse(graph.remove("A"));
    }

    @Test
    public void testSetEdgeWeight() {
        // Test updating the weight of a directed edge in the graph.
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        assertEquals(0, graph.set("A", "B", 3));
        assertEquals(3, graph.set("A", "B", 5));
    }

    @Test
    public void testRemoveEdge() {
        // Test removing a weighted directed edge from the graph.
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        graph.set("A", "B", 3);
        assertEquals(3, graph.set("A", "B", 0));
        assertFalse(graph.targets("A").containsKey("B"));
        assertFalse(graph.sources("B").containsKey("A"));
    }

    @Test
    public void testGetSources() {
        // Test retrieving source vertices and their weights for a target vertex.
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        graph.set("A", "B", 3);
        Map<String, Integer> sources = graph.sources("B");
        assertTrue(sources.containsKey("A"));
        assertEquals(3, sources.get("A").intValue());
    }

    @Test
    public void testGetTargets() {
        // Test retrieving target vertices and their weights for a source vertex.
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        graph.set("A", "B", 3);
        Map<String, Integer> targets = graph.targets("A");
        assertTrue(targets.containsKey("B"));
        assertEquals(3, targets.get("B").intValue());
    }
}
