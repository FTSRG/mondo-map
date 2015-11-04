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
package eu.mondo.map.hg.benchmark.jena.match;

import com.hp.hpl.jena.rdf.model.Resource;

import eu.mondo.map.hg.benchmark.jena.driver.ResourceComparator;
import eu.mondo.map.hg.benchmark.matches.MatchComparator;

public class JenaMatchComparator extends MatchComparator<JenaMatch, Resource> {

	protected ResourceComparator rc = new ResourceComparator();

	@Override
	public int compare(final JenaMatch o1, final JenaMatch o2) {
		final Resource[] m1 = o1.toArray();
		final Resource[] m2 = o2.toArray();
		return compareArrays(m1, m2, rc);
	}

}
