package com.next.jpatis.jpa;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.Parameter;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import com.next.jpatis.core.OrmAccess;

public class QueryImpl implements Query {

	//private String name;
	private Class<?> resultClass;
	//private String sql;
	QueryParamResult sqlParseResult;
	private List<Parameter<?>> parameters;
	private Object [] paramValues;
	private EntityManagerImpl em;
	
	public QueryImpl(String sql, EntityManagerImpl em)
	{
		this(sql, Object[].class, em);
	}
	public QueryImpl(String sql, Class<?> resultClass, EntityManagerImpl em) 
	{
		this.em = em;
		//this.sql = sql;
		this.resultClass = resultClass;
		QueryParser parser = new QueryParser();
		sqlParseResult = parser.parse(sql);
		parameters =new ArrayList<>();
		for(int i = 0;i<sqlParseResult.getParams().size();i++)
		{
			String name = sqlParseResult.getParams().get(i);
			parameters.add(new ParameterImpl(name, i+1));
		}
		paramValues = new Object[sqlParseResult.getParams().size()];
	}
	public QueryImpl(String name) {
		//this.name = name;
	}
	@Override
	public Set<Parameter<?>> getParameters() 
	{
		Set<Parameter<?>> rt = new HashSet<>(parameters);
		return rt;
	}

	@Override
	public Query setParameter(String name, Object value) 
	{
		for(int i =0;i<parameters.size();i++)
		{
			if(this.parameters.get(i).getName().equals(name)==false)
			{
				continue;
			}
			Parameter<?> param = parameters.get(i);
			int position = param.getPosition();
			this.setParameter(position, value);
		}
		return this;
	}
	
	@Override
	public Query setParameter(int position, Object value) 
	{
		paramValues[position-1] = value;
		return this;
	}
	@Override
	public List<?> getResultList() 
	{
		OrmAccess orm = em.orm;
		List<?> rt = orm.load(resultClass, sqlParseResult.getSqlWoParamName(), paramValues);
		return rt;
	}

	@Override
	public Object getSingleResult() {
		OrmAccess orm = em.orm;
		List<?> rt = orm.load(resultClass, sqlParseResult.getSqlWoParamName(), paramValues);
		return rt.get(0);
	}

	@Override
	public int executeUpdate() {
		throw new  RuntimeException("NOT IMPLEMENTED");
	}

	@Override
	public Query setMaxResults(int maxResult) {
		throw new  RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public int getMaxResults() {
		throw new  RuntimeException("NOT IMPLEMENTED");
	}

	@Override
	public Query setFirstResult(int startPosition) {
		throw new  RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public int getFirstResult() {
		throw new  RuntimeException("NOT IMPLEMENTED");
	}

	@Override
	public Query setHint(String hintName, Object value) {
		throw new  RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public Map<String, Object> getHints() {
		throw new  RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public <T> Query setParameter(Parameter<T> param, T value) {
		throw new  RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public Query setParameter(Parameter<Calendar> param, Calendar value, TemporalType temporalType) {
		throw new  RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public Query setParameter(Parameter<Date> param, Date value, TemporalType temporalType) {
		throw new  RuntimeException("NOT IMPLEMENTED");
		
	}


	@Override
	public Query setParameter(String name, Calendar value, TemporalType temporalType) {
		throw new  RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public Query setParameter(String name, Date value, TemporalType temporalType) {
		throw new  RuntimeException("NOT IMPLEMENTED");
		
	}



	@Override
	public Query setParameter(int position, Calendar value, TemporalType temporalType) {
		throw new  RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public Query setParameter(int position, Date value, TemporalType temporalType) {
		throw new  RuntimeException("NOT IMPLEMENTED");
		
	}



	@Override
	public Parameter<?> getParameter(String name) {
		throw new  RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public <T> Parameter<T> getParameter(String name, Class<T> type) {
		throw new  RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public Parameter<?> getParameter(int position) {
		throw new  RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public <T> Parameter<T> getParameter(int position, Class<T> type) {
		throw new  RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public boolean isBound(Parameter<?> param) {
		throw new  RuntimeException("NOT IMPLEMENTED");
	}

	@Override
	public <T> T getParameterValue(Parameter<T> param) {
		throw new  RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public Object getParameterValue(String name) {
		throw new  RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public Object getParameterValue(int position) {
		throw new  RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public Query setFlushMode(FlushModeType flushMode) {
		throw new  RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public FlushModeType getFlushMode() {
		throw new  RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public Query setLockMode(LockModeType lockMode) {
		throw new  RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public LockModeType getLockMode() {
		throw new  RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public <T> T unwrap(Class<T> cls) {
		throw new  RuntimeException("NOT IMPLEMENTED");
		
	}

}
