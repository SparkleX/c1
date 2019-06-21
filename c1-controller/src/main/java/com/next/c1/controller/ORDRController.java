package com.next.c1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.next.c1.domain.DoORDR;
import com.next.c1.service.ORDRService;
@RestController
@RequestMapping(path="/api/ORDR")
public class ORDRController extends BaseController<DoORDR, ORDRService>{

}
