package com.next.jpatis.core;

import java.util.List;
import java.util.Optional;

public interface SqlConnection {
	<T> List<T> select(Class<T> sql, Object ...params);
	void insert(Object entity);
	void update(Object entity);
	void delete(Object entity);
	<T> List<T> selectAll(Class<T> clazz);
	<T> Optional<T> findById(Class<T> clazz, Object id);
	void updateById(Object id, Object entity);
	void deleteById(Object id, Class<?> entityClass);
}
