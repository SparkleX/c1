package com.next.jpatis.jpa;

import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.persistence.criteria.Subquery;
import javax.persistence.metamodel.EntityType;

import com.next.jpatis.core.JpaUtils;

public class CriteriaQueryImpl<T> implements CriteriaQuery<T>{

	Class<T> resultClass;

	public CriteriaQueryImpl(Class<T> resultClass) {
		this.resultClass = resultClass;
	}

	@Override
	public <X> Root<X> from(Class<X> entityClass) {
		return new RootImpl<X>(entityClass);
		
	}

	@Override
	public <X> Root<X> from(EntityType<X> entity) {
		throw new RuntimeException();
		
	}

	@Override
	public Set<Root<?>> getRoots() {
		throw new RuntimeException();
		
	}

	@Override
	public Selection<T> getSelection() {
		throw new RuntimeException();
		
	}

	@Override
	public List<Expression<?>> getGroupList() {
		throw new RuntimeException();
		
	}

	@Override
	public Predicate getGroupRestriction() {
		throw new RuntimeException();
		
	}

	@Override
	public boolean isDistinct() {
		throw new RuntimeException();
		//return false;
	}

	@Override
	public Class<T> getResultType() {
		throw new RuntimeException();
		
	}

	@Override
	public <U> Subquery<U> subquery(Class<U> type) {
		throw new RuntimeException();
		
	}

	@Override
	public Predicate getRestriction() {
		throw new RuntimeException();
		
	}

	@Override
	public CriteriaQuery<T> select(Selection<? extends T> selection) {
		
		return this;
		
	}

	@Override
	public CriteriaQuery<T> multiselect(Selection<?>... selections) {
		throw new RuntimeException();
		
	}

	@Override
	public CriteriaQuery<T> multiselect(List<Selection<?>> selectionList) {
		throw new RuntimeException();
		
	}

	@Override
	public CriteriaQuery<T> where(Expression<Boolean> restriction) {
		throw new RuntimeException();
		
	}

	@Override
	public CriteriaQuery<T> where(Predicate... restrictions) {
		throw new RuntimeException();
		
	}

	@Override
	public CriteriaQuery<T> groupBy(Expression<?>... grouping) {
		throw new RuntimeException();
		
	}

	@Override
	public CriteriaQuery<T> groupBy(List<Expression<?>> grouping) {
		throw new RuntimeException();
		
	}

	@Override
	public CriteriaQuery<T> having(Expression<Boolean> restriction) {
		throw new RuntimeException();
		
	}

	@Override
	public CriteriaQuery<T> having(Predicate... restrictions) {
		throw new RuntimeException();
		
	}

	@Override
	public CriteriaQuery<T> orderBy(Order... o) {
		throw new RuntimeException();
		
	}

	@Override
	public CriteriaQuery<T> orderBy(List<Order> o) {
		throw new RuntimeException();
		
	}

	@Override
	public CriteriaQuery<T> distinct(boolean distinct) {
		throw new RuntimeException();
		
	}

	@Override
	public List<Order> getOrderList() {
		throw new RuntimeException();
		
	}

	@Override
	public Set<ParameterExpression<?>> getParameters() {
		throw new RuntimeException();
		
	}

	String getSql() 
	{
		String table = JpaUtils.getTableName(this.resultClass);
		return "select * from " + table;
	}

}
