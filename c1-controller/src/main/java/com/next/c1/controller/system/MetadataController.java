package com.next.c1.controller.system;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.next.c1.schema.table.Table;
import com.next.c1.service.sys.MetadataService;

@RestController
@RequestMapping(path="/api/metadata")
public class MetadataController {
	@Autowired
	MetadataService service;
	@GetMapping(path="/")
	public Map<String, Table> getAllMetadata() {
		return service.getAllMetadata();
	}
}
