/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * Copyright (c) 2010, Red Hat, Inc. and/or its affiliates or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Red Hat, Inc.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 */

package org.hibernate.search.impl;

import org.apache.lucene.analysis.Analyzer;
import org.hibernate.search.backend.spi.BackendQueueProcessorFactory;
import org.hibernate.search.backend.spi.LuceneIndexingParameters;
import org.hibernate.search.backend.spi.Worker;
import org.hibernate.search.engine.spi.DocumentBuilderContainedEntity;
import org.hibernate.search.engine.spi.EntityIndexBinder;
import org.hibernate.search.engine.impl.FilterDef;
import org.hibernate.search.engine.ServiceManager;
import org.hibernate.search.filter.FilterCachingStrategy;
import org.hibernate.search.indexes.impl.IndexManagerHolder;
import org.hibernate.search.spi.internals.PolymorphicIndexHierarchy;
import org.hibernate.search.spi.internals.SearchFactoryImplementorWithShareableState;
import org.hibernate.search.spi.internals.SearchFactoryState;
import org.hibernate.search.store.DirectoryProvider;

import java.util.Map;
import java.util.Properties;

/**
 * Shared factory state
 *
 * @author Emmanuel Bernard
 */
public class MutableSearchFactoryState implements SearchFactoryState {
	private Map<Class<?>, DocumentBuilderContainedEntity<?>> documentBuildersContainedEntities;
	private Map<Class<?>, EntityIndexBinder<?>> indexBindingsPerEntity;
	private String indexingStrategy;
	private Worker worker;
	private BackendQueueProcessorFactory backendQueueProcessorFactory;
	private Map<String, FilterDef> filterDefinitions;
	private FilterCachingStrategy filterCachingStrategy;
	private Map<String, Analyzer> analyzers;
	private int cacheBitResultsSize;
	private Properties configurationProperties;
	private PolymorphicIndexHierarchy indexHierarchy;
	private Map<DirectoryProvider, LuceneIndexingParameters> directoryProviderIndexingParams;
	private ServiceManager serviceManager;
	private boolean transactionManagerExpected = true;
	private IndexManagerHolder allIndexesManager;

	public void copyStateFromOldFactory(SearchFactoryState oldFactoryState) {
		indexingStrategy = oldFactoryState.getIndexingStrategy();
		indexBindingsPerEntity = oldFactoryState.getIndexBindingForEntity();
		documentBuildersContainedEntities = oldFactoryState.getDocumentBuildersContainedEntities();
		worker = oldFactoryState.getWorker();
		filterDefinitions = oldFactoryState.getFilterDefinitions();
		filterCachingStrategy = oldFactoryState.getFilterCachingStrategy();
		analyzers = oldFactoryState.getAnalyzers();
		cacheBitResultsSize = oldFactoryState.getCacheBitResultsSize();
		configurationProperties = oldFactoryState.getConfigurationProperties();
		indexHierarchy = oldFactoryState.getIndexHierarchy();
		directoryProviderIndexingParams = oldFactoryState.getDirectoryProviderIndexingParams();
		serviceManager = oldFactoryState.getServiceManager();
		transactionManagerExpected = oldFactoryState.isTransactionManagerExpected();
		allIndexesManager = oldFactoryState.getAllIndexesManager();
	}

	public ServiceManager getServiceManager() {
		return serviceManager;
	}

	public void setServiceManager(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}

	public Map<Class<?>, DocumentBuilderContainedEntity<?>> getDocumentBuildersContainedEntities() {
		return documentBuildersContainedEntities;
	}

	public Map<Class<?>, EntityIndexBinder<?>> getIndexBindingForEntity() {
		return indexBindingsPerEntity;
	}

	public String getIndexingStrategy() {
		return indexingStrategy;
	}

	public Worker getWorker() {
		return worker;
	}

	public BackendQueueProcessorFactory getBackendQueueProcessorFactory() {
		return backendQueueProcessorFactory;
	}

	public Map<String, FilterDef> getFilterDefinitions() {
		return filterDefinitions;
	}

	public FilterCachingStrategy getFilterCachingStrategy() {
		return filterCachingStrategy;
	}

	public Map<String, Analyzer> getAnalyzers() {
		return analyzers;
	}

	public int getCacheBitResultsSize() {
		return cacheBitResultsSize;
	}

	public Properties getConfigurationProperties() {
		return configurationProperties;
	}

	public PolymorphicIndexHierarchy getIndexHierarchy() {
		return indexHierarchy;
	}

	public Map<DirectoryProvider, LuceneIndexingParameters> getDirectoryProviderIndexingParams() {
		return directoryProviderIndexingParams;
	}

	public void setDocumentBuildersContainedEntities(Map<Class<?>, DocumentBuilderContainedEntity<?>> documentBuildersContainedEntities) {
		this.documentBuildersContainedEntities = documentBuildersContainedEntities;
	}

	public void setDocumentBuildersIndexedEntities(Map<Class<?>, EntityIndexBinder<?>> documentBuildersIndexedEntities) {
		this.indexBindingsPerEntity = documentBuildersIndexedEntities;
	}

	public void setIndexingStrategy(String indexingStrategy) {
		this.indexingStrategy = indexingStrategy;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}

	public void setBackendQueueProcessorFactory(BackendQueueProcessorFactory backendQueueProcessorFactory) {
		this.backendQueueProcessorFactory = backendQueueProcessorFactory;
	}

	public void setFilterDefinitions(Map<String, FilterDef> filterDefinitions) {
		this.filterDefinitions = filterDefinitions;
	}

	public void setFilterCachingStrategy(FilterCachingStrategy filterCachingStrategy) {
		this.filterCachingStrategy = filterCachingStrategy;
	}

	public void setAnalyzers(Map<String, Analyzer> analyzers) {
		this.analyzers = analyzers;
	}

	public void setCacheBitResultsSize(int cacheBitResultsSize) {
		this.cacheBitResultsSize = cacheBitResultsSize;
	}

	public void setConfigurationProperties(Properties configurationProperties) {
		this.configurationProperties = configurationProperties;
	}

	public void setIndexHierarchy(PolymorphicIndexHierarchy indexHierarchy) {
		this.indexHierarchy = indexHierarchy;
	}

	public void setDirectoryProviderIndexingParams(Map<DirectoryProvider, LuceneIndexingParameters> directoryProviderIndexingParams) {
		this.directoryProviderIndexingParams = directoryProviderIndexingParams;
	}

	public boolean isTransactionManagerExpected() {
		return transactionManagerExpected;
	}

	public void setTransactionManagerExpected(boolean transactionManagerExpected) {
		this.transactionManagerExpected = transactionManagerExpected;
	}

	public void setAllIndexesManager(IndexManagerHolder indexesFactory) {
		this.allIndexesManager = indexesFactory;
	}

	@Override
	public IndexManagerHolder getAllIndexesManager() {
		return allIndexesManager;
	}

	/**
	 * @param factory
	 */
	public void setActiveSearchFactory(SearchFactoryImplementorWithShareableState factory) {
		allIndexesManager.setActiveSearchFactory( factory );
	}

}
