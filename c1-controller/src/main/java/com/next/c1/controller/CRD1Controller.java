package com.next.c1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.next.c1.domain.DoCRD1;
import com.next.c1.service.CRD1Service;

@RestController
@RequestMapping(path="/api/CRD1")
public class CRD1Controller extends BaseLineController<DoCRD1, CRD1Service>{
	
}
