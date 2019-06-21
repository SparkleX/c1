package com.next.c1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.next.c1.domain.DoOITM;
import com.next.c1.service.OITMService;
@RestController
@RequestMapping(path="/api/OITM")
public class OITMController extends BaseController<DoOITM, OITMService>{

}
