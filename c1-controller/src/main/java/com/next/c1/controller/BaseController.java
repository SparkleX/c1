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
import org.springframework.web.bind.annotation.RequestParam;

import com.next.c1.service.BaseService;

public class BaseController<T, SERVICE extends BaseService<T, ?>> {

	@Autowired
	protected SERVICE service;
	@Autowired
	protected HttpServletResponse response;

	@PostMapping
	public void create(@RequestBody T o) {
		service.create(o);
	}

	@GetMapping(path = "/{id}/.desc")
	public String getDescription(@PathVariable Integer id) {
		return service.getDescription(id);
	}

	@GetMapping(path = "/{id}/.next")
	public Integer getNext(@PathVariable Integer id) {
		Integer newId=service.getNext(id);
		return seek(newId);
		
	}

	private Integer seek(Integer newId) {
		if(newId==null) {
			response.setStatus(HttpStatus.NO_CONTENT.value());
		}
		return newId;
	}

	@GetMapping(path = "/{id}/.prev")
	public Integer getPrev(@PathVariable Integer id) {
		Integer newId=service.getPrev(id);
		return seek(newId);
	}

	@GetMapping(path = "/.first")
	public Integer getFirst() {
		Integer newId=service.getFirst();
		return seek(newId);
	}

	@GetMapping(path = "/.last")
	public Integer getLast() {
		Integer newId=service.getLast();
		return seek(newId);
	}
	@GetMapping(path = "/init")
	public T init() {
		T rt = this.service.init();
		return rt;
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
	@PostMapping(path = "/change")
	public T change(@RequestBody T data, @RequestParam("table") String table, @RequestParam("column") String column, @RequestParam("row") Integer row) {
		T rt = service.change(data, table, column, row);
		return rt;
	}
	@GetMapping
	public List<T> search() {
		return service.search();
	}
}
