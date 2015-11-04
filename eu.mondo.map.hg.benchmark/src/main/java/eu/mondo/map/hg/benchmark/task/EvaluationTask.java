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

package eu.mondo.map.hg.benchmark.task;

import java.util.Collection;
import java.util.concurrent.Callable;

import eu.mondo.map.hg.benchmark.checker.Checker;

public class EvaluationTask<M> implements Callable<Collection<M>> {

	protected Checker<M> checker;

	public EvaluationTask(Checker<M> checker) {
		this.checker = checker;
	}

	@Override
	public Collection<M> call() throws Exception {
		return checker.check();
	}

}
