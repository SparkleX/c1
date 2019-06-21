package com.next.c1.controller;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.next.c1.domain.DoORDR;
import com.next.c1.service.ORDRService;

@RunWith(SpringRunner.class)
@WebMvcTest(ORDRController.class)
public class ORDRControllerTest extends BaseControllerTest<DoORDR,ORDRService>{
}