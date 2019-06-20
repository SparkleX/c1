package com.next.jpatis.jpa;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Cache;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnitUtil;
import javax.persistence.Query;
import javax.persistence.SynchronizationType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.metamodel.Metamodel;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("rawtypes")
public class EntityManagerFactoryImpl implements EntityManagerFactory {
	private DataSource dataSource;

	public List<Class<?>> getEntityClasses() {
		return entityClasses;
	}

	private List<Class<?>> entityClasses;
	final static Logger logger = LoggerFactory.getLogger(EntityManagerFactoryImpl.class);

	public EntityManagerFactoryImpl(DataSource ds, List<Class<?>> entityClasses) {
		this.dataSource = ds;
		this.entityClasses = entityClasses;
	}

	@Override
	public EntityManager createEntityManager() {
		try {
			Connection conn = this.dataSource.getConnection();
			return new EntityManagerImpl(this, conn);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void close() {
	}

	@Override
	public EntityManager createEntityManager(Map map) {
		throw new UnsupportedOperationException();
	}

	@Override
	public EntityManager createEntityManager(SynchronizationType synchronizationType) {
		throw new UnsupportedOperationException();
	}

	@Override
	public EntityManager createEntityManager(SynchronizationType synchronizationType, Map map) {
		throw new UnsupportedOperationException();
	}

	@Override
	public CriteriaBuilder getCriteriaBuilder() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Metamodel getMetamodel() {
		return new MetamodelImpl(this);
	}

	@Override
	public boolean isOpen() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Map<String, Object> getProperties() {
		return new HashMap<String, Object>();
	}

	@Override
	public Cache getCache() {
		throw new UnsupportedOperationException();
	}

	@Override
	public PersistenceUnitUtil getPersistenceUnitUtil() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void addNamedQuery(String name, Query query) {
		throw new UnsupportedOperationException();

	}

	@Override
	public <T> T unwrap(Class<T> cls) {
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> void addNamedEntityGraph(String graphName, EntityGraph<T> entityGraph) {
		throw new UnsupportedOperationException();

	}

}
