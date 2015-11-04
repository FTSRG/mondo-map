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

package eu.mondo.map.hg.benchmark.blazegraph.driver;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.openrdf.repository.RepositoryException;
import org.openrdf.rio.RDFParseException;

import com.bigdata.rdf.sail.BigdataSail;
import com.bigdata.rdf.sail.BigdataSailRepository;

import eu.mondo.map.hg.benchmark.rdf.RDFBenchmarkConfig;
import eu.mondo.map.hg.benchmark.sesame.driver.SesameDriver;

public class BlazegraphDriver extends SesameDriver {

	protected final BigdataSail sail;

	public BlazegraphDriver(final RDFBenchmarkConfig rdfbc) throws IOException, RepositoryException {
		// load journal properties from resources
		final Properties props = loadProperties("/blazegraph.properties");
		// instantiate a sail
		final String journalFile = (String) props.get("com.bigdata.journal.AbstractJournal.file");
//		FileUtils.deleteQuietly(new File(journalFile));

		sail = new BigdataSail(props);
		repository = new BigdataSailRepository(sail);
		repository.initialize();
	}

	@Override
	public void beginTransaction() {
		vf = sail.getValueFactory();
	}

	@Override
	public void read(final String modelPathWithoutExtension) throws IOException, RepositoryException,
			RDFParseException {
		load(modelPathWithoutExtension);
	}

	@Override
	public void destroy() throws RepositoryException {
		super.destroy();
		repository.shutDown();
	}

	public Properties loadProperties(final String resource) throws IOException {
		final Properties p = new Properties();
		final InputStream is = this.getClass().getResourceAsStream(resource);
		p.load(new InputStreamReader(new BufferedInputStream(is)));
		return p;
	}

}
