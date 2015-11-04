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

package eu.mondo.map.hg.benchmark.rdf;

import org.apache.commons.cli.ParseException;

import eu.mondo.map.hg.benchmark.config.BenchmarkConfig;
import eu.mondo.map.hg.benchmark.config.ModificationMethod;
import eu.mondo.map.hg.constants.ModelType;
import eu.mondo.map.hg.constants.Query;
import eu.mondo.map.hg.constants.ScenarioConstants;

public class RDFBenchmarkConfig extends BenchmarkConfig {

	protected boolean inferencing;

	public RDFBenchmarkConfig(final String[] args, final String className) throws ParseException {
		super(args, className);
	}

	public RDFBenchmarkConfig(final String className, final ScenarioConstants scenario, final int size,
			final int runIndex, final Query query, final int iterationCount,
			final ModificationMethod modificationMethod, final long modificationConstant,
			final ModelType modelType) {
		super(className, scenario, size, runIndex, query, iterationCount, modificationMethod,
				modificationConstant, modelType);
	}

	@Override
	protected void initOptions() {
		super.initOptions();

		options.addOption("inferencing", true, "RDF: type of inference");
	}

	@Override
	public void processArguments(final String[] args) throws ParseException {
		super.processArguments(args);

		inferencing = cmd.hasOption("inferencing");
	}

	public boolean isInferencing() {
		return inferencing;
	}
}
