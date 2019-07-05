package com.next.c1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.next.c1.repository.BaseRepository;

@Service
public class BaseSearchService<T,REPO extends BaseRepository<T,Integer>> {
	
	@Autowired
	ApplicationContext appContext;

	public List<T> search(REPO repository) {
		return repository.findAll();
	}

}
