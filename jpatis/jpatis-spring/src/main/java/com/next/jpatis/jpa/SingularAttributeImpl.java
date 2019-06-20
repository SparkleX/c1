package com.next.jpatis.jpa;

import java.lang.reflect.Member;

import javax.persistence.metamodel.ManagedType;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.Type;

public class SingularAttributeImpl<X,Y> implements SingularAttribute<X,Y>
{

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersistentAttributeType getPersistentAttributeType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ManagedType<X> getDeclaringType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<Y> getJavaType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Member getJavaMember() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAssociation() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCollection() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BindableType getBindableType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<Y> getBindableJavaType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isId() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isVersion() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isOptional() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Type<Y> getType() {
		// TODO Auto-generated method stub
		return null;
	}

}
