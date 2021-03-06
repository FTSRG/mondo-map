package hu.bme.mit.ga.metrics.impl.simple;

import hu.bme.mit.ga.base.data.ScalarData;
import hu.bme.mit.ga.adapters.GraphAdapter;
import hu.bme.mit.ga.metrics.AbstractGraphMetric;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumberOfEdges extends AbstractGraphMetric<ScalarData<Integer>> {

    public NumberOfEdges() {
        super("NumberOfEdges", new ScalarData<>());
    }

    @Override
    protected <N, T> void evaluateAll(GraphAdapter<N, T> adapter) {
        data.setValue(adapter.getIndexer().getNumberOfEdges());
    }

    @Override
    public <N, T> void evaluate(GraphAdapter<N, T> adapter, N element) {
        data.setValue(adapter.getIndexer().getDegree(element));
    }

    @Override
    public List<Map<String, Object>> getTsvMaps(String[] header) {
        final List<Map<String, Object>> values = new ArrayList<>();
        Map<String, Object> value = new HashMap<>();
        value.put(header[0], "NumberOfEdges");
        value.put(header[1], null);
        value.put(header[2], null);
        value.put(header[3], data.getValue());
        values.add(value);
        return values;
    }

}
