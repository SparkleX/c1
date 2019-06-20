package com.next.c1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.next.c1.domain.OCRDDomain;
import com.next.c1.service.OCRDService;

@RestController
@RequestMapping(path="/api/OCRD")
public class OCRDController extends BaseController<OCRDDomain, OCRDService>{
	
}
