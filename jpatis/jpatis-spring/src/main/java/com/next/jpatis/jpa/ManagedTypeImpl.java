package com.next.jpatis.jpa;

import java.util.Set;

import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.IdentifiableType;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.ManagedType;
import javax.persistence.metamodel.MapAttribute;
import javax.persistence.metamodel.PluralAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.Type;

public class ManagedTypeImpl<X> implements ManagedType<X>,IdentifiableType<X> {

	public ManagedTypeImpl(Class<X> cls) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public PersistenceType getPersistenceType() {
		throw new RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public Class<X> getJavaType() {
		throw new RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public Set<Attribute<? super X, ?>> getAttributes() {
		throw new RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public Set<Attribute<X, ?>> getDeclaredAttributes() {
		throw new RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public <Y> SingularAttribute<? super X, Y> getSingularAttribute(String name, Class<Y> type) {
		throw new RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public <Y> SingularAttribute<X, Y> getDeclaredSingularAttribute(String name, Class<Y> type) {
		throw new RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public Set<SingularAttribute<? super X, ?>> getSingularAttributes() {
		throw new RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public Set<SingularAttribute<X, ?>> getDeclaredSingularAttributes() {
		throw new RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public <E> CollectionAttribute<? super X, E> getCollection(String name, Class<E> elementType) {
		throw new RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public <E> CollectionAttribute<X, E> getDeclaredCollection(String name, Class<E> elementType) {
		throw new RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public <E> SetAttribute<? super X, E> getSet(String name, Class<E> elementType) {
		throw new RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public <E> SetAttribute<X, E> getDeclaredSet(String name, Class<E> elementType) {
		throw new RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public <E> ListAttribute<? super X, E> getList(String name, Class<E> elementType) {
		throw new RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public <E> ListAttribute<X, E> getDeclaredList(String name, Class<E> elementType) {
		throw new RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public <K, V> MapAttribute<? super X, K, V> getMap(String name, Class<K> keyType, Class<V> valueType) {
		throw new RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public <K, V> MapAttribute<X, K, V> getDeclaredMap(String name, Class<K> keyType, Class<V> valueType) {
		throw new RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public Set<PluralAttribute<? super X, ?, ?>> getPluralAttributes() {
		throw new RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public Set<PluralAttribute<X, ?, ?>> getDeclaredPluralAttributes() {
		throw new RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public Attribute<? super X, ?> getAttribute(String name) {
		throw new RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public Attribute<X, ?> getDeclaredAttribute(String name) {
		throw new RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public SingularAttribute<? super X, ?> getSingularAttribute(String name) {
		throw new RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public SingularAttribute<X, ?> getDeclaredSingularAttribute(String name) {
		throw new RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public CollectionAttribute<? super X, ?> getCollection(String name) {
		throw new RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public CollectionAttribute<X, ?> getDeclaredCollection(String name) {
		throw new RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public SetAttribute<? super X, ?> getSet(String name) {
		throw new RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public SetAttribute<X, ?> getDeclaredSet(String name) {
		throw new RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public ListAttribute<? super X, ?> getList(String name) {
		throw new RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public ListAttribute<X, ?> getDeclaredList(String name) {
		throw new RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public MapAttribute<? super X, ?, ?> getMap(String name) {
		throw new RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public MapAttribute<X, ?, ?> getDeclaredMap(String name) {
		throw new RuntimeException("NOT IMPLEMENTED");
		
	}

	@Override
	public <Y> SingularAttribute<? super X, Y> getId(Class<Y> type) {
		return new SingularAttributeImpl<X,Y>();
	}

	@Override
	public <Y> SingularAttribute<X, Y> getDeclaredId(Class<Y> type) {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	@Override
	public <Y> SingularAttribute<? super X, Y> getVersion(Class<Y> type) {
		return null;
	}

	@Override
	public <Y> SingularAttribute<X, Y> getDeclaredVersion(Class<Y> type) {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	@Override
	public IdentifiableType<? super X> getSupertype() {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	@Override
	public boolean hasSingleIdAttribute() {
		return true;
	}

	@Override
	public boolean hasVersionAttribute() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<SingularAttribute<? super X, ?>> getIdClassAttributes() {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	@Override
	public Type<?> getIdType() {
		return new IdTypeImpl();
	}

}
