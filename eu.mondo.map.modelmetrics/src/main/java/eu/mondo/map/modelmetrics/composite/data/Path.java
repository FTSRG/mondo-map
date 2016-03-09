package eu.mondo.map.modelmetrics.composite.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import eu.mondo.map.core.graph.Node;

public class Path<N>  {

	protected List<Node<N>> path;
	protected int depth;

	public Path() {
		path = new ArrayList<>();
	}

	public Path(int depth) {
		path = new ArrayList<>();
		this.depth = depth;
	}

	public Path(Path<N> path2) {
		this.path = new ArrayList<>();
		this.depth = path2.depth;
		this.path.addAll(path2.getPath());
	}

	public boolean isEmpty() {
		return path.isEmpty();
	}

	public List<Node<N>> getPath() {
		return path;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int size() {
		return path.size();
	}

	public boolean add(Node<N> e) {
		return path.add(e);
	}

	public boolean addAll(Collection<? extends Node<N>> c) {
		return path.addAll(c);
	}

	@Override
	public String toString() {
		return "Path [path=" + path + ", depth=" + depth + "]";
	}

}
