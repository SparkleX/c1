package com.next.jpatis.spring;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpatisRepository<T,ID> extends JpaRepository<T,ID>{

	void insert(T entity);

	void update(T entity);
	
	void updateById(ID key, T entity);

	@Override
	void delete(T entity);	
	@Override
	void deleteById(ID id);
}
