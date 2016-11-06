package hu.bme.mit.mba.modelmetrics.tests.emf;

import hu.bme.mit.mba.modelmetrics.tests.PairwiseMultiplexityTest;
import hu.bme.mit.mba.tests.model.TestModelTypes;

public class EmfPairwiseMultiplexityTest extends PairwiseMultiplexityTest {

    @Override
    protected void initModel(TestModelTypes modelType) {
        testModel = modelType.init();
        adapter = EmfAdapterInitializer.getAdapter(testModel);
    }

}
