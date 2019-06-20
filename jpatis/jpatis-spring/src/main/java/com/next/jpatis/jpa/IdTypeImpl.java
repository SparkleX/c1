package com.next.jpatis.jpa;

import javax.persistence.metamodel.Type;
@SuppressWarnings("rawtypes")
public class IdTypeImpl implements Type{

	@Override
	public PersistenceType getPersistenceType() {
		return PersistenceType.BASIC;
	}

	@Override
	public Class getJavaType() {
		return String.class;
	}

}
