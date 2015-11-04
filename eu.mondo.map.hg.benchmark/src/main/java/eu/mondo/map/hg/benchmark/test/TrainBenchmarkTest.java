/*******************************************************************************
 * Copyright (c) 2010-2015, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/

package eu.mondo.map.hg.benchmark.test;

import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.apache.commons.cli.ParseException;
import org.junit.Rule;
import org.junit.rules.ErrorCollector;

import eu.mondo.map.hg.benchmark.BenchmarkLogic;
import eu.mondo.map.hg.constants.Query;
import eu.mondo.map.hg.constants.ScenarioConstants;
import eu.mondo.sam.core.results.BenchmarkResult;
import eu.mondo.sam.core.results.JsonSerializer;
import eu.mondo.sam.core.results.MetricResult;
import eu.mondo.sam.core.results.PhaseResult;

public abstract class TrainBenchmarkTest {

	@Rule
	public ErrorCollector collector = new ErrorCollector();

	protected TestBenchmarkInitializer<?> bi;

	public BenchmarkLogic initialize(final Query query, final String tool,
			final ScenarioConstants scenario) throws IOException {
		return bi.initializeBenchmark(query, scenario);
	}

	protected void testQuery(final Query query, final ScenarioConstants scenario,
			final int expectedResultSize) throws ParseException, IOException {
		final BenchmarkLogic bl = bi.initializeBenchmark(query, scenario);
		runQuery(bl, expectedResultSize);
	}

	private void runQuery(final BenchmarkLogic bl, final long expectedResultSize) throws IOException {
		JsonSerializer.setResultPath("../results/test/");
		final BenchmarkResult br = bl.runBenchmark();
		long lastResultSize = 0;
		for (final PhaseResult pr : br.getPhaseResults()) {
			final String name = pr.getPhaseName();
			if ("Check".equals(name) || "Recheck".equals(name)) {
				for (final MetricResult m : pr.getMetrics()) {
					if ("Matches".equals(m.getName())) {
						lastResultSize = Long.parseLong(m.getValue());
					}
				}
			}
		}
		collector.checkThat(lastResultSize, equalTo(expectedResultSize));
	}
}
