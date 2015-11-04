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

package eu.mondo.map.hg.benchmark.rdf.analyzer;

import java.util.ListIterator;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.sparql.core.TriplePath;
import com.hp.hpl.jena.sparql.syntax.ElementFilter;
import com.hp.hpl.jena.sparql.syntax.ElementPathBlock;
import com.hp.hpl.jena.sparql.syntax.ElementVisitorBase;
import com.hp.hpl.jena.sparql.syntax.ElementWalker;

import eu.mondo.map.analysis.QueryAnalyzer;
import eu.mondo.map.analysis.metrics.queries.QueryMetric;

public class RDFQueryAnalyzer extends QueryAnalyzer {

	protected String queryString;

	@Override
	protected void calculateMetrics() {
		final QueryMetric navigationsMetric = getNavigationsMetric();
		final Query query = QueryFactory.create(queryString);
		ElementWalker.walk(query.getQueryPattern(), new ElementVisitorBase() {
			@Override
			public void visit(ElementPathBlock el) {
				ListIterator<TriplePath> it = el.getPattern().iterator();
				while (it.hasNext()) {
					it.next();
					navigationsMetric.increase();
				}
			}

			@Override
			public void visit(ElementFilter el) {
				filtersMetric.increase();
				variablesMetric.increase(el.getExpr().getVarsMentioned().size());
				constantsMetric.increase(el.getExpr().isConstant() ? 1 : 0);
			}

		});

	}

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String query) {
		this.queryString = query;
	}

}
