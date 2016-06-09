package eu.mondo.map.core.metrics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AggregatedMetric<M extends ListMetric<? extends Number>> extends ScalarMetric<Double> {

	protected String listMetricName;
	boolean useDefault;

	public AggregatedMetric() {
		super("AggregatedMetric");
		useDefault = true;
	}

	public void calculateAverage(final M list) {
		double sum = 0.0;
		for (int i = 0; i < list.size(); i++) {
			sum += list.get(i).doubleValue();
		}
		value = sum / list.size();
		listMetricName = "Average";
	}

	@Override
	public void clear() {
		value = 0.0;
	}

	@Override
	public List<PublishedMetric> resolve() {
		List<PublishedMetric> metrics = new ArrayList<>();
		PublishedMetric metric = new PublishedMetric(name).setInstance(listMetricName).setValue(value);
		metrics.add(metric);
		return metrics;
	}

	// protected String resolveName() {
	// if (useDefault) {
	// return String.format("%s-%s", name, listMetricName);
	// } else {
	// return name;
	// }
	// }

	@Override
	public void setName(final String name) {
		super.setName(name);
		useDefault = false;
	}

	@Override
	public void calculate(Iterator<Object> iterator) {
		// TODO Auto-generated method stub

	}

	@Override
	public void calculate(Object node) {
		// TODO Auto-generated method stub

	}

}
