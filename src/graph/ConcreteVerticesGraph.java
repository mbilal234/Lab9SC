/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import java.util.*;

/**
 * An implementation of Graph.
 *
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteVerticesGraph implements Graph<String> {

    private final List<Vertex> vertices = new ArrayList<>();

    // Abstraction function:
    //   Represents a mutable weighted directed graph with labeled vertices.
    //   Each Vertex in the 'vertices' list represents a vertex in the graph.

    // Representation invariant:
    //   - Vertices in 'vertices' must have distinct labels.
    //   - Edges must have non-negative weights.
    //   - No self-loops (edges where source and target vertices are the same).

    // Safety from rep exposure:
    //   - 'vertices' is marked as private final.
    //   - Accessor methods ensure safe access to internal data structures.

    // Constructor
    public ConcreteVerticesGraph() {

    }

    // Check representation invariant
    private void checkRep() {
        // Check for distinct labels
        Set<String> labels = new HashSet<>();
        for (Vertex v : vertices) {
            if (!labels.add(v.getLabel())) {
                throw new RuntimeException("Duplicate vertex label found.");
            }
        }
        // Check for other representation invariants
        for (Vertex v : vertices) {
            for (Map.Entry<Vertex, Integer> entry : v.getEdges().entrySet()) {
                if (entry.getValue() < 0) {
                    throw new RuntimeException("Edge weight is negative.");
                }
                if (entry.getKey() == v) {
                    throw new RuntimeException("Self-loop detected.");
                }
            }
        }
    }

    @Override
    public boolean add(String vertex) {
        for (Vertex v : vertices) {
            if (v.getLabel().equals(vertex)) {
                return false; // Vertex with the same label already exists
            }
        }
        vertices.add(new Vertex(vertex));
        checkRep();
        return true;
    }

    @Override
    public int set(String source, String target, int weight) {
        Vertex sourceVertex = findVertex(source);
        Vertex targetVertex = findVertex(target);

        if (sourceVertex == null || targetVertex == null) {
            return 0; // Either source or target vertex not found
        }

        int previousWeight = sourceVertex.getEdges().getOrDefault(targetVertex, 0);

        if (weight != 0) {
            sourceVertex.addEdge(targetVertex, weight);
        } else {
            sourceVertex.removeEdge(targetVertex);
        }

        checkRep();
        return previousWeight;
    }

    @Override
    public boolean remove(String vertex) {
        Iterator<Vertex> iterator = vertices.iterator();
        while (iterator.hasNext()) {
            Vertex v = iterator.next();
            if (v.getLabel().equals(vertex)) {
                iterator.remove();
                checkRep();
                return true;
            }
        }
        return false; // Vertex not found
    }

    @Override
    public Set<String> vertices() {
        Set<String> result = new HashSet<>();
        for (Vertex v : vertices) {
            result.add(v.getLabel());
        }
        checkRep();
        return result;
    }

    @Override
    public Map<String, Integer> sources(String target) {
        Map<String, Integer> result = new HashMap<>();
        Vertex targetVertex = findVertex(target);

        if (targetVertex == null) {
            return result; // Target vertex not found
        }

        for (Vertex v : vertices) {
            int weight = v.getEdges().getOrDefault(targetVertex, 0);
            if (weight != 0) {
                result.put(v.getLabel(), weight);
            }
        }

        checkRep();
        return result;
    }

    @Override
    public Map<String, Integer> targets(String source) {
        Map<String, Integer> result = new HashMap<>();
        Vertex sourceVertex = findVertex(source);

        if (sourceVertex == null) {
            return result; // Source vertex not found
        }

        for (Map.Entry<Vertex, Integer> entry : sourceVertex.getEdges().entrySet()) {
            result.put(entry.getKey().getLabel(), entry.getValue());
        }

        checkRep();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Vertex v : vertices) {
            sb.append(v.toString()).append("\n");
        }
        return sb.toString();
    }

    // Helper method to find a vertex by label
    private Vertex findVertex(String label) {
        for (Vertex v : vertices) {
            if (v.getLabel().equals(label)) {
                return v;
            }
        }
        return null;
    }
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

    public int getWeightTo(Vertex target) {
        return edges.get(target);
    }

    public boolean hasTarget(Vertex target) {
        return edges.containsKey(target);
    }

    public Map<Vertex, Integer> getSources() {
        return edges;
    }

    public Map<Vertex, Integer> getTargets() {
        return  edges;
    }
}
