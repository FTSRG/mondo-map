package eu.mondo.map.modelmetrics.impl.simple;

import eu.mondo.map.base.metrics.ScalarMetric;
import eu.mondo.map.base.util.MathUtils;
import eu.mondo.map.modeladapters.ModelAdapter;
import eu.mondo.map.modelmetrics.ModelEvaluator;
import eu.mondo.map.modelmetrics.impl.typed.TypedDegreeList;

public class NumberOfEdges extends ScalarMetric<Integer> implements ModelEvaluator {

	public NumberOfEdges() {
		super("NumberOfEdges");
	}

	// public void calculate(final DegreeList degreeList) {
	// value = MathUtils.sumInt(degreeList.getValues());
	// }

	public void calculate(final TypedDegreeList typedDegreeList) {
		clear();
		for (String key : typedDegreeList.getValues().keySet()) {
			value += MathUtils.sumInt(typedDegreeList.getValues().get(key));
		}
		value /= 2;
	}

	// public void calculate(final Network<?> network) {
	// value = network.getNumberOfEdges();
	// }

	@Override
	public void clear() {
		value = 0;
	}

	@Override
	/**
	 * Calculates the number of nodes in the graph.
	 */
	public <M, N, T> void evaluate(ModelAdapter<M, N, T> adapter) {
		value = adapter.getNumberOfEdges();
	}

	@Override
	/**
	 * Calculates the number of edges which belong to the element.
	 */
	public <M, N, T> void evaluate(ModelAdapter<M, N, T> adapter, N element) {
		value = adapter.getDegree(element);
	}

}
