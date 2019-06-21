package com.next.c1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.next.c1.service.BaseService;



public class BaseController<T, SERVICE extends BaseService<T,?>> {
	
	@Autowired
	protected SERVICE service;
	
	@PostMapping
	public void create(T o) {
		service.create(o);
	}
	@GetMapping(path="/{id}")
	public T get(@PathVariable Integer id) {
		return service.get(id);
	}
	
	@PutMapping(path="/{id}")
	public void update(@PathVariable Integer id, @RequestBody T entity)
	{
		service.update(id, entity);
	}
	@DeleteMapping(path="/{id}")
	public void delete(@PathVariable Integer id)
	{
		service.delete(id);
	}
	@GetMapping
	public List<T> search()
	{
		return service.search();
	}	
}
