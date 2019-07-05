package com.next.c1.service;

import com.next.c1.repository.BaseRepository;

public class BaseLineService<T,REPO extends BaseRepository<T,Integer>> extends BaseService<T,REPO>{

	@Override
	final public void create(T o) {
		throw new UnsupportedOperationException();
	}
	@Override
	final public void update(Integer id, T entity) {
		throw new UnsupportedOperationException();
	}
	@Override
	final public void delete(Integer id) {
		throw new UnsupportedOperationException();
	}
	@Override
	final public Integer getNext(Integer id) {
		throw new UnsupportedOperationException();
	}
	@Override
	final public Integer getPrev(Integer id) {
		throw new UnsupportedOperationException();
	}
	@Override
	final public Integer getFirst() {
		throw new UnsupportedOperationException();
	}
	@Override
	final public Integer getLast() {
		throw new UnsupportedOperationException();
	}	
}
