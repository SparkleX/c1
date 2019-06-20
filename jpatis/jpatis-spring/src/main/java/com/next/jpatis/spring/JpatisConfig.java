package com.next.jpatis.spring;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.next.jpatis.core.SqlConnection;

@Configuration
public class JpatisConfig extends JpaBaseConfiguration {
	JpatisConfig(DataSource dataSource, JpaProperties properties,
			ObjectProvider<JtaTransactionManager> jtaTransactionManager,
			ObjectProvider<TransactionManagerCustomizers> transactionManagerCustomizers) {
		super(dataSource, properties, jtaTransactionManager, transactionManagerCustomizers);
	}

	@Bean
	SqlConnection sqlConnection() {
		return new SqlConnectionProxy();
	}

	@Override
	protected AbstractJpaVendorAdapter createJpaVendorAdapter() {
		return new JpatisVendorAdapter();
	}

	@Override
	protected Map<String, Object> getVendorProperties() {
		return new HashMap<>();
	}

}
