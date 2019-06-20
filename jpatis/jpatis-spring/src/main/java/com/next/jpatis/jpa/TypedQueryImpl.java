package com.next.jpatis.jpa;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.Parameter;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

public class TypedQueryImpl<T> implements TypedQuery<T>
{

	private CriteriaQueryImpl<T> criteriaQuery;
	private EntityManagerImpl em;

	public TypedQueryImpl(CriteriaQuery<T> criteriaQuery, EntityManagerImpl quickEntityManager) {
		this.criteriaQuery = (CriteriaQueryImpl<T>) criteriaQuery;
		this.em = quickEntityManager;
	}
	@Override
	public List<T> getResultList() {
		String sql = criteriaQuery.getSql();
		try {
			return em.orm.load(criteriaQuery.resultClass, sql);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	public int executeUpdate() {
		throw new RuntimeException();
		//return 0;
	}

	@Override
	public int getMaxResults() {
		throw new RuntimeException();
		//return 0;
	}

	@Override
	public int getFirstResult() {
		throw new RuntimeException();
		//return 0;
	}

	@Override
	public Map<String, Object> getHints() {
		throw new RuntimeException();
		
	}

	@Override
	public Set<Parameter<?>> getParameters() {
		throw new RuntimeException();
		
	}

	@Override
	public Parameter<?> getParameter(String name) {
		throw new RuntimeException();
		
	}

	@Override
	public <T1> Parameter<T1> getParameter(String name, Class<T1> type) {
		throw new RuntimeException();
		
	}

	@Override
	public Parameter<?> getParameter(int position) {
		throw new RuntimeException();
		
	}

	@Override
	public <T1> Parameter<T1> getParameter(int position, Class<T1> type) {
		throw new RuntimeException();
		
	}

	@Override
	public boolean isBound(Parameter<?> param) {
		throw new RuntimeException();
		//return false;
	}

	@Override
	public <T1> T1 getParameterValue(Parameter<T1> param) {
		throw new RuntimeException();
		
	}

	@Override
	public Object getParameterValue(String name) {
		throw new RuntimeException();
		
	}

	@Override
	public Object getParameterValue(int position) {
		throw new RuntimeException();
		
	}

	@Override
	public FlushModeType getFlushMode() {
		throw new RuntimeException();
		
	}

	@Override
	public LockModeType getLockMode() {
		throw new RuntimeException();
		
	}

	@Override
	public <T1> T1 unwrap(Class<T1> cls) {
		throw new RuntimeException();
		
	}



	@Override
	public T getSingleResult() {
		throw new RuntimeException();
		
	}

	@Override
	public TypedQuery<T> setMaxResults(int maxResult) {
		throw new RuntimeException();
		
	}

	@Override
	public TypedQuery<T> setFirstResult(int startPosition) {
		throw new RuntimeException();
		
	}

	@Override
	public TypedQuery<T> setHint(String hintName, Object value) {
		throw new RuntimeException();
		
	}

	@Override
	public <X> TypedQuery<T> setParameter(Parameter<X> param, X value) {
		throw new RuntimeException();
		
	}

	@Override
	public TypedQuery<T> setParameter(Parameter<Calendar> param, Calendar value, TemporalType temporalType) {
		throw new RuntimeException();
		
	}

	@Override
	public TypedQuery<T> setParameter(Parameter<Date> param, Date value, TemporalType temporalType) {
		throw new RuntimeException();
		
	}

	@Override
	public TypedQuery<T> setParameter(String name, Object value) {
		throw new RuntimeException();
		
	}

	@Override
	public TypedQuery<T> setParameter(String name, Calendar value, TemporalType temporalType) {
		throw new RuntimeException();
		
	}

	@Override
	public TypedQuery<T> setParameter(String name, Date value, TemporalType temporalType) {
		throw new RuntimeException();
		
	}

	@Override
	public TypedQuery<T> setParameter(int position, Object value) {
		throw new RuntimeException();
		
	}

	@Override
	public TypedQuery<T> setParameter(int position, Calendar value, TemporalType temporalType) {
		throw new RuntimeException();
		
	}

	@Override
	public TypedQuery<T> setParameter(int position, Date value, TemporalType temporalType) {
		throw new RuntimeException();
		
	}

	@Override
	public TypedQuery<T> setFlushMode(FlushModeType flushMode) {
		throw new RuntimeException();
		
	}

	@Override
	public TypedQuery<T> setLockMode(LockModeType lockMode) {
		throw new RuntimeException();
		
	}

}
