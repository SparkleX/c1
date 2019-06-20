package com.next.c1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.next.jpatis.spring.JpatisRepository;

public class BaseService<T,REPO extends JpatisRepository<T,Integer>> {
	
	@Autowired
	protected REPO repository;

	public T create(T o) {
		// TODO Auto-generated method stub
		return null;
	}

	public T get(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public T update(Integer id, T entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	public List<T> search() {
		// TODO Auto-generated method stub
		return null;
	}

}
