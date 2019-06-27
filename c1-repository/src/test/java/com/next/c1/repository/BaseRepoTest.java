package com.next.c1.repository;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Ignore
public class BaseRepoTest<T, REPO extends BaseRepository<T,Integer>> {
	@Autowired
	protected REPO repo;
	@Test
	public void findAllTest() throws Exception {
		this.repo.findAll();
	}	
	@Test
	public void nextTest() throws Exception {
		this.repo.getNext(1);
	}	
	@Test
	public void prevTest() throws Exception {
		this.repo.getPrev(1);
	}
	@Test
	public void firstTest() throws Exception {
		this.repo.getFirst();
	}
	@Test
	public void lastTest() throws Exception {
		this.repo.getLast();
	}	
	
}
