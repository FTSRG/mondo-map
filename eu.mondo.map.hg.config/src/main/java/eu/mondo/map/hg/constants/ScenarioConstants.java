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
package eu.mondo.map.hg.constants;

public enum ScenarioConstants {
	ANALYZE("Analyze"), // 
	BATCH("Batch"), //
	DESCRIBE("Describe"), //
	INJECT("Inject"), //
	REPAIR("Repair");

	private String name;

	ScenarioConstants(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
