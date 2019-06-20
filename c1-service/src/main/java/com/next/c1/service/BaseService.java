package com.next.c1.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.next.jpatis.spring.JpatisRepository;

public class BaseService<T,REPO extends JpatisRepository<T,Integer>> {
	
	@Autowired
	protected REPO repository;

	public T create(T o) {
		// TODO Auto-generated method stub
		return null;
	}

}
