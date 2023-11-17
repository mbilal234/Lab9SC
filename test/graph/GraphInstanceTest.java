/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
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
        assert false; // make sure assertions are enabled with VM argument: -ea
    }

    @Test
    public void testInitialVerticesEmpty() {
        // Verify that a new graph has no vertices.
        assertEquals("Expected new graph to have no vertices",
                Collections.emptySet(), emptyInstance().vertices());
    }

    @Test
    public void testAddVertex() {
        Graph<String> graph = emptyInstance();
        assertTrue(graph.add("A"));
        assertTrue(graph.vertices().contains("A"));
    }

    @Test
    public void testAddDuplicateVertex() {
        Graph<String> graph = emptyInstance();
        assertTrue(graph.add("A"));
        assertFalse(graph.add("A"));
    }

    @Test
    public void testAddEdge() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        assertEquals(0, graph.set("A", "B", 3));
        assertTrue(graph.targets("A").containsKey("B"));
        assertTrue(graph.sources("B").containsKey("A"));
    }

    @Test
    public void testRemoveVertex() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        assertTrue(graph.remove("A"));
        assertFalse(graph.vertices().contains("A"));
    }

    @Test
    public void testRemoveNonexistentVertex() {
        Graph<String> graph = emptyInstance();
        assertFalse(graph.remove("A"));
    }

    @Test
    public void testSetEdgeWeight() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        assertEquals(0, graph.set("A", "B", 3));
        assertEquals(3, graph.set("A", "B", 5));
    }

    @Test
    public void testRemoveEdge() {
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
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        graph.set("A", "B", 3);
        Map<String, Integer> targets = graph.targets("A");
        assertTrue(targets.containsKey("B"));
        assertEquals(3, targets.get("B").intValue());
    }
}
