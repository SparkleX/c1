package com.next.c1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import com.next.c1.service.BaseService;

public class BaseController<T, SERVICE extends BaseService<T,?>> {
	
	@Autowired
	protected SERVICE service;
	
	@PostMapping
	public T create(T o) {
		return service.create(o);
	}
}
