package com.next.jpatis.jpa;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.criteria.CollectionJoin;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.MapJoin;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.persistence.criteria.SetJoin;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.MapAttribute;
import javax.persistence.metamodel.PluralAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;

public class RootImpl<X> implements Root<X> {

	public RootImpl(Class<X> entityClass) 
	{
	}

	@Override
	public Set<Join<X, ?>> getJoins() {
		throw new RuntimeException();
		
	}

	@Override
	public boolean isCorrelated() {
		throw new RuntimeException();
	}

	@Override
	public From<X, X> getCorrelationParent() {
		throw new RuntimeException();
		
	}

	@Override
	public <Y> Join<X, Y> join(SingularAttribute<? super X, Y> attribute) {
		throw new RuntimeException();
		
	}

	@Override
	public <Y> Join<X, Y> join(SingularAttribute<? super X, Y> attribute, JoinType jt) {
		throw new RuntimeException();
		
	}

	@Override
	public <Y> CollectionJoin<X, Y> join(CollectionAttribute<? super X, Y> collection) {
		throw new RuntimeException();
		
	}

	@Override
	public <Y> SetJoin<X, Y> join(SetAttribute<? super X, Y> set) {
		throw new RuntimeException();
		
	}

	@Override
	public <Y> ListJoin<X, Y> join(ListAttribute<? super X, Y> list) {
		throw new RuntimeException();
		
	}

	@Override
	public <K, V> MapJoin<X, K, V> join(MapAttribute<? super X, K, V> map) {
		throw new RuntimeException();
		
	}

	@Override
	public <Y> CollectionJoin<X, Y> join(CollectionAttribute<? super X, Y> collection, JoinType jt) {
		throw new RuntimeException();
		
	}

	@Override
	public <Y> SetJoin<X, Y> join(SetAttribute<? super X, Y> set, JoinType jt) {
		throw new RuntimeException();
		
	}

	@Override
	public <Y> ListJoin<X, Y> join(ListAttribute<? super X, Y> list, JoinType jt) {
		throw new RuntimeException();
		
	}

	@Override
	public <K, V> MapJoin<X, K, V> join(MapAttribute<? super X, K, V> map, JoinType jt) {
		throw new RuntimeException();
		
	}

	@Override
	public <X1, Y> Join<X1, Y> join(String attributeName) {
		throw new RuntimeException();
		
	}

	@Override
	public <X1, Y> CollectionJoin<X1, Y> joinCollection(String attributeName) {
		throw new RuntimeException();
		
	}

	@Override
	public <X1, Y> SetJoin<X1, Y> joinSet(String attributeName) {
		throw new RuntimeException();
		
	}

	@Override
	public <X1, Y> ListJoin<X1, Y> joinList(String attributeName) {
		throw new RuntimeException();
		
	}

	@Override
	public <X1, K, V> MapJoin<X1, K, V> joinMap(String attributeName) {
		throw new RuntimeException();
		
	}

	@Override
	public <X1, Y> Join<X1, Y> join(String attributeName, JoinType jt) {
		throw new RuntimeException();
		
	}

	@Override
	public <X1, Y> CollectionJoin<X1, Y> joinCollection(String attributeName, JoinType jt) {
		throw new RuntimeException();
		
	}

	@Override
	public <X1, Y> SetJoin<X1, Y> joinSet(String attributeName, JoinType jt) {
		throw new RuntimeException();
		
	}

	@Override
	public <X1, Y> ListJoin<X1, Y> joinList(String attributeName, JoinType jt) {
		throw new RuntimeException();
		
	}

	@Override
	public <X1, K, V> MapJoin<X1, K, V> joinMap(String attributeName, JoinType jt) {
		throw new RuntimeException();
		
	}

	@Override
	public Path<?> getParentPath() {
		throw new RuntimeException();
		
	}

	@Override
	public <Y> Path<Y> get(SingularAttribute<? super X, Y> attribute) {
		throw new RuntimeException();
		
	}

	@Override
	public <E, C extends Collection<E>> Expression<C> get(PluralAttribute<X, C, E> collection) {
		throw new RuntimeException();
		
	}

	@Override
	public <K, V, M extends Map<K, V>> Expression<M> get(MapAttribute<X, K, V> map) {
		throw new RuntimeException();
		
	}

	@Override
	public Expression<Class<? extends X>> type() {
		throw new RuntimeException();
		
	}

	@Override
	public <Y> Path<Y> get(String attributeName) {
		throw new RuntimeException();
		
	}

	@Override
	public Predicate isNull() {
		throw new RuntimeException();
		
	}

	@Override
	public Predicate isNotNull() {
		throw new RuntimeException();
		
	}

	@Override
	public Predicate in(Object... values) {
		throw new RuntimeException();
		
	}

	@Override
	public Predicate in(Expression<?>... values) {
		throw new RuntimeException();
		
	}

	@Override
	public Predicate in(Collection<?> values) {
		throw new RuntimeException();
		
	}

	@Override
	public Predicate in(Expression<Collection<?>> values) {
		throw new RuntimeException();
		
	}

	@Override
	public <X1> Expression<X1> as(Class<X1> type) {
		throw new RuntimeException();
		
	}

	@Override
	public Selection<X> alias(String name) {
		throw new RuntimeException();
		
	}

	@Override
	public boolean isCompoundSelection() {
		throw new RuntimeException();
		//return false;
	}

	@Override
	public List<Selection<?>> getCompoundSelectionItems() {
		throw new RuntimeException();
		
	}

	@Override
	public Class<? extends X> getJavaType() {
		throw new RuntimeException();
		
	}

	@Override
	public String getAlias() {
		throw new RuntimeException();
		
	}

	@Override
	public Set<Fetch<X, ?>> getFetches() {
		throw new RuntimeException();
		
	}

	@Override
	public <Y> Fetch<X, Y> fetch(SingularAttribute<? super X, Y> attribute) {
		throw new RuntimeException();
		
	}

	@Override
	public <Y> Fetch<X, Y> fetch(SingularAttribute<? super X, Y> attribute, JoinType jt) {
		throw new RuntimeException();
		
	}

	@Override
	public <Y> Fetch<X, Y> fetch(PluralAttribute<? super X, ?, Y> attribute) {
		throw new RuntimeException();
		
	}

	@Override
	public <Y> Fetch<X, Y> fetch(PluralAttribute<? super X, ?, Y> attribute, JoinType jt) {
		throw new RuntimeException();
		
	}

	@Override
	public <X1, Y> Fetch<X1, Y> fetch(String attributeName) {
		throw new RuntimeException();
		
	}

	@Override
	public <X1, Y> Fetch<X1, Y> fetch(String attributeName, JoinType jt) {
		throw new RuntimeException();
		
	}

	@Override
	public EntityType<X> getModel() {
		throw new RuntimeException();
		
	}

}
