package com.next.c1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.next.jpatis.spring.JpatisRepository;

public class BaseService<T,REPO extends JpatisRepository<T,Integer>> {
	
	@Autowired
	protected REPO repository;

	public void create(T o) {
		repository.insert(o);;
	}

	public T get(Integer id) {
		return repository.findById(id).get();
	}

	public void update(Integer id, T entity) {
		repository.updateById(id, entity);
	}

	public void delete(Integer id) {
		repository.deleteById(id);
		
	}

	public List<T> search() {
		return repository.findAll();
	}

}
