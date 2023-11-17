package graph;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConcreteEdgesGraphTest extends GraphInstanceTest {

	@Override
    public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph();
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
      assertFalse(graph.add("A")); // Adding the same vertex again should return false
  }

  @Test
  public void testAddEdge() {
      Graph<String> graph = emptyInstance();
      graph.add("A");
      graph.add("B");
      assertEquals(0, graph.set("A", "B", 5)); // Adding an edge, should return 0 (no previous edge)
      assertTrue(graph.targets("A").containsKey("B"));
      assertEquals(5, (int) graph.targets("A").get("B"));
  }

  @Test
  public void testSetEdgeWeight() {
      Graph<String> graph = emptyInstance();
      graph.add("A");
      graph.add("B");
      graph.set("A", "B", 5);
      assertEquals(5, graph.set("A", "B", 10)); // Setting a new weight, should return the previous weight (5)
      assertEquals(10, (int) graph.targets("A").get("B")); // Updated weight
  }

  @Test
  public void testRemoveVertex() {
      Graph<String> graph = emptyInstance();
      graph.add("A");
      assertTrue(graph.remove("A"));
      assertFalse(graph.vertices().contains("A"));
  }

  @Test
  public void testRemoveNonExistentVertex() {
      Graph<String> graph = emptyInstance();
      assertFalse(graph.remove("A")); // Removing a non-existent vertex should return false
  }

}
