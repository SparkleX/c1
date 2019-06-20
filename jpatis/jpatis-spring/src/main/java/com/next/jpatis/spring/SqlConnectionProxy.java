package com.next.jpatis.spring;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.next.jpatis.core.JpatisException;
import com.next.jpatis.core.SqlConnection;

@Component
public class SqlConnectionProxy implements SqlConnection{
	@Autowired
	EntityManager em;
	
	private SqlConnection getSqlConnection() {
		return em.unwrap(SqlConnection.class);
	}

	@Override
	public <T> List<T> select(Class<T> sql, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Object entity) throws JpatisException {
		getSqlConnection().insert(entity);		
	}

	@Override
	public void update(Object entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Object entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> List<T> selectAll(Class<T> clazz) {
		return getSqlConnection().selectAll(clazz);
	}

	@Override
	public <T> Optional<T> findById(Class<T> clazz, Object id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateById(Object id, Object entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Object id, Class<?> entityClass) {
		// TODO Auto-generated method stub
		
	}
	
}
