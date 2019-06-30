package com.next.c1.controller;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.next.c1.data.BatchDescNode;
import com.next.c1.data.BatchDescResult;
import com.next.c1.service.BaseService;
import com.next.c1.service.OADMService;

@RestController
@RequestMapping(path="/api/batch")
public class BatchDescriptionController {
	
	@Autowired
	ApplicationContext appContext;
	
	@PostMapping(path="/desc")
	public BatchDescResult getAllMetadata(@RequestBody BatchDescResult body) throws ClassNotFoundException {
		for(Entry<String, BatchDescNode> entry:body.entrySet()) {
			String tableName = entry.getKey();
			String serviceClassName = OADMService.class.getPackage().getName() + "." + tableName + "Service";
			Class<?> serviceClass = Class.forName(serviceClassName);
			BaseService<?, ?> service = (BaseService<?, ?>) appContext.getBean(serviceClass);
			
			for(Map.Entry<Integer,String> id:entry.getValue().entrySet()) {
				String desc = service.getDescription(id.getKey());
				id.setValue(desc);
			}
		}
		return body;		
	}
}
