package hu.bme.mit.mba.modelmetrics.impl.typed;

import hu.bme.mit.mba.base.data.MapData;
import hu.bme.mit.mba.modeladapters.ModelAdapter;
import hu.bme.mit.mba.modeladapters.TypedModelAdapter;
import hu.bme.mit.mba.modelmetrics.AbstractModelMetric;

public class NumberOfTypedEdges extends AbstractModelMetric<MapData<String, Integer>> {

    public NumberOfTypedEdges() {
        super("NumberOfTypedEdges", new MapData<>());
    }

    // public void calculate(final Network<?> network) {
    // clear();
    // for (String dimension : network.getNodesOnDimensions().keySet()) {
    // int sumOfEdges = 0;
    // for (Node<?> node : network.getNodesOnDimensions().get(dimension)) {
    // sumOfEdges += node.getNumberOfNeighbors(dimension);
    // }
    // sumOfEdges /= 2;
    // typedValues.put(dimension, sumOfEdges);
    // }
    // }

    @Override
    protected <N, T> void evaluateAll(ModelAdapter<N, T> adapter) {
        TypedModelAdapter<N, T> typedAdapter = castAdapter(adapter);
        for (T type : typedAdapter.getTypes()) {
            int sumOfEdges = 0;
            for (N node : typedAdapter.getNodes(type)) {
                sumOfEdges += typedAdapter.getDegree(node, type);
            }
            sumOfEdges /= 2;
            data.put(type.toString(), sumOfEdges);
        }
    }

}
