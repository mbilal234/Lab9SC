/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of Graph.
 * 
 * <p>
 * PS2 instructions: you MUST use the provided rep.
 */

// ConcreteEdgesGraph class

public class ConcreteEdgesGraph implements Graph<String> {

    private final Set<String> vertices = new HashSet<>();
    private final List<Edge> edges = new ArrayList<>();

    // Abstraction function:
    // Represents a weighted directed graph with labeled vertices and edges.
    // Representation invariant:
    // Edges in the 'edges' list must connect vertices in 'vertices'.
    // Safety from rep exposure:
    // All fields are private. Defensive copies are used where needed.

    // TODO constructor
    // Constructor
    public ConcreteEdgesGraph() {
        // TODO: Initialize the graph if needed
        // (no specific initialization needed for this example)
    }

    // TODO checkRep

    // Representation invariant check
    private void checkRep() {
        // TODO: Check and assert the representation invariant
        for (Edge edge : edges) {
            assert vertices.contains(edge.getSource()) : "Source vertex not in vertices set";
            assert vertices.contains(edge.getTarget()) : "Target vertex not in vertices set";
        }
    }

    @Override
    public boolean add(String vertex) {
        // TODO: Implement the add method
        if (vertices.add(vertex)) {
            checkRep();
            return true;
        }
        return false;
    }

    @Override
    public int set(String source, String target, int weight) {
        // TODO: Implement the set method
        Edge newEdge = new Edge(source, target, weight);
        int previousWeight = 0;

        // Check if the edge already exists
        for (Edge edge : edges) {
            if (edge.getSource().equals(source) && edge.getTarget().equals(target)) {
                previousWeight = edge.getWeight();
                edges.remove(edge);
                break;
            }
        }

        // Add the new edge
        edges.add(newEdge);
        vertices.add(source);
        vertices.add(target);

        checkRep();
        return previousWeight;
    }

    @Override
    public boolean remove(String vertex) {
        // TODO: Implement the remove method
        if (vertices.remove(vertex)) {
            // Remove edges connected to the vertex
            edges.removeIf(edge -> edge.getSource().equals(vertex) || edge.getTarget().equals(vertex));
            checkRep();
            return true;
        }
        return false;
    }

    @Override
    public Set<String> vertices() {
        // TODO: Implement the vertices method
        return new HashSet<>(vertices);
    }

    @Override
    public Map<String, Integer> sources(String target) {
        // TODO: Implement the sources method
        Map<String, Integer> sourceMap = new HashMap<>();
        for (Edge edge : edges) {
            if (edge.getTarget().equals(target)) {
                sourceMap.put(edge.getSource(), edge.getWeight());
            }
        }
        return sourceMap;
    }

    @Override
    public Map<String, Integer> targets(String source) {
        // TODO: Implement the targets method
        Map<String, Integer> targetMap = new HashMap<>();
        for (Edge edge : edges) {
            if (edge.getSource().equals(source)) {
                targetMap.put(edge.getTarget(), edge.getWeight());
            }
        }
        return targetMap;
    }

    // TODO toString()
    @Override
    public String toString() {
        // TODO: Implement the toString method
        StringBuilder result = new StringBuilder();
        for (Edge edge : edges) {
            result.append(edge.toString()).append("\n");
        }
        return result.toString();
    }

}

/**
 * TODO specification
 * Immutable.
 * This class is internal to the rep of ConcreteEdgesGraph.
 * 
 * <p>
 * PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Edge {
    // TODO fields
    private final String source;
    private final String target;
    private final int weight;

    // Abstraction function:
    // Represents a directed edge from source to target with a given weight.
    // Representation invariant:
    // None (No checks are needed as this class is immutable).
    // Safety from rep exposure:
    // All fields are private and final.

    // TODO constructor
    // Constructor
    public Edge(String source, String target, int weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
    }

    // Observer methods
        // TODO methods
    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }

    public int getWeight() {
        return weight;
    }

    // TODO toString() method
    @Override
    public String toString() {
        return String.format("(%s -> %s, weight: %d)", source, target, weight);
    }

    // hashCode() method
    @Override
    public int hashCode() {
        int result = source.hashCode();
        result = 31 * result + target.hashCode();
        result = 31 * result + weight;
        return result;
    }

    // equals() method
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Edge edge = (Edge) obj;
        return weight == edge.weight &&
                source.equals(edge.source) &&
                target.equals(edge.target);
    }

    // TODO checkRep
    // Check representation
    private void checkRep() {
        // No invariants to check in an immutable class
    }





}
