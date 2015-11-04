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

package eu.mondo.map.hg.generator.rdf;

import eu.mondo.map.hg.generator.SyntheticGenerator;
import eu.mondo.map.hg.generator.rdf.config.RDFGeneratorConfig;

public class RDFGeneratorMain {

	public static void main(final String[] args) throws Exception {
		final RDFGeneratorConfig generatorConfig = new RDFGeneratorConfig(args);
		final RDFGeneratorFactory factory = new RDFGeneratorFactory(generatorConfig);
		final SyntheticGenerator generator = factory.getSyntheticGenerator();
		generator.generate();
	}

}
