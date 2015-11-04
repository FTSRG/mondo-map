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

package eu.mondo.map.hg.benchmark.neo4j.checkers;

import static eu.mondo.map.hg.benchmark.neo4j.constants.Neo4jConstants.labelSensor;
import static eu.mondo.map.hg.benchmark.neo4j.constants.Neo4jConstants.labelSwitch;
import static eu.mondo.map.hg.benchmark.neo4j.constants.Neo4jConstants.relationshipTypeSensor;
import static eu.mondo.map.hg.constants.railway.RailwayQueryConstants.VAR_SW;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Transaction;

import eu.mondo.map.hg.benchmark.neo4j.driver.Neo4jDriver;
import eu.mondo.map.hg.benchmark.neo4j.matches.Neo4jSwitchSensorMatch;

public class Neo4jCoreSwitchSensorChecker extends Neo4jCoreChecker<Neo4jSwitchSensorMatch> {

	public Neo4jCoreSwitchSensorChecker(final Neo4jDriver neoDriver) {
		super(neoDriver);
	}

	@Override
	public Collection<Neo4jSwitchSensorMatch> check() {
		final Collection<Neo4jSwitchSensorMatch> matches = new HashSet<>();

		final GraphDatabaseService graphDb = driver.getGraphDb();
		try (Transaction tx = graphDb.beginTx()) {
			// (switch:Switch)-[:sensor]->(Sensor) NAC
			final ResourceIterator<Node> switches = graphDb.findNodes(labelSwitch);
			while (switches.hasNext()) {
				final Node sw = switches.next();
				final Iterable<Relationship> relationshipSensors = sw.getRelationships(
						Direction.OUTGOING, relationshipTypeSensor);

				boolean hasSensor = false;
				for (final Relationship relationshipSensor : relationshipSensors) {
					final Node sensor = relationshipSensor.getEndNode();
					if (sensor.hasLabel(labelSensor)) {
						hasSensor = true;
						break;
					}
				}

				if (!hasSensor) {
					final Map<String, Object> match = new HashMap<>();
					match.put(VAR_SW, sw);
					matches.add(new Neo4jSwitchSensorMatch(match));
				}
			}
		}

		return matches;
	}
}
