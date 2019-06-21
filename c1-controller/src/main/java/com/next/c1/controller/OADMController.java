package com.next.c1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.next.c1.domain.DoOADM;
import com.next.c1.service.OADMService;

@RestController
@RequestMapping(path="/api/OCRD")
public class OADMController extends BaseController<DoOADM, OADMService>{
	
}
