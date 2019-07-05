package com.next.c1.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.next.c1.service.BaseLineService;

public class BaseLineController<T, SERVICE extends BaseLineService<T, ?>> {

	@Autowired
	protected SERVICE service;
	@Autowired
	protected HttpServletResponse response;

	@PostMapping
	public void create(@RequestBody T o) {
		service.create(o);
	}

	@GetMapping(path = "/{id}/desc")
	public String getDescription(@PathVariable Integer id) {
		return service.getDescription(id);
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

	@GetMapping
	public List<T> search() {
		return service.search();
	}
}
