package com.next.jpatis.spring;

import javax.persistence.spi.PersistenceProvider;

import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;

import com.next.jpatis.jpa.PersistenceProviderImpl;

public class JpatisVendorAdapter extends AbstractJpaVendorAdapter {
	@Override
	public PersistenceProvider getPersistenceProvider() {
		return new PersistenceProviderImpl();
	}
}
