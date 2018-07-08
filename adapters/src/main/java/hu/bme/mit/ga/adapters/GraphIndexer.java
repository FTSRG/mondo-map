package hu.bme.mit.ga.adapters;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import org.objenesis.strategy.StdInstantiatorStrategy;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public final class GraphIndexer<N, T> {

    private int numberOfEdges;
    private Map<T, Multimap<N, N>> outgoing = new HashMap<>();
    private Map<T, Multimap<N, N>> incoming = new HashMap<>();
    private Set<T> types = new HashSet<>();
    private Set<N> nodes = new HashSet<>();

    public GraphIndexer() {
    }

    public void persist(final String path) throws FileNotFoundException {
        final Kryo kryo = new Kryo();
        try (final Output output = new Output(new FileOutputStream(path))) {
            kryo.writeObject(output, this);
        }
    }

    public static GraphIndexer fromFile(final String path) throws FileNotFoundException {
        final Kryo kryo = new Kryo();
        kryo.setInstantiatorStrategy(new Kryo.DefaultInstantiatorStrategy(new StdInstantiatorStrategy()));
        try (final Input input = new Input(new FileInputStream(path))) {
            return kryo.readObject(input, GraphIndexer.class);
        }
    }

    public void addEdge(final T type, final N sourceNode, final N targetNode) {
        if (!nodes.contains(sourceNode)) {
            nodes.add(sourceNode);
        }
        if (!nodes.contains(targetNode)) {
            nodes.add(targetNode);
        }
        if (!outgoing.containsKey(type)) {
            types.add(type);
            outgoing.put(type, ArrayListMultimap.create());
            incoming.put(type, ArrayListMultimap.create());
        }
        outgoing.get(type).put(sourceNode, targetNode);
        incoming.get(type).put(targetNode, sourceNode);
        numberOfEdges++;
    }


    public boolean isAdjacentDirected(final N source, final N target) {
        for (T d : types) {
            if (outgoing.get(d).containsEntry(source, target)) {
                return true;
            }
        }
        return false;
    }

    public boolean isAdjacentUndirected(final N source, final N target) {
        return isAdjacentDirected(source, target) || isAdjacentDirected(target, source);
    }

    public boolean isAdjacentDirected(final N source, final N target, final T type) {
        return incoming.get(type).containsEntry(source, target);
    }

    public boolean isAdjacentUndirected(final N source, final N target, final T type) {
        return isAdjacentDirected(source, target, type) || isAdjacentDirected(target, source, type);
    }

    public int getIndegree(N element) {
        int inDegree = 0;
        for (T type : types) {
            inDegree += incoming.get(type).get(element).size();
        }
        return inDegree;
    }

    public int getOutdegree(N element) {
        int outDegree = 0;
        for (T type : types) {
            outDegree += outgoing.get(type).get(element).size();
        }
        return outDegree;
    }

    public int getIndegree(final N element, final T type) {
        return getIncoming(element, type).size();
    }

    public int getOutdegree(final N element, final T type) {
        return getOutgoing(element, type).size();
    }

    public int getDegree(final N element) {
        return getIndegree(element) + getOutdegree(element);
    }

    public int getDegree(final N element, final T type) {
        return getIndegree(element, type) + getOutdegree(element, type);
    }

    public int getNumberOfNodes() {
        return nodes.size();
    }

    public int getNumberOfNodes(final T type) {
        return getNodes(type).size();
    }

    public int getNumberOfEdges() {
        return numberOfEdges;
    }

    public Set<N> getNodes() {
        return nodes;
    }

    public Set<N> getNodes(T type) {
        Set<N> nodeSet = new HashSet<>();
        nodeSet.addAll(incoming.get(type).keySet());
        nodeSet.addAll(outgoing.get(type).keySet());
        return nodeSet;
    }

    public Set<N> getOutgoing(N node, T type) {
        return new HashSet<>(outgoing.get(type).get(node));
    }

    public Set<N> getIncoming(N node, T type) {
        return new HashSet<>(incoming.get(type).get(node));
    }

    public Multimap<N, N> getOutgoing(T type) {
        return outgoing.get(type);
    }

    public Multimap<N, N> getIncoming(T type) {
        return incoming.get(type);
    }

    public Set<N> getNeighbors(final N element, final T type) {
        return Sets.union(getIncoming(element, type), getOutgoing(element, type));
    }

    public Set<N> getNeighbors(final N element) {
        return Sets.union(getIncomingNeighbors(element), getOutgoingNeighbors(element));
    }

    public Set<N> getIncomingNeighbors(final N element) {
        Set<N> neighbors = new HashSet<>();
        for (T type : getTypes()) {
            neighbors.addAll(getIncoming(element, type));
        }
        return neighbors;
    }

    public Set<N> getOutgoingNeighbors(final N element) {
        Set<N> neighbors = new HashSet<>();
        for (T type : getTypes()) {
            neighbors.addAll(getOutgoing(element, type));
        }
        return neighbors;
    }

    public Set<T> getTypes() {
        return types;
    }

    public Set<T> getTypes(final N element) {
        Set<T> types = new HashSet<>();
        for (T type : getTypes()) {
            if (!getOutgoing(element, type).isEmpty() || !getIncoming(element, type).isEmpty()) {
                types.add(type);
            }
        }
        return types;
    }

    public int getNumberOfTypes() {
        return getTypes().size();
    }

    public int getNumberOfTypes(final N element) {
        return getTypes(element).size();
    }

    public Iterator<N> getModelIterator() {
        return getNodes().iterator();
    }

}