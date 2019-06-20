package com.next.jpatis.jpa;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.metamodel.EmbeddableType;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.ManagedType;
import javax.persistence.metamodel.Metamodel;
@SuppressWarnings({"rawtypes","unchecked"})
public class MetamodelImpl implements Metamodel
{
	public MetamodelImpl(EntityManagerFactoryImpl factory) {
	}

	@Override
	public <X> EntityType<X> entity(Class<X> cls) {
		throw new UnsupportedOperationException();
	}
	
	public <X> EntityType<X> entity(String entity) {
		throw new UnsupportedOperationException();

	}
	
	@Override
	public <X> ManagedType<X> managedType(Class<X> cls) {
		return (ManagedType<X>) new ManagedTypeImpl(cls);
	}

	@Override
	public <X> EmbeddableType<X> embeddable(Class<X> cls) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<ManagedType<?>> getManagedTypes() {
		return new HashSet();
	}

	@Override
	public Set<EntityType<?>> getEntities() {
		return new HashSet();
	}

	@Override
	public Set<EmbeddableType<?>> getEmbeddables() {
		return new HashSet<EmbeddableType<?>>();
	}
}
