package com.next.c1.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.next.c1.service.BaseService;

public class BaseController<T, SERVICE extends BaseService<T, ?>> {

	@Autowired
	protected SERVICE service;
	@Autowired
	protected HttpServletResponse response;

	@PostMapping
	public void create(T o) {
		service.create(o);
	}

	@GetMapping(path = "/{id}/.desc")
	public String getDescription(@PathVariable Integer id) {
		return service.getDescription(id);
	}

	@GetMapping(path = "/{id}/.next")
	public Integer getNext(@PathVariable Integer id) {
		return service.getNext(id);
	}

	@GetMapping(path = "/{id}/.prev")
	public Integer getPrev(@PathVariable Integer id) {
		return service.getPrev(id);
	}

	@GetMapping(path = "/.first")
	public Integer getFirst() {
		return service.getFirst();
	}

	@GetMapping(path = "/.last")
	public Integer getLast() {
		return service.getLast();
	}

	@GetMapping(path = "/{id}")
	public T get(@PathVariable Integer id) {
		return service.get(id);
	}

	@PutMapping(path = "/{id}")
	public void update(@PathVariable Integer id, @RequestBody T entity) {
		service.update(id, entity);
	}

	@DeleteMapping(path = "/{id}")
	public void delete(@PathVariable Integer id) {
		service.delete(id);
		response.setStatus(HttpStatus.NO_CONTENT.value());
	}

	@GetMapping
	public List<T> search() {
		return service.search();
	}
}
