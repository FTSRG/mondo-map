package analyzer;

import hu.bme.mit.ga.adapters.csv.CsvGraphAdapter;
import hu.bme.mit.ga.metrics.AbstractGraphMetric;
import hu.bme.mit.ga.metrics.GraphMetric;
import hu.bme.mit.ga.metrics.impl.simple.Degrees;
import hu.bme.mit.ga.metrics.impl.simple.Density;
import hu.bme.mit.ga.metrics.impl.simple.NumberOfEdges;
import hu.bme.mit.ga.metrics.impl.simple.NumberOfNodes;
import hu.bme.mit.ga.metrics.impl.typed.DimensionActivity;
import hu.bme.mit.ga.metrics.impl.typed.DimensionalDegree;
import hu.bme.mit.ga.metrics.impl.typed.EdgeDimensionConnectivity;
import hu.bme.mit.ga.metrics.impl.typed.MultiplexParticipationCoefficient;
import hu.bme.mit.ga.metrics.impl.typed.NodeActivity;
import hu.bme.mit.ga.metrics.impl.typed.NumberOfTypedEdges;
import hu.bme.mit.ga.metrics.impl.typed.PairwiseMultiplexity;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvMapWriter;
import org.supercsv.io.ICsvMapWriter;
import org.supercsv.prefs.CsvPreference;
import org.testng.annotations.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CsvTest {

    private static CellProcessor[] getProcessors() {

        final CellProcessor[] processors = new CellProcessor[]{
            new NotNull(),
            new Optional(),
            new Optional(),
            new NotNull()
        };

        return processors;
    }

    @Test
    public void test() throws IOException {
        final String filename = "modelmetrics.tsv";
        final String[] header = new String[]{"Category", "Instance", "Index", "Value"};
        final List<AbstractGraphMetric> modelMetrics = new ArrayList<>();

        // the adapter represents a bridge between model and metrics
        CsvGraphAdapter adapter = new CsvGraphAdapter(new NotNull());

        // adapter must be initialized, this will create an index which is necessary during the evaluation
        adapter.init("nodes.csv", "edges.csv");
        System.out.println("initialized");

        // calculate metrics
        System.out.println("dd");
        Degrees dd = new Degrees();
        dd.evaluate(adapter);
        modelMetrics.add(dd);
//        showResult(dd);

        System.out.println("pm");
        PairwiseMultiplexity pm = new PairwiseMultiplexity();
        pm.evaluate(adapter);
        showResult(pm);
        modelMetrics.add(pm);

        MultiplexParticipationCoefficient mpc = new MultiplexParticipationCoefficient();
        System.out.println("mpc");
        mpc.evaluate(adapter);
        modelMetrics.add(mpc);
        showResult(mpc);

        System.out.println("dimension activity");
        DimensionActivity dimensionActivity = new DimensionActivity();
        dimensionActivity.evaluate(adapter);
        showResult(dimensionActivity);
        modelMetrics.add(dimensionActivity);

        EdgeDimensionConnectivity edgeDimensionConnectivity = new EdgeDimensionConnectivity();
        edgeDimensionConnectivity.evaluate(adapter);
        showResult(edgeDimensionConnectivity);
        modelMetrics.add(edgeDimensionConnectivity);

        Density d = new Density();
        d.evaluate(adapter);
        modelMetrics.add(d);

        NumberOfNodes numberOfNodes = new NumberOfNodes();
        numberOfNodes.evaluate(adapter);
        modelMetrics.add(numberOfNodes);

        NumberOfEdges numberOfEdges = new NumberOfEdges();
        numberOfEdges.evaluate(adapter);
        modelMetrics.add(numberOfEdges);

        NodeActivity nodeActivity = new NodeActivity();
        nodeActivity.evaluate(adapter);
        modelMetrics.add(nodeActivity);

        NumberOfTypedEdges numberOfTypedEdges = new NumberOfTypedEdges();
        numberOfEdges.evaluate(adapter);
        modelMetrics.add(numberOfTypedEdges);

        DimensionalDegree dimensionalDegree = new DimensionalDegree();
        dimensionalDegree.evaluate(adapter);
        modelMetrics.add(dimensionalDegree);

        writeToTsv(modelMetrics, header, filename);

    }

    private void showResult(GraphMetric metric) {
        System.out.println(String.format("Data of %s : %s", metric.getName(), metric.getData().toString()));
    }

    private void writeToTsv(List<AbstractGraphMetric> metrics, String[] header, String filename) throws IOException {
        ICsvMapWriter mapWriter = null;

        try {
            mapWriter = new CsvMapWriter(new FileWriter(filename),
                CsvPreference.TAB_PREFERENCE);

            final CellProcessor[] processors = getProcessors();

            // write the header
            mapWriter.writeHeader(header);

            for (AbstractGraphMetric metric : metrics) {
                List<Map<String, Object>> mapList = metric.getTsvMaps(header);
                for (Map<String, Object> map : mapList) {
                    mapWriter.write(map, header, processors);
                }
            }

        } finally {
            if (mapWriter != null) {
                mapWriter.close();
            }
        }

    }

}
