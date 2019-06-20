package com.next.jpatis.jpa;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.next.jpatis.core.OrmAccess;
import com.next.jpatis.core.SqlConnection;

@SuppressWarnings("rawtypes")
public class EntityManagerImpl implements EntityManager 
{
	final static Logger logger = LoggerFactory.getLogger(EntityManagerImpl.class);
	boolean open;
	private Connection conn;
	private EntityTransactionImpl transaction;
	OrmAccess orm;
	public EntityManagerImpl(EntityManagerFactoryImpl entityManagerFactoryImpl, Connection conn) throws Exception
	{
		this.conn = conn;
		this.open = true;
		this.orm = new OrmAccess(this.conn);
		String schema = TenantContext.getCurrentTenant();
		if(schema != null)
		{
			this.init(schema);
		}
	}
	@Override
	public boolean isOpen() 
	{
		return open;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T unwrap(Class<T> cls) 
	{
		if(cls.equals(SqlConnection.class))
		{
			return (T) this.orm;
		}
		return null;		
	}

	@Override
	public Object getDelegate() {
		return this;
		
	}

	@Override
	public void close() 
	{
		try 
		{
			this.conn.close();
			this.conn = null;
			this.open = false;
		} catch (SQLException e) 
		{
			logger.error(e.getMessage(), e);
		}
	}



	@Override
	public EntityTransaction getTransaction() 
	{
		if(this.transaction==null)
		{
			this.transaction = new EntityTransactionImpl(this.conn);
		}
		return this.transaction;
		
	}
	@Override
	public void persist(Object entity) {
		orm.insert(entity);
	}

	@Override
	public <T> T merge(T entity) {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	@Override
	public void remove(Object entity) {
		throw new RuntimeException("NOT IMPLEMENTED");

	}

	@Override
	public <T> T find(Class<T> entityClass, Object primaryKey) 
	{
		Optional<T> rt = this.orm.findById(entityClass, primaryKey);
		if(rt.isPresent()) {
			return rt.get();
		}
		return null;
		
	}

	@Override
	public <T> T find(Class<T> entityClass, Object primaryKey, Map<String, Object> properties) 
	{
		return find(entityClass, primaryKey);
		
	}

	@Override
	public <T> T find(Class<T> entityClass, Object primaryKey, LockModeType lockMode) {
		return find(entityClass, primaryKey);
	}

	@Override
	public <T> T find(Class<T> entityClass, Object primaryKey, LockModeType lockMode, Map<String, Object> properties) {
		return find(entityClass, primaryKey);
	}

	@Override
	public <T> T getReference(Class<T> entityClass, Object primaryKey) {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	@Override
	public void flush() {
		throw new RuntimeException("NOT IMPLEMENTED");

	}

	@Override
	public void setFlushMode(FlushModeType flushMode) {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	@Override
	public FlushModeType getFlushMode() {
		throw new RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public void lock(Object entity, LockModeType lockMode) {
		throw new RuntimeException("NOT IMPLEMENTED");

	}

	@Override
	public void lock(Object entity, LockModeType lockMode, Map<String, Object> properties) {
		throw new RuntimeException("NOT IMPLEMENTED");

	}

	@Override
	public void refresh(Object entity) {
		throw new RuntimeException("NOT IMPLEMENTED");

	}

	@Override
	public void refresh(Object entity, Map<String, Object> properties) {
		throw new RuntimeException("NOT IMPLEMENTED");

	}

	@Override
	public void refresh(Object entity, LockModeType lockMode) {
		throw new RuntimeException("NOT IMPLEMENTED");

	}

	@Override
	public void refresh(Object entity, LockModeType lockMode, Map<String, Object> properties) {
		throw new RuntimeException("NOT IMPLEMENTED");

	}

	@Override
	public void clear() 
	{

	}

	@Override
	public void detach(Object entity) {
		throw new RuntimeException("NOT IMPLEMENTED");

	}

	@Override
	public boolean contains(Object entity) {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	@Override
	public LockModeType getLockMode(Object entity) {
		throw new RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public void setProperty(String propertyName, Object value) {
		throw new RuntimeException("NOT IMPLEMENTED");

	}

	@Override
	public Map<String, Object> getProperties() {
		throw new RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public Query createQuery(String qlString) {
		throw new RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public <T> TypedQuery<T> createQuery(CriteriaQuery<T> criteriaQuery) {
		return new TypedQueryImpl<T>(criteriaQuery, this);
		
	}

	@Override
	public Query createQuery(CriteriaUpdate updateQuery) {
		throw new RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public Query createQuery(CriteriaDelete deleteQuery) {
		throw new RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public <T> TypedQuery<T> createQuery(String qlString, Class<T> resultClass) {
		throw new RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public Query createNamedQuery(String name) {
		return new QueryImpl(name);
		
	}

	@Override
	public <T> TypedQuery<T> createNamedQuery(String name, Class<T> resultClass) {
		throw new RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public Query createNativeQuery(String sql) {
		return new QueryImpl(sql, this);
		
	}

	@Override
	public Query createNativeQuery(String sql, Class resultClass) {
		return new QueryImpl(sql, resultClass, this);
		
	}

	@Override
	public Query createNativeQuery(String sqlString, String resultSetMapping) {
		throw new RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public StoredProcedureQuery createNamedStoredProcedureQuery(String name) {
		throw new RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public StoredProcedureQuery createStoredProcedureQuery(String procedureName) {
		throw new RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public StoredProcedureQuery createStoredProcedureQuery(String procedureName, Class... resultClasses) {
		throw new RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public StoredProcedureQuery createStoredProcedureQuery(String procedureName, String... resultSetMappings) {
		throw new RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public void joinTransaction() {
		throw new RuntimeException("NOT IMPLEMENTED");

	}

	@Override
	public boolean isJoinedToTransaction() {
		throw new RuntimeException("NOT IMPLEMENTED");
	}



	@Override
	public EntityManagerFactory getEntityManagerFactory() {
		throw new RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public CriteriaBuilder getCriteriaBuilder() {
		throw new RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public Metamodel getMetamodel() {
		throw new RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public <T> EntityGraph<T> createEntityGraph(Class<T> rootType) {
		throw new RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public EntityGraph<?> createEntityGraph(String graphName) {
		throw new RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public EntityGraph<?> getEntityGraph(String graphName) {
		throw new RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public <T> List<EntityGraph<? super T>> getEntityGraphs(Class<T> entityClass) {
		throw new RuntimeException("NOT IMPLEMENTED");
		
	}
	private void init(String schema) throws Exception {
		Driver driver = DriverManager.getDriver(conn.getMetaData().getURL());
		String sql;
		if(driver.getClass().getName().startsWith("com.microsoft."))
		{
			sql = "use " + schema;
		}
		else if(driver.getClass().getName().startsWith("com.sap."))
		{
			sql = "set schema " + schema;	
		}
		else
		{
			throw new RuntimeException();
		}		
		this.orm.exec(sql);	
		
	}
}
