package com.next.jpatis.jpa;

import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceProvider;
import javax.persistence.spi.PersistenceUnitInfo;
import javax.persistence.spi.ProviderUtil;
import javax.sql.DataSource;
@SuppressWarnings("rawtypes")
public class PersistenceProviderImpl implements PersistenceProvider {

	
	@Override
	public EntityManagerFactory createEntityManagerFactory(String emName, Map map) {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	@Override
	public EntityManagerFactory createContainerEntityManagerFactory(PersistenceUnitInfo info, Map map) 
	{
		DataSource ds = info.getNonJtaDataSource();
		return new EntityManagerFactoryImpl(ds, null);
	}

	@Override
	public void generateSchema(PersistenceUnitInfo info, Map map) {
		throw new RuntimeException("NOT IMPLEMENTED");

	}

	@Override
	public boolean generateSchema(String persistenceUnitName, Map map) {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	@Override
	public ProviderUtil getProviderUtil() {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

}
