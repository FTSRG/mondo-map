package hu.bme.mit.ga.metrics.tests;

import hu.bme.mit.ga.base.data.BaseData;
import hu.bme.mit.ga.adapters.GraphAdapter;
import hu.bme.mit.ga.adapters.tests.TestGraphAdapter;
import hu.bme.mit.ga.analyzer.GraphAnalyzer;
import hu.bme.mit.ga.metrics.GraphMetric;
import hu.bme.mit.ga.metrics.impl.GraphMetricsEnum;
import hu.bme.mit.ga.tests.graph.TestGraph;
import hu.bme.mit.ga.tests.graph.TestGraphInstances;
import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class GraphMetricTest<D extends BaseData> {

    protected final Logger logger = Logger.getLogger(this.getClass());
    protected TestGraph testGraph;
    protected GraphMetric metric;
    protected D data;
    protected GraphAdapter<?, ?> adapter;

    public abstract GraphMetricsEnum getMetric();

    @DataProvider
    public Object[][] data() {
        List<Object[]> casesList = new ArrayList<>();
        for (TestGraphInstances type : TestGraphInstances.values()) {
            casesList.add(testCase(type));
        }

        Object[][] casesArray = new Object[casesList.size()][2];
        for (int i = 0; i < casesList.size(); i++) {
            casesArray[i] = casesList.get(i);
        }
        return casesArray;
    }

    protected abstract Object[] testCase(TestGraphInstances modelType);

    @Test(dataProvider = "data")
    public void testEvaluation(TestGraphInstances modelType, Consumer<D> checker)
        throws InstantiationException, IllegalAccessException, IOException {
        metric = getMetric().instantiate();
        initData();
        initModel(modelType);

        evaluateAndCheck(checker);
    }

    @Test(dataProvider = "data")
    public void testEvaluationWithAnalyzer(TestGraphInstances modelType, Consumer<D> checker)
        throws InstantiationException, IllegalAccessException, IOException {
        initModel(modelType);
        GraphAnalyzer analyzer = new GraphAnalyzer();
        analyzer.use(getMetric());
        metric = analyzer.getMetric(getMetric());
        initData();

        evaluateAndCheck(checker, analyzer);
    }

    protected void skippedModel(TestGraphInstances modelType) {
        logger.warn("The model " + modelType + " is not evaluated");
    }

    protected void initModel(TestGraphInstances modelType) throws IOException {
        testGraph = modelType.init();
        adapter = new TestGraphAdapter();
        ((TestGraphAdapter) adapter).init(testGraph);
    }

    @SuppressWarnings("unchecked")
    protected void initData() {
        data = (D) metric.getData();
    }

    protected void evaluateAndCheck(Consumer<D> checker) {
        metric.evaluate(adapter);
        checker.accept(data);
    }

    protected void evaluateAndCheck(Consumer<D> checker, GraphAnalyzer analyzer) {
        analyzer.evaluate(adapter);
        checker.accept(data);
    }

}
