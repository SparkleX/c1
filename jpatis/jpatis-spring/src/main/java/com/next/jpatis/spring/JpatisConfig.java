package com.next.jpatis.spring;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Table;
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
import com.next.jpatis.ddl.DdlUtil;
import com.next.spring.utils.SpringScanner;

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
		try	{
			createTables();
			return new JpatisVendorAdapter();
		}catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	private void createTables() throws ClassNotFoundException, SQLException {
		try(Connection conn = getDataSource().getConnection()) {
			Statement stat = conn.createStatement();
			String[] rt = this.getPackagesToScan();
			if(this.getProperties().isGenerateDdl()) {
				for(String p:rt) {
					List<String> packs = SpringScanner.scan(p, Table.class, Entity.class);
					for(String clazz:packs) {
						Class<?> entityClass = Class.forName(clazz);
						String sql = DdlUtil.createTable(entityClass);
						stat.execute(sql);						
					}
				}				
			}	
		}
	}
	@Override
	protected Map<String, Object> getVendorProperties() {
		return new HashMap<>();
	}

}
