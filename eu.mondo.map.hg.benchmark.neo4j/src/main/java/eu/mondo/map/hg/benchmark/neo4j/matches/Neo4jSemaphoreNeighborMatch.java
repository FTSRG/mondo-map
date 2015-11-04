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
package eu.mondo.map.hg.benchmark.neo4j.matches;

import static eu.mondo.map.hg.constants.railway.RailwayQueryConstants.VAR_ROUTE1;
import static eu.mondo.map.hg.constants.railway.RailwayQueryConstants.VAR_ROUTE2;
import static eu.mondo.map.hg.constants.railway.RailwayQueryConstants.VAR_SEMAPHORE;
import static eu.mondo.map.hg.constants.railway.RailwayQueryConstants.VAR_SENSOR1;
import static eu.mondo.map.hg.constants.railway.RailwayQueryConstants.VAR_SENSOR2;
import static eu.mondo.map.hg.constants.railway.RailwayQueryConstants.VAR_TE1;
import static eu.mondo.map.hg.constants.railway.RailwayQueryConstants.VAR_TE2;

import java.util.Map;

import org.neo4j.graphdb.Node;

import eu.mondo.map.hg.benchmark.matches.railway.SemaphoreNeighborMatch;

public class Neo4jSemaphoreNeighborMatch extends Neo4jMatch implements SemaphoreNeighborMatch {

	public Neo4jSemaphoreNeighborMatch(final Map<String, Object> match) {
		super(match);
	}

	@Override
	public Node getSemaphore() {
		return (Node) match.get(VAR_SEMAPHORE);
	}

	@Override
	public Node getRoute1() {
		return (Node) match.get(VAR_ROUTE1);
	}

	@Override
	public Node getRoute2() {
		return (Node) match.get(VAR_ROUTE2);
	}

	@Override
	public Node getSensor1() {
		return (Node) match.get(VAR_SENSOR1);
	}

	@Override
	public Node getSensor2() {
		return (Node) match.get(VAR_SENSOR2);
	}

	@Override
	public Node getTe1() {
		return (Node) match.get(VAR_TE1);
	}

	@Override
	public Node getTe2() {
		return (Node) match.get(VAR_TE2);
	}

	@Override
	public Node[] toArray() {
		return new Node[] { getSemaphore(), getRoute1(), getRoute2(), getSensor1(), getSensor2(),
				getTe1(), getTe2() };
	}

}
