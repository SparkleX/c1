package com.next.c1.repository;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.next.jpatis.spring.JpatisRepository;

@Ignore
public class BaseRepoTest<T, REPO extends JpatisRepository<T,Integer>> {
	@Autowired
	protected REPO repo;
	@Test
	public void findAllTest() throws Exception {
		this.repo.findAll();
	}	
}
