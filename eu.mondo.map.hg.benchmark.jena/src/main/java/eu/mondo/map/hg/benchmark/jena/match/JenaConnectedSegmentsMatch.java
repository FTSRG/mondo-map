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

import static eu.mondo.map.hg.constants.railway.RailwayQueryConstants.VAR_SEGMENT1;
import static eu.mondo.map.hg.constants.railway.RailwayQueryConstants.VAR_SEGMENT2;
import static eu.mondo.map.hg.constants.railway.RailwayQueryConstants.VAR_SEGMENT3;
import static eu.mondo.map.hg.constants.railway.RailwayQueryConstants.VAR_SEGMENT4;
import static eu.mondo.map.hg.constants.railway.RailwayQueryConstants.VAR_SEGMENT5;
import static eu.mondo.map.hg.constants.railway.RailwayQueryConstants.VAR_SEGMENT6;
import static eu.mondo.map.hg.constants.railway.RailwayQueryConstants.VAR_SENSOR;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.rdf.model.Resource;

import eu.mondo.map.hg.benchmark.matches.railway.ConnectedSegmentsMatch;

public class JenaConnectedSegmentsMatch extends JenaMatch implements ConnectedSegmentsMatch {

	public JenaConnectedSegmentsMatch(final QuerySolution qs) {
		super(qs);
	}

	@Override
	public Resource getSensor() {
		return qs.getResource(VAR_SENSOR);
	}

	@Override
	public Resource getSegment1() {
		return qs.getResource(VAR_SEGMENT1);
	}

	@Override
	public Resource getSegment2() {
		return qs.getResource(VAR_SEGMENT2);
	}

	@Override
	public Resource getSegment3() {
		return qs.getResource(VAR_SEGMENT3);
	}

	@Override
	public Resource getSegment4() {
		return qs.getResource(VAR_SEGMENT4);
	}

	@Override
	public Resource getSegment5() {
		return qs.getResource(VAR_SEGMENT5);
	}

	@Override
	public Resource getSegment6() {
		return qs.getResource(VAR_SEGMENT6);
	}

	@Override
	public Resource[] toArray() {
		return new Resource[] { getSensor(), getSegment1(), getSegment2(), getSegment3(),
				getSegment4(), getSegment5(), getSegment6() };
	}

}
