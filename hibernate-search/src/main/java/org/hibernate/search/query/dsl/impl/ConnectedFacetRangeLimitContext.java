package org.hibernate.search.query.dsl.impl;

import org.hibernate.search.query.dsl.FacetRangeEndContext;
import org.hibernate.search.query.dsl.FacetRangeLimitContext;

/**
 * @author Hardy Ferentschik
 */
public class ConnectedFacetRangeLimitContext<T> implements FacetRangeLimitContext<T> {
	private final FacetBuildingContext context;

	public ConnectedFacetRangeLimitContext(FacetBuildingContext context) {
		this.context = context;
	}

	public FacetRangeLimitContext<T> excludeLimit() {
		context.setIncludeRangeStart( false );
		return this;
	}

	public FacetRangeEndContext<T> to(T upperLimit) {
		context.setRangeEnd( upperLimit );
		return new ConnectedFacetRangeEndContext( context );
	}

}


