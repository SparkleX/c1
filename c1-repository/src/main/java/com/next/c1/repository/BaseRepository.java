package com.next.c1.repository;

import org.springframework.data.repository.NoRepositoryBean;

import com.next.jpatis.spring.JpatisRepository;
@NoRepositoryBean
public interface BaseRepository<T,ID> extends JpatisRepository<T,ID>{
	ID getFirst();
	ID getLast();
	ID getNext(ID id);
	ID getPrev(ID id);
}