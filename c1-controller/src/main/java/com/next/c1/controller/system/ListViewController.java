package com.next.c1.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.next.c1.schema.choose.List;
import com.next.c1.service.sys.ListViewService;

@RestController
@RequestMapping(path="/api/list")
public class ListViewController {
	@Autowired
	ListViewService service;
	@GetMapping(path="/")
	public List getAllMetadata(@RequestParam("table") String table) {
		return service.getData(table);
	}
}
