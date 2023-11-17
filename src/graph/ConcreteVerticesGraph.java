/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import java.util.*;

/**
 * An implementation of Graph.
 * 
 * <p>
 * PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteVerticesGraph implements Graph<String> {

    private final List<Vertex> vertices = new ArrayList<>();

    // Abstraction function:
    // TODO
    // Representation invariant:
    // TODO
    // Safety from rep exposure:
    // TODO

    // TODO constructor

    // TODO checkRep

    @Override
    public boolean add(String vertex) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public int set(String source, String target, int weight) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public boolean remove(String vertex) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public Set<String> vertices() {
        throw new RuntimeException("not implemented");
    }

    @Override
    public Map<String, Integer> sources(String target) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public Map<String, Integer> targets(String source) {
        throw new RuntimeException("not implemented");
    }

    // TODO toString()

}

/**
 * TODO specification
 * Mutable.
 * This class is internal to the rep of ConcreteVerticesGraph.
 *
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Vertex {

    private final String label;
    private final Map<Vertex, Integer> edges;

    // Abstraction function:
    //   Represents a mutable vertex with a label and outgoing edges.
    //   The label is a unique identifier for the vertex.
    //   The edges map stores the target vertices and their associated weights.

    // Representation invariant:
    //   - 'label' must not be null.
    //   - 'edges' must not be null.
    //   - All edge weights must be non-negative.

    // Safety from rep exposure:
    //   - 'label' and 'edges' are marked as private final.
    //   - 'edges' is an immutable view of the internal data structure.

    // Constructor
    public Vertex(String label) {
        if (label == null) {
            throw new IllegalArgumentException("Vertex label cannot be null.");
        }
        this.label = label;
        this.edges = new HashMap<>();
        checkRep();
    }

    // Check representation invariant
    private void checkRep() {
        if (label == null) {
            throw new RuntimeException("Vertex label is null.");
        }
        if (edges == null) {
            throw new RuntimeException("Vertex edges map is null.");
        }
        for (Map.Entry<Vertex, Integer> entry : edges.entrySet()) {
            if (entry.getValue() < 0) {
                throw new RuntimeException("Edge weight is negative.");
            }
        }
    }

    public String getLabel() {
        return label;
    }

    public Map<Vertex, Integer> getEdges() {
        return Collections.unmodifiableMap(edges);
    }

    // Methods to add and remove edges
    public void addEdge(Vertex target, int weight) {
        edges.put(target, weight);
        checkRep();
    }

    public void removeEdge(Vertex target) {
        edges.remove(target);
        checkRep();
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "label='" + label + '\'' +
                ", edges=" + edges +
                '}';
    }
}
