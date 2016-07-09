package eu.mondo.map.modelanalyzer.incr;

import com.google.common.base.Preconditions;

import eu.mondo.map.modeladapters.ModelAdapter;
import eu.mondo.map.modelanalyzer.ModelAnalyzer;
import eu.mondo.map.modelmetrics.ModelEvaluator;
import eu.mondo.map.modelmetrics.impl.ModelMetrics;
import eu.mondo.map.modelmetrics.incr.IncrementalModelEvaluator;

public class IncrementalModelAnalyzer extends ModelAnalyzer<IncrementalModelEvaluator> {

	@Override
	public IncrementalModelAnalyzer clear() {
		super.clear();
		return this;
	}

	@Override
	public IncrementalModelAnalyzer use(ModelMetrics metric) {
		super.use(metric);
		return this;
	}

	@Override
	public IncrementalModelAnalyzer use(ModelMetrics metric, String name) {
		super.use(metric, name);
		return this;
	}

	@Override
	protected void useMetric(ModelMetrics metric, String name) {
		checkNewMetric(metric);
		ModelEvaluator metricObj = metric.instantiate();
		Preconditions.checkState(metricObj instanceof IncrementalModelEvaluator,
				"Metric " + metric.toString() + " must implement IncrementalModelEvaluator.");

		addMetric(metric, (IncrementalModelEvaluator) metricObj, name);
	}

	@Override
	public IncrementalModelAnalyzer useAll() {
		super.useAll();
		return this;
	}

	@Override
	public IncrementalModelAnalyzer omit(ModelMetrics metric) {
		super.omit(metric);
		return this;
	}

	@Override
	public IncrementalModelAnalyzer calculate(ModelAdapter<?, ?, ?> adapter) {
		super.calculate(adapter);
		return this;
	}

	@Override
	public IncrementalModelAnalyzer calculate(ModelAdapter<?, ?, ?> adapter, ModelMetrics metric) {
		super.calculate(adapter, metric);
		return this;
	}

}
